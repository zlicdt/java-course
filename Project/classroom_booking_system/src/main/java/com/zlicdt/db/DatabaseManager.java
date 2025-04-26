package com.zlicdt.db;

import java.sql.*;
import java.util.concurrent.locks.ReentrantLock;
import com.zlicdt.exceptions.DatabaseConnectionException;
import com.zlicdt.exceptions.DatabaseQueryException;

/**
 * Database connection manager to handle SQLite connections
 * Uses singleton pattern to ensure only one instance manages connections
 */
public class DatabaseManager implements DatabaseInterface {
    private static DatabaseManager instance;
    private static final String DB_URL = "jdbc:sqlite:data.db";
    private static final ReentrantLock lock = new ReentrantLock();
    private static final int MAX_RETRY = 5;
    private static final int RETRY_DELAY = 200; // milliseconds
    
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
    @Override
    public Connection getConnection() throws DatabaseConnectionException {
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
                    throw new DatabaseConnectionException("Unable to connect to database, retried " + MAX_RETRY + " times", e);
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
    @Override
    public ResultSet executeQuery(String sql) throws DatabaseConnectionException, DatabaseQueryException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            lock.lock();
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            throw new DatabaseQueryException("SQL query execution failed: " + sql, e);
        } finally {
            lock.unlock();
            // Note: we don't close the ResultSet and Connection here
            // as they are needed by the caller. Caller must close them.
        }
    }
    
    /**
     * Execute query with prepared statement (method overloading)
     * Overloaded method that executes queries using prepared statements
     */
    public ResultSet executeQuery(String sql, Object[] params) throws DatabaseConnectionException, DatabaseQueryException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            lock.lock();
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            
            // Set parameters
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new DatabaseQueryException("Prepared SQL query execution failed: " + sql, e);
        } finally {
            lock.unlock();
            // Caller must close resources
        }
    }
    
    /**
     * Execute update with auto-close resources and retry mechanism
     */
    @Override
    public int executeUpdate(String sql) throws DatabaseConnectionException, DatabaseQueryException {
        Connection conn = null;
        Statement stmt = null;
        int result = 0;
        
        try {
            lock.lock();
            conn = getConnection();
            stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
            return result;
        } catch (SQLException e) {
            throw new DatabaseQueryException("SQL update operation failed: " + sql, e);
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
     * Execute update with prepared statement (method overloading)
     * Overloaded method that executes updates using prepared statements
     */
    public int executeUpdate(String sql, Object[] params) throws DatabaseConnectionException, DatabaseQueryException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        try {
            lock.lock();
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            
            // Set parameters
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            
            result = pstmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new DatabaseQueryException("Prepared SQL update operation failed: " + sql, e);
        } finally {
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException e) { /* ignore */ }
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
    @Override
    public boolean bookingExists(String date, String time, String room) throws DatabaseConnectionException, DatabaseQueryException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            String sql = "SELECT * FROM book WHERE date=? AND time=? AND room=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            pstmt.setString(2, time);
            pstmt.setString(3, room);
            rs = pstmt.executeQuery();
            return rs.next(); // Returns true if there's at least one result
        } catch (SQLException e) {
            throw new DatabaseQueryException("Failed to query booking record", e);
        } finally {
            closeResources(conn, pstmt, rs);
        }
    }
    
    /**
     * Add a new booking
     */
    @Override
    public boolean addBooking(String username, String date, String time, String room) throws DatabaseConnectionException, DatabaseQueryException {
        String sql = "INSERT INTO book (name, time, date, room) VALUES (?, ?, ?, ?)";
        Object[] params = {username, time, date, room};
        return executeUpdate(sql, params) > 0;
    }
    
    /**
     * Overloaded method that adds a booking with a note
     */
    public boolean addBooking(String username, String date, String time, String room, String note) throws DatabaseConnectionException, DatabaseQueryException {
        String sql = "INSERT INTO book (name, time, date, room, note) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {username, time, date, room, note};
        return executeUpdate(sql, params) > 0;
    }
    
    /**
     * Delete a booking by ID
     */
    @Override
    public boolean deleteBooking(int bookingId) throws DatabaseConnectionException, DatabaseQueryException {
        String sql = "DELETE FROM book WHERE id=?";
        Object[] params = {bookingId};
        return executeUpdate(sql, params) > 0;
    }
    
    /**
     * Get all bookings for a user
     */
    @Override
    public ResultSet getUserBookings(String username) throws DatabaseConnectionException, DatabaseQueryException {
        String sql = "SELECT * FROM book WHERE name=?";
        Object[] params = {username};
        return executeQuery(sql, params);
    }
    
    /**
     * Overloaded method that gets user bookings for a specific date
     */
    public ResultSet getUserBookings(String username, String date) throws DatabaseConnectionException, DatabaseQueryException {
        String sql = "SELECT * FROM book WHERE name=? AND date=?";
        Object[] params = {username, date};
        return executeQuery(sql, params);
    }
    
    /**
     * Check if admin user exists in the database
     */
    @Override
    public boolean adminExists() throws DatabaseConnectionException, DatabaseQueryException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            String sql = "SELECT * FROM accounts WHERE username=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "admin");
            rs = pstmt.executeQuery();
            return rs.next(); // Returns true if admin exists
        } catch (SQLException e) {
            throw new DatabaseQueryException("Error checking admin account", e);
        } finally {
            closeResources(conn, pstmt, rs);
        }
    }
    
    /**
     * Create admin user with the given password
     */
    @Override
    public boolean createAdminUser(String password) throws DatabaseConnectionException, DatabaseQueryException {
        String sql = "INSERT INTO accounts (username, password, email) VALUES (?, ?, ?)";
        Object[] params = {"admin", password, "admin@system.com"};
        return executeUpdate(sql, params) > 0;
    }
    
    /**
     * Overloaded method that creates an admin user with a custom email
     */
    public boolean createAdminUser(String password, String email) throws DatabaseConnectionException, DatabaseQueryException {
        String sql = "INSERT INTO accounts (username, password, email) VALUES (?, ?, ?)";
        Object[] params = {"admin", password, email};
        return executeUpdate(sql, params) > 0;
    }
    
    /**
     * Get all bookings (for admin user)
     */
    @Override
    public ResultSet getAllBookings() throws DatabaseConnectionException, DatabaseQueryException {
        String sql = "SELECT * FROM book ORDER BY date, time";
        return executeQuery(sql);
    }
    
    /**
     * Overloaded method that gets all bookings for a specific date
     */
    public ResultSet getAllBookings(String date) throws DatabaseConnectionException, DatabaseQueryException {
        String sql = "SELECT * FROM book WHERE date=? ORDER BY time";
        Object[] params = {date};
        return executeQuery(sql, params);
    }
}