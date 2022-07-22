package br.com.agrotis.api.builder;

import br.com.agrotis.api.domain.entity.User;
import br.com.agrotis.api.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Component
public class UserBuilder extends ConstrutorDeEntidade<User> {

    private static final long ID = 10;
    private static final String Name = "Fulano";
    private static final String TAX_NUMBER = "38.230.504/0001-70";
    private static final LocalDateTime START_DATE = LocalDateTime.now().plusDays(5);
    private static final LocalDateTime END_DATE = LocalDateTime.now().plusDays(30);
    private static final LocalDateTime CREATED_AT = LocalDateTime.now();
    private static final LocalDateTime LAST_UPDATE = LocalDateTime.now();

    @Autowired
    private UserRepository repository;

    @Autowired
    private LaboratoryBuilder laboratoryBuilder;

    @Autowired
    private PropertyBuilder propertyBuilder;

    @Override
    public User construirEntidade() throws ParseException {
        return User.builder()
                .id(ID)
                .name(Name)
                .taxNumber(TAX_NUMBER)
                .startDate(START_DATE)
                .endDate(END_DATE)
                .laboratory(laboratoryBuilder.construirEntidade())
                .property(propertyBuilder.construirEntidade())
                .createdAt(CREATED_AT)
                .lastUpdate(LAST_UPDATE)
                .build();
    }

    @Override
    protected User persistir(User entidade) {
        return repository.save(entidade);
    }

    @Override
    protected Collection<User> obterTodos() {
        return repository.findAll();
    }

    @Override
    protected Optional<User> obterPorId(long id) {
        return repository.findById(id);
    }

}
