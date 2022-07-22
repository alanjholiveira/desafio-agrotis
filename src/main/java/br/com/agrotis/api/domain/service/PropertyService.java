package br.com.agrotis.api.domain.service;

import br.com.agrotis.api.domain.entity.Property;
import br.com.agrotis.api.infrastructure.exceptions.NotFoundException;
import br.com.agrotis.api.infrastructure.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository repository;

    @Autowired
    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public List<Property> findAll() {
        return repository.findAll();
    }


    public Property getById(final long id) {
        return findById(id);
    }

    public Property save(final Property entity) {
        return repository.save(entity);
    }

    public void update(final long id, final Property entity) {
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

    private Property findById(final long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }
    
}
