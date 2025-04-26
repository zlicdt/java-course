package com.zlicdt.exceptions;

/**
 * Custom exception class for handling database connection issues
 */
public class DatabaseConnectionException extends Exception {
    
    /**
     * No-argument constructor
     */
    public DatabaseConnectionException() {
        super("Database connection failed");
    }
    
    /**
     * Constructor with error message
     * 
     * @param message The error message
     */
    public DatabaseConnectionException(String message) {
        super(message);
    }
    
    /**
     * Constructor with error message and original cause
     * 
     * @param message The error message
     * @param cause The original exception that caused this exception
     */
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructor with original cause
     * 
     * @param cause The original exception that caused this exception
     */
    public DatabaseConnectionException(Throwable cause) {
        super("Database connection failed: " + cause.getMessage(), cause);
    }
}