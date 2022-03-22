package Exceptions;

public class EmptyRepositoryException extends RuntimeException {
    public EmptyRepositoryException() {
        super("The repository is empty");
    }

    @Override
    public String toString() {
        return "The repository is empty";
    }
}
