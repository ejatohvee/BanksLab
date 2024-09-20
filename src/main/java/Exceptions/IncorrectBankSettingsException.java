package Exceptions;

public class IncorrectBankSettingsException extends RuntimeException{
    public IncorrectBankSettingsException() {
    }

    public IncorrectBankSettingsException(String message) {
        super(message);
    }
}
