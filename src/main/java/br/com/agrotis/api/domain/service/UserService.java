package br.com.agrotis.api.domain.service;

import br.com.agrotis.api.domain.entity.User;
import br.com.agrotis.api.infrastructure.exceptions.NotFoundException;
import br.com.agrotis.api.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User getById(final long id) {
        return findById(id);
    }

    public User save(final User entity) {
        return repository.save(entity);
    }

    public void update(final long id, final User entity) {
        var result = Optional.of(findById(id))
                .map(r -> {
                    r.setId(id);
                    r.setName(entity.getName());
                    r.setEndDate(entity.getEndDate());
                    r.setStartDate(entity.getStartDate());
                    r.setProperty(entity.getProperty());
                    r.setObservations(entity.getObservations());
                    return r;
                });

        repository.save(result.get());
    }

    public void delete(long id) {
        var result = findById(id);
        repository.delete(result);
    }

    private User findById(final long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }
    
}
