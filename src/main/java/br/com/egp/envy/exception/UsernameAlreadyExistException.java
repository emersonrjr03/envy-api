package br.com.egp.envy.exception;

public class UsernameAlreadyExistException extends Exception {
    private String field;

    public UsernameAlreadyExistException(String message, Throwable cause, String field) {
        super(message, cause);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
