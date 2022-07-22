package br.com.agrotis.api.domain.service;

import br.com.agrotis.api.domain.entity.Laboratory;
import br.com.agrotis.api.infrastructure.exceptions.NotFoundException;
import br.com.agrotis.api.infrastructure.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratoryService {

    private final LaboratoryRepository repository;

    @Autowired
    public LaboratoryService(LaboratoryRepository repository) {
        this.repository = repository;
    }

    public List<Laboratory> findAll() {
        return repository.findAll();
    }


    public Laboratory getById(final long id) {
        return findById(id);
    }

    public Laboratory save(final Laboratory entity) {
        return repository.save(entity);
    }

    public void update(final long id, final Laboratory entity) {
        var result = Optional.of(findById(id))
                .map(r -> {
                    r.setId(id);
                    r.setName(entity.getName());
                    return r;
                });

        repository.save(result.get());
    }

    public void delete(long id) {
        var result = findById(id);
        repository.delete(result);
    }

    private Laboratory findById(final long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }
    
}
