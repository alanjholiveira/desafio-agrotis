package br.com.agrotis.api.application.rest.v1.request;

import br.com.agrotis.api.application.rest.v1.response.LaboratoryResponse;
import br.com.agrotis.api.application.rest.v1.response.PropertyResponse;
import br.com.agrotis.api.domain.entity.User;

import java.time.LocalDateTime;

public record UserRequest(
        String nome,
        LocalDateTime dataInicial,
        LocalDateTime dataFinal,
        PropertyResponse infosPropriedade,
        String cnpj,
        LaboratoryResponse laboratorio,
        String observacoes
) {

    public static User from(final UserRequest request) {
        return User.builder()
                .name(request.nome())
                .startDate(request.dataInicial())
                .endDate(request.dataFinal())
                .property(PropertyResponse.from(request.infosPropriedade()))
                .taxNumber(request.cnpj)
                .laboratory(LaboratoryResponse.from(request.laboratorio()))
                .build();
    }

    public static UserRequest from(final User entity) {
        return new UserRequest(entity.getName(), entity.getStartDate(), entity.getEndDate(),
                PropertyResponse.from(entity.getProperty()), entity.getTaxNumber(),
                LaboratoryResponse.from(entity.getLaboratory()), entity.getObservations());
    }

}
