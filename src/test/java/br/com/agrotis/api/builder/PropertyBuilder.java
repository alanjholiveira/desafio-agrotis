package br.com.agrotis.api.builder;

import br.com.agrotis.api.domain.entity.Property;
import br.com.agrotis.api.infrastructure.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Component
public class PropertyBuilder extends ConstrutorDeEntidade<Property> {

    private static final long ID = 10;
    private static final String Name = "Fulano";
    private static final LocalDateTime CREATED_AT = LocalDateTime.now();
    private static final LocalDateTime LAST_UPDATE = LocalDateTime.now();

    @Autowired
    private PropertyRepository repository;

    @Override
    public Property construirEntidade() throws ParseException {
        return Property.builder()
                .id(ID)
                .name(Name)
                .createdAt(CREATED_AT)
                .lastUpdate(LAST_UPDATE)
                .build();
    }

    @Override
    protected Property persistir(Property entidade) {
        return repository.save(entidade);
    }

    @Override
    protected Collection<Property> obterTodos() {
        return repository.findAll();
    }

    @Override
    protected Optional<Property> obterPorId(long id) {
        return repository.findById(id);
    }

}
