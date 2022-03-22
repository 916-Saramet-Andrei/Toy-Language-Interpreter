package Exceptions;

public class IndexException extends RuntimeException {
    public IndexException() {
        super("Index out of bounds");
    }

    public IndexException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "Index Exception " + super.getMessage();
    }
}
