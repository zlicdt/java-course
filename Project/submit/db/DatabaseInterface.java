package db;

import java.sql.*;
import exceptions.DatabaseConnectionException;
import exceptions.DatabaseQueryException;

public interface DatabaseInterface {
    /**
     * Get the database connection
     */
    Connection getConnection() throws DatabaseConnectionException;
    
    /**
     * Excute SQL query and return ResultSet
     */
    ResultSet executeQuery(String sql) throws DatabaseConnectionException, DatabaseQueryException;
    
    /**
     * Execute SQL update and return number of affected rows
     */
    int executeUpdate(String sql) throws DatabaseConnectionException, DatabaseQueryException;
    
    /**
     * Check if a booking exists
     */
    boolean bookingExists(String date, String time, String room) throws DatabaseConnectionException, DatabaseQueryException;
    
    /**
     * Add a new booking
     */
    boolean addBooking(String username, String date, String time, String room) throws DatabaseConnectionException, DatabaseQueryException;
    
    /**
     * Delete a booking by ID
     */
    boolean deleteBooking(int bookingId) throws DatabaseConnectionException, DatabaseQueryException;
    
    /**
     * Get all bookings for a specific user
     */
    ResultSet getUserBookings(String username) throws DatabaseConnectionException, DatabaseQueryException;
    
    /**
     * Check if an admin user exists
     */
    boolean adminExists() throws DatabaseConnectionException, DatabaseQueryException;
    
    /**
     * Create a new admin user if it doesn't exist
     */
    boolean createAdminUser(String password) throws DatabaseConnectionException, DatabaseQueryException;
    
    /**
     * Get all bookings from the database
     */
    ResultSet getAllBookings() throws DatabaseConnectionException, DatabaseQueryException;
}