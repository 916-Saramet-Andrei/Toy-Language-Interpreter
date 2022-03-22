package Exceptions;

public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException() {
        super();
    }

    public DivisionByZeroException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "Division by 0 Exception ";
    }
}
