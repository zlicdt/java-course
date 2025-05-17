package exceptions;

public class DatabaseConnectionException extends Exception {
    
    // Default constructor
    public DatabaseConnectionException() {
        super("Database connection failed");
    }
    
    // Constructor with error message
    public DatabaseConnectionException(String message) {
        super(message);
    }
    
    // Overloaded constructor with error message and cause
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}