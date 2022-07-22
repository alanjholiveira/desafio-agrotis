package br.com.agrotis.api.infrastructure.exceptions;

public class NotFoundException extends RuntimeException {

    private static final String MESSAGE = "Registro Não Encontrado";

    public NotFoundException() {
        super(MESSAGE);
    }
}
