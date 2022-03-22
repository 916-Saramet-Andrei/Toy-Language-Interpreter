package Exceptions;

public class FileException extends RuntimeException {
    public FileException(Throwable cause) {
        super(cause);
    }

    public FileException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
