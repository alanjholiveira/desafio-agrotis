package br.com.agrotis.api.application.rest.v1.request;

import br.com.agrotis.api.domain.entity.Laboratory;

public record LaboratoryRequest(long id, String nome) {

    public static Laboratory from(final LaboratoryRequest request) {
        return Laboratory.builder()
                .id(request.id())
                .name(request.nome())
                .build();
    }

}
