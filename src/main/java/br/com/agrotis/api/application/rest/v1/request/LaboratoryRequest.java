package br.com.agrotis.api.application.rest.v1.request;

import br.com.agrotis.api.domain.entity.Laboratory;

public record LaboratoryRequest(String nome) {

    public static Laboratory from(final LaboratoryRequest request) {
        return Laboratory.builder()
                .name(request.nome())
                .build();
    }

    public static LaboratoryRequest from(final Laboratory entity) {
        return new LaboratoryRequest(entity.getName());
    }

}
