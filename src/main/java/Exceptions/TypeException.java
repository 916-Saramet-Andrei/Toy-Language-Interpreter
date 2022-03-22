package Exceptions;

public class TypeException extends RuntimeException {
    public TypeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "Type Exception " + super.getMessage();
    }
}
