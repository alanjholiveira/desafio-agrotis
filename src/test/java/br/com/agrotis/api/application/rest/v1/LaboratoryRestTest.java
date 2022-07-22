package br.com.agrotis.api.application.rest.v1;

import br.com.agrotis.api.application.rest.v1.request.LaboratoryRequest;
import br.com.agrotis.api.builder.LaboratoryBuilder;
import br.com.agrotis.api.domain.entity.Laboratory;
import br.com.agrotis.api.domain.service.LaboratoryService;
import br.com.agrotis.api.infrastructure.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class LaboratoryRestTest {

    private static final String URL = "/v1/laboratories";
    private static final long ID = 10;

    @Autowired
    private LaboratoryRest restAPi;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private LaboratoryService service;

    @Autowired
    private LaboratoryBuilder builder;

    private Laboratory entity;


    @BeforeEach
    public void setup() throws ParseException {
        entity = builder.construirEntidade();
        standaloneSetup(this.restAPi);
        given().webAppContextSetup(webApplicationContext);
    }

    @Test
    void when_findAll_returns_success() {
        when(service.findAll()).thenReturn(List.of(entity));

        given()
                .accept(MediaType.APPLICATION_JSON)
                .when()
                .get(URL)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void when_getById_returns_success() {

        when(service.getById(10)).thenReturn(entity);

        given()
                .accept(MediaType.APPLICATION_JSON)
                .when()
                .get(URL.concat("/").concat(String.valueOf(ID)))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void when_save_return_sucess() throws ParseException, IOException {
        var request = builder.construirEntidade();

        when(service.save(any(Laboratory.class))).thenReturn(entity);

        given()
                .accept(MediaType.APPLICATION_JSON)
                .contentType("application/json")
                .body(TestUtil.convertObjectToJsonBytes(LaboratoryRequest.from(request)))
                .when()
                .post(URL)
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void when_update_return_sucess() throws ParseException, IOException {
        var request = builder.construirEntidade();
        request.setName("Fulano Silva");

        given()
                .accept(MediaType.APPLICATION_JSON)
                .contentType("application/json")
                .body(TestUtil.convertObjectToJsonBytes(LaboratoryRequest.from(request)))
                .when()
                .put(URL.concat("/").concat(String.valueOf(ID)))
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void when_delete_returns_success() {

        given()
                .accept(MediaType.APPLICATION_JSON)
                .when()
                .delete(URL.concat("/").concat(String.valueOf(ID)))
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }


}
