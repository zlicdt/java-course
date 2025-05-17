package exceptions;

public class DatabaseQueryException extends Exception {
    
    // No-argument constructor
    public DatabaseQueryException() {
        super("Database query operation failed");
    }
    
    // Constructor with error message
    public DatabaseQueryException(String message) {
        super(message);
    }
    
    // Constructor with error message and original cause
    public DatabaseQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}