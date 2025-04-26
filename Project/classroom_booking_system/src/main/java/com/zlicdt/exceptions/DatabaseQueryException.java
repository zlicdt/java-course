package com.zlicdt.exceptions;

/**
 * Custom exception class for handling database query or operation issues
 */
public class DatabaseQueryException extends Exception {
    
    /**
     * No-argument constructor
     */
    public DatabaseQueryException() {
        super("Database query operation failed");
    }
    
    /**
     * Constructor with error message
     * 
     * @param message The error message
     */
    public DatabaseQueryException(String message) {
        super(message);
    }
    
    /**
     * Constructor with error message and original cause
     * 
     * @param message The error message
     * @param cause The original exception that caused this exception
     */
    public DatabaseQueryException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructor with original cause
     * 
     * @param cause The original exception that caused this exception
     */
    public DatabaseQueryException(Throwable cause) {
        super("Database query operation failed: " + cause.getMessage(), cause);
    }
}