package com.zlicdt.db;

import java.sql.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Database connection manager to handle SQLite connections
 * Uses singleton pattern to ensure only one instance manages connections
 */
public class DatabaseManager {
    private static DatabaseManager instance;
    private static final String DB_URL = "jdbc:sqlite:data.db";
    private static final ReentrantLock lock = new ReentrantLock();
    private static final int MAX_RETRY = 5;
    private static final int RETRY_DELAY = 200; // milliseconds
    
    private DatabaseManager() {
        // Private constructor for singleton pattern
    }
    
    /**
     * Get the singleton instance of DatabaseManager
     */
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    /**
     * Get a database connection with retry mechanism
     */
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        int attempts = 0;
        
        while (conn == null && attempts < MAX_RETRY) {
            try {
                conn = DriverManager.getConnection(DB_URL);
                // Set timeout to avoid indefinite waiting
                Statement stmt = conn.createStatement();
                stmt.execute("PRAGMA busy_timeout = 5000");
                stmt.close();
            } catch (SQLException e) {
                attempts++;
                if (attempts >= MAX_RETRY) {
                    throw e; // Re-throw if we've reached max retries
                }
                
                try {
                    Thread.sleep(RETRY_DELAY * attempts);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        
        return conn;
    }
    
    /**
     * Execute query with auto-close resources
     */
    public ResultSet executeQuery(String sql) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            lock.lock();
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            return rs;
        } finally {
            lock.unlock();
            // Note: we don't close the ResultSet and Connection here
            // as they are needed by the caller. Caller must close them.
        }
    }
    
    /**
     * Execute update with auto-close resources and retry mechanism
     */
    public int executeUpdate(String sql) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        int result = 0;
        
        try {
            lock.lock();
            conn = getConnection();
            stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
            return result;
        } finally {
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) { /* ignore */ }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { /* ignore */ }
            }
            lock.unlock();
        }
    }
    
    /**
     * Safely close database resources
     */
    public static void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try { rs.close(); } catch (SQLException e) { /* ignore */ }
        }
        if (stmt != null) {
            try { stmt.close(); } catch (SQLException e) { /* ignore */ }
        }
        if (conn != null) {
            try { conn.close(); } catch (SQLException e) { /* ignore */ }
        }
    }
    
    /**
     * Check if a booking already exists
     */
    public boolean bookingExists(String date, String time, String room) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM book WHERE date='" + date + 
                         "' AND time='" + time + 
                         "' AND room='" + room + "'";
            rs = stmt.executeQuery(sql);
            return rs.next(); // Returns true if there's at least one result
        } finally {
            closeResources(conn, stmt, rs);
        }
    }
    
    /**
     * Add a new booking
     */
    public boolean addBooking(String username, String date, String time, String room) throws SQLException {
        String sql = "INSERT INTO book (name, time, date, room) VALUES ('" + 
                     username + "', '" + 
                     time + "', '" + 
                     date + "', '" + 
                     room + "')";
        
        return executeUpdate(sql) > 0;
    }
    
    /**
     * Delete a booking by ID
     */
    public boolean deleteBooking(int bookingId) throws SQLException {
        String sql = "DELETE FROM book WHERE id=" + bookingId;
        return executeUpdate(sql) > 0;
    }
    
    /**
     * Get all bookings for a user
     */
    public ResultSet getUserBookings(String username) throws SQLException {
        String sql = "SELECT * FROM book WHERE name='" + username + "'";
        return executeQuery(sql);
    }
    
    /**
     * Check if admin user exists in the database
     */
    public boolean adminExists() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM accounts WHERE username='admin'";
            rs = stmt.executeQuery(sql);
            return rs.next(); // Returns true if admin exists
        } finally {
            closeResources(conn, stmt, rs);
        }
    }
    
    /**
     * Create admin user with the given password
     */
    public boolean createAdminUser(String password) throws SQLException {
        String sql = "INSERT INTO accounts (username, password, email) VALUES ('admin', '" + 
                     password + "', 'admin@system.com')";
        
        return executeUpdate(sql) > 0;
    }
    
    /**
     * Get all bookings (for admin user)
     */
    public ResultSet getAllBookings() throws SQLException {
        String sql = "SELECT * FROM book ORDER BY date, time";
        return executeQuery(sql);
    }
}