package Exceptions;

public class WithdrawMoneyException extends RuntimeException{
    public WithdrawMoneyException() {
    }

    public WithdrawMoneyException(String message) {
        super(message);
    }
}
