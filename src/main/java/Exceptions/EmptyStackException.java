package Exceptions;

public class EmptyStackException extends RuntimeException {
    public EmptyStackException() {
        super("The stack is empty");
    }

    @Override
    public String toString() {
        return "Empty Stack Exception";
    }
}
