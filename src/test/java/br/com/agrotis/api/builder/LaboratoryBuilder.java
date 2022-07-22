package br.com.agrotis.api.builder;

import br.com.agrotis.api.domain.entity.Laboratory;
import br.com.agrotis.api.infrastructure.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Component
public class LaboratoryBuilder extends ConstrutorDeEntidade<Laboratory> {

    private static final long ID = 10;
    private static final String Name = "Fulano";
    private static final LocalDateTime CREATED_AT = LocalDateTime.now();
    private static final LocalDateTime LAST_UPDATE = LocalDateTime.now();

    @Autowired
    private LaboratoryRepository repository;

    @Override
    public Laboratory construirEntidade() throws ParseException {
        return Laboratory.builder()
                .id(ID)
                .name(Name)
                .createdAt(CREATED_AT)
                .lastUpdate(LAST_UPDATE)
                .build();
    }

    @Override
    protected Laboratory persistir(Laboratory entidade) {
        return repository.save(entidade);
    }

    @Override
    protected Collection<Laboratory> obterTodos() {
        return repository.findAll();
    }

    @Override
    protected Optional<Laboratory> obterPorId(long id) {
        return repository.findById(id);
    }

}
