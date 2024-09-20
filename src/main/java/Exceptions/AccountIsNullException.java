package Exceptions;

public class AccountIsNullException extends RuntimeException {
    public AccountIsNullException() {
        super();
    }

    public AccountIsNullException(String message) {
        super(message);
    }
}