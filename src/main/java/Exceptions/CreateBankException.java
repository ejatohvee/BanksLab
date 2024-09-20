package Exceptions;

public class CreateBankException extends RuntimeException{
    public CreateBankException() {
        super();
    }

    public CreateBankException(String message) {
        super(message);
    }
}
