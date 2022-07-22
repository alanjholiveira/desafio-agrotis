package br.com.agrotis.api.application.rest.v1;

import br.com.agrotis.api.application.rest.v1.request.PropertyRequest;
import br.com.agrotis.api.application.rest.v1.response.PropertyResponse;
import br.com.agrotis.api.domain.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/v1/properties")
@RestController
public class PropertyRest {

    private final PropertyService service;

    public PropertyRest(PropertyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponse>> getAll() {
        var response = service.findAll().parallelStream()
                .map(PropertyResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getById(@PathVariable final long id) {
        var response = service.getById(id);

        return ResponseEntity.ok(PropertyResponse.from(response));
    }

    @PostMapping
    public ResponseEntity<PropertyResponse> save(@RequestBody @Valid PropertyRequest request) {
        var response = service.save(PropertyRequest.from(request));

        return new ResponseEntity<>(PropertyResponse.from(response), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final long id,
                                       @RequestBody @Valid final PropertyRequest request) {
        service.update(id, PropertyRequest.from(request));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final long id) {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
