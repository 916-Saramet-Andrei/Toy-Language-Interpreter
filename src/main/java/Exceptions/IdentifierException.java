package Exceptions;

public class IdentifierException extends RuntimeException {
    public IdentifierException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "Identifier Exception " + super.getMessage();
    }
}
