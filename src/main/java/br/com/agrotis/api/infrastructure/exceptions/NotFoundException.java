package br.com.agrotis.api.infrastructure.exceptions;

public class NotFoundException extends RuntimeException {

    private static final String MESSAGE = "";

    public NotFoundException() {
        super(MESSAGE);
    }
}
