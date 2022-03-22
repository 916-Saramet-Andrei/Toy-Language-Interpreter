package Exceptions;

public class ThreadingExecutionException extends RuntimeException {
    public ThreadingExecutionException(Throwable cause) {
        super(cause);
    }

    public ThreadingExecutionException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
