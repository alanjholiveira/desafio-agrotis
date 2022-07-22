package br.com.agrotis.api.domain.service;

import br.com.agrotis.api.builder.LaboratoryBuilder;
import br.com.agrotis.api.domain.entity.Laboratory;
import br.com.agrotis.api.infrastructure.exceptions.NotFoundException;
import br.com.agrotis.api.infrastructure.repository.LaboratoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LaboratoryServiceTest {

    @InjectMocks
    private LaboratoryService service;

    @Autowired
    private LaboratoryBuilder builder;

    @Mock
    private LaboratoryRepository repository;

    private List<Laboratory> laboratories;
    private Laboratory entity1;
    private Laboratory entity2;

    @BeforeEach
    void init() throws ParseException {

        entity1 = builder.construirEntidade();

        entity2 = builder.construirEntidade();
        entity2.setId(20);

        laboratories = new ArrayList<>();
        laboratories.addAll(Arrays.asList(entity1, entity2));
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(laboratories);

        var result = service.findAll();

        assertNotNull(result);
        assertEquals(laboratories, result);
    }

    @Test
    void testGetById() {
        when(repository.findById(any())).thenReturn(Optional.of(entity1));

        var result = service.getById(1);

        assertNotNull(result);
        assertEquals(entity1, result);
    }

    @Test
    void testGetByIdException() throws NotFoundException {
        Mockito.doReturn(null).when(repository).save(any());
        AtomicReference<Laboratory> result = new AtomicReference<Laboratory>();

        Exception exception = assertThrows(NotFoundException.class, () ->
                result.set(service.getById(0))
        );

        assertNull(result.get());
        assertEquals("Registro Não Encontrado", exception.getMessage());
    }


    @Test
    void testSave() {
        when(repository.save(any(Laboratory.class))).thenReturn(entity1);

        var result = service.save(entity1);

        assertNotNull(result);
        assertEquals(entity1, result);
    }

    @Test
    void testUpdate() {
        when(repository.findById(any())).thenReturn(Optional.of(entity1));
        when(repository.save(any(Laboratory.class))).thenReturn(entity2);

        service.update(20, entity2);

        verify(repository, times(1)).save(entity2);
    }

    @Test
    void testDelete() {
        when(repository.findById(any())).thenReturn(Optional.of(entity1));
        Mockito.doNothing()
                        .when(repository)
                                .delete(any(Laboratory.class));

        service.delete(entity1.getId());

        verify(repository, times(1)).delete(entity1);
    }

}
