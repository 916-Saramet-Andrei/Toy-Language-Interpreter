package Exceptions;

public class KeyException extends RuntimeException {
    public KeyException() {
        super("the key does not exist");
    }

    public KeyException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "Key Exception " + super.getMessage();
    }
}
