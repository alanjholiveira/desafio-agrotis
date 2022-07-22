package br.com.agrotis.api.application.rest.v1;

import br.com.agrotis.api.application.rest.v1.request.UserRequest;
import br.com.agrotis.api.application.rest.v1.response.UserResponse;
import br.com.agrotis.api.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/v1/users")
@RestController
public class UserRest {

    private final UserService service;

    public UserRest(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        var response = service.findAll().parallelStream()
                .map(UserResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable final long id) {
        var response = service.getById(id);

        return ResponseEntity.ok(UserResponse.from(response));
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest request) {
        var response = service.save(UserRequest.from(request));

        return new ResponseEntity<>(UserResponse.from(response), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final long id,
                                       @RequestBody @Valid final UserRequest request) {
        service.update(id, UserRequest.from(request));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final long id) {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
