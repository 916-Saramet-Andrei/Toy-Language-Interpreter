package Exceptions;

public class InvalidHeapAddressException extends RuntimeException {
    public InvalidHeapAddressException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "Invalid Heap Address Exception " + super.getMessage();
    }
}
