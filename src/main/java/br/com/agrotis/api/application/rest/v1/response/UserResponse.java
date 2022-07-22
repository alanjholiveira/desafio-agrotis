package br.com.agrotis.api.application.rest.v1.response;

import br.com.agrotis.api.domain.entity.User;

import java.time.LocalDateTime;

public record UserResponse(
        long id,
        String nome,
        LocalDateTime dataInicial,
        LocalDateTime dataFinal,
        String infosPropriedade,
        String cnpj,
        String laboratorio,
        String observacoes
) {

    public static UserResponse from(final User entity) {
        return new UserResponse(entity.getId(), entity.getName(), entity.getStartDate(), entity.getEndDate(),
                entity.getProperty().getName(), entity.getTaxNumber(), entity.getLaboratory().getName(),
                entity.getObservations());
    }

}
