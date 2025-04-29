package question3;

// Exception class for trying to open a nonexistent door.
public class BadDoorException extends Exception {
    public BadDoorException(String message) {
        super(message);
    }
}