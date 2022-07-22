package br.com.agrotis.api.application.rest.v1.request;

import br.com.agrotis.api.domain.entity.Property;

public record PropertyRequest(String nome) {

    public static Property from(final PropertyRequest request) {
        return Property.builder()
                .name(request.nome())
                .build();
    }

    public static PropertyRequest from(final Property entity) {
        return new PropertyRequest(entity.getName());
    }

}
