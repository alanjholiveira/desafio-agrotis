package br.com.agrotis.api.application.rest.v1.response;

import br.com.agrotis.api.domain.entity.Laboratory;

public record LaboratoryResponse(long id, String nome) {

    public static LaboratoryResponse from(final Laboratory entity) {
        return new LaboratoryResponse(entity.getId(), entity.getName());
    }

}
