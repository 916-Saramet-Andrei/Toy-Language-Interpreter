package Exceptions;

public class TypeCheckException extends RuntimeException {
    public TypeCheckException() {
        super("the types do not match");
    }

    public TypeCheckException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "TypeChecker Exception: " + this.getMessage();
    }
}
