package br.com.alura.med.infra.controllers;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.alura.med.mock.DadosCadastroMedicoMock;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class MedicoControllerIT {

    private static final String BASE_PATH_MEDICOS = "/medicos";
    private static String token;
    private static String secret = "test";
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeAll
    public static void setup() {
        // Gera um token JWT manualmente
        var algoritmo = Algorithm.HMAC256(secret);
        token = JWT.create()
                .withIssuer("API med")
                .withSubject("admin")
                .withClaim("id", 1L)
                .sign(algoritmo);
    }

    @Nested
    class CadastrarMedico {

        @Test
        @DisplayName("Deve retornar medico criado")
        void deveRetornarMedicoCriado() {
            given().header(AUTHORIZATION, String.format("Bearer %s", token))
                    .contentType(APPLICATION_JSON_VALUE)
                    .body(DadosCadastroMedicoMock.newDados())
                    .log().all()
                    .when()
                    .post(BASE_PATH_MEDICOS)
                    .then()
                    .statusCode(CREATED.value())
                    .body(matchesJsonSchemaInClasspath("schemas/medico.json"));
        }
    }

    @Nested
    class ListarMedicos {

        @Test
        @DisplayName("Deve retornar lista de medicos")
        void deveRetornarListaDeMedicos() {
            given()
                    .log().all()
                    .when()
                    .get(BASE_PATH_MEDICOS)
                    .then()
                    .statusCode(OK.value())
                    .body("size()", greaterThan(0))
                    .body("content[0].nome", equalTo("Gabriel"))
                    .body("content[0].email", equalTo("admin@admin.com"))
                    .body(matchesJsonSchemaInClasspath("schemas/medico.page.json"));
        }
    }

    @Nested
    class DeleteMedico {

        @Test
        @DisplayName("Deve deletar medico")
        void deveDeletarMedico() {
            given().header(AUTHORIZATION, String.format("Bearer %s", token))
                    .log().all()
                    .when()
                    .delete(BASE_PATH_MEDICOS + "/404")
                    .then()
                    .statusCode(204);
        }
    }

    @Nested
    class DetalharMedico {

        @Test
        @DisplayName("Não deve retornar medico")
        void naoDeveRetornarMedico() {
            given()
                    .header(AUTHORIZATION, String.format("Bearer %s", token))
                    .log().all()
                    .when()
                    .pathParam("id", 404)
                    .get(String.format("%s/{id}", BASE_PATH_MEDICOS))
                    .then()
                    .statusCode(NOT_FOUND.value())
                    .body(matchesJsonSchemaInClasspath("schemas/erro.json"));
        }
    }
}
