package br.com.agrotis.api.application.rest.v1;

import br.com.agrotis.api.application.rest.v1.request.LaboratoryRequest;
import br.com.agrotis.api.application.rest.v1.response.LaboratoryResponse;
import br.com.agrotis.api.domain.entity.Laboratory;
import br.com.agrotis.api.domain.service.LaboratoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/v1/laboratories")
@RestController
public class LaboratoryRest {

    private final LaboratoryService service;

    public LaboratoryRest(LaboratoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LaboratoryResponse>> getAll() {
        var response = service.findAll().parallelStream()
                .map(LaboratoryResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryResponse> getById(@PathVariable final long id) {
        Laboratory response = service.getById(id);

        return ResponseEntity.ok(LaboratoryResponse.from(response));
    }

    @PostMapping
    public ResponseEntity<LaboratoryResponse> save(@RequestBody @Valid LaboratoryRequest request) {
        var response = service.save(LaboratoryRequest.from(request));

        return new ResponseEntity<>(LaboratoryResponse.from(response), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final long id,
                                       @RequestBody @Valid final LaboratoryRequest request) {
        service.update(id, LaboratoryRequest.from(request));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final long id) {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
