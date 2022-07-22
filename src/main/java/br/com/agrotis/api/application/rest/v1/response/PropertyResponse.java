package br.com.agrotis.api.application.rest.v1.response;

import br.com.agrotis.api.domain.entity.Property;

public record PropertyResponse(long id, String nome) {

    public static PropertyResponse from(final Property entity) {
        return new PropertyResponse(entity.getId(), entity.getName());
    }

    public static Property from(final PropertyResponse response) {
        return Property.builder()
                .id(response.id())
                .name(response.nome())
                .build();
    }

}
