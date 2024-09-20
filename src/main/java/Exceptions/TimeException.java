package Exceptions;

public class TimeException extends RuntimeException {
    public TimeException() {
    }
    public TimeException(String message) {
        super(message);
    }
}
