package db;
import java.sql.*;
import exceptions.DatabaseConnectionException;
import exceptions.DatabaseQueryException;

public class sqliteAction implements DatabaseInterface {
    private static final String DB_URL = "jdbc:sqlite:data.db";
    
    public static void init() {
        Connection c = null;
        Statement stmt = null;
        
        try {
            c = DriverManager.getConnection(DB_URL);
            stmt = c.createStatement();
            String createAccountsString = "CREATE TABLE IF NOT EXISTS accounts (id INTEGER PRIMARY KEY, username TEXT, password TEXT, email TEXT)";
            stmt.executeUpdate(createAccountsString);
            String createBookString = "CREATE TABLE IF NOT EXISTS book (id INTEGER PRIMARY KEY, name TEXT, time TEXT, date TEXT, room TEXT, note TEXT)";
            stmt.executeUpdate(createBookString);
        } catch (SQLException e) {
            System.out.println("Database initialize error: " + e.getMessage());
        } finally {
            // Ensure connection to database is closed
            try {
                if (stmt != null) stmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                System.out.println("Can not release resources: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get the database connection
     */
    @Override
    public Connection getConnection() throws DatabaseConnectionException {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Can not connect to database: ", e);
        }
    }
    
    /**
     * Execute SQL query and return ResultSet
     */
    @Override
    public ResultSet executeQuery(String sql) throws DatabaseConnectionException, DatabaseQueryException {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new DatabaseQueryException("Failed to excute query: " + sql, e);
        }
    }
    
    /**
     * Execute SQL update and return number of affected rows
     */
    @Override
    public int executeUpdate(String sql) throws DatabaseConnectionException, DatabaseQueryException {
        Connection conn = null;
        Statement stmt = null;
        int result = 0;
        
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
            return result;
        } catch (SQLException e) {
            throw new DatabaseQueryException("Failed to update database: " + sql, e);
        } finally {
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) { /* ignore */ }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { /* ignore */ }
            }
        }
    }
    
    /**
     * Overload method: Check if a booking exists
     */
    public int executeUpdate(String sql, Object[] params) throws DatabaseConnectionException, DatabaseQueryException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            
            result = pstmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new DatabaseQueryException("Failed to update database: ", e);
        } finally {
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException e) { /* ignore */ }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { /* ignore */ }
            }
        }
    }
    
    // Let DatabaseManager to handle the database operations!
    @Override
    public boolean bookingExists(String date, String time, String room) throws DatabaseConnectionException, DatabaseQueryException {
        return DatabaseManager.getInstance().bookingExists(date, time, room);
    }
    
    @Override
    public boolean addBooking(String username, String date, String time, String room) throws DatabaseConnectionException, DatabaseQueryException {
        return DatabaseManager.getInstance().addBooking(username, date, time, room);
    }
    
    @Override
    public boolean deleteBooking(int bookingId) throws DatabaseConnectionException, DatabaseQueryException {
        return DatabaseManager.getInstance().deleteBooking(bookingId);
    }
    
    @Override
    public ResultSet getUserBookings(String username) throws DatabaseConnectionException, DatabaseQueryException {
        return DatabaseManager.getInstance().getUserBookings(username);
    }
    
    @Override
    public boolean adminExists() throws DatabaseConnectionException, DatabaseQueryException {
        return DatabaseManager.getInstance().adminExists();
    }
    
    @Override
    public boolean createAdminUser(String password) throws DatabaseConnectionException, DatabaseQueryException {
        return DatabaseManager.getInstance().createAdminUser(password);
    }
    
    @Override
    public ResultSet getAllBookings() throws DatabaseConnectionException, DatabaseQueryException {
        return DatabaseManager.getInstance().getAllBookings();
    }
}