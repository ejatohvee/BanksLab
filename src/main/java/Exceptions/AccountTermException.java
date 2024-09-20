package Exceptions;

public class AccountTermException extends RuntimeException{
    public AccountTermException() {
    }

    public AccountTermException(String message) {
        super(message);
    }
}
