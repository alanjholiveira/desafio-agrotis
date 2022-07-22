package br.com.agrotis.api.infrastructure.exceptions;

public class ErrorSaveException extends RuntimeException {

    private static final String MESSAGE = "";

    public ErrorSaveException() {
        super(MESSAGE);
    }
}
