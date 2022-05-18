package com.itis.kikoff;

import com.itis.kikoff.models.dto.EmailPasswordDto;
import com.itis.kikoff.models.dto.SignUpDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class KikoffApplicationTests {

    EmailPasswordDto user = new EmailPasswordDto("kot@mail.ru", "kot123");

    private Map<String, String> headers;
    private String token = "";

    @BeforeEach
    public void setUp() {
        RestAssured.port = 8080;

    }

    @Test
    public void UtestAuthorization() throws Exception {
        token = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("http://localhost:8080/signIn")
                .jsonPath()
                .get("token");
        headers = new HashMap<>() {
            {
                put("Content-Type", "application/json");
                put("X-TOKEN", token);
            }
        };
        given().headers(headers).with().body(user)
//                .when()
//                .request("POST", "/signIn")
                .then()
                .statusCode(200);
    }
    @Test
    public void createNewUserTest() {
        token = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("http://localhost:8080/signUp")
                .jsonPath()
                .get("token");
        headers = new HashMap<>() {
            {
                put("Content-Type", "application/json");
                put("X-TOKEN", token);
            }
        };
        given().headers(headers).with().body(new SignUpDto("qwerty@mail.ru", "qwerty","Qwerty", "QWERTY", "QWERTYch", LocalDateTime.now()))
                .when()
                .request("POST", "/signUp")
                .then()
                .statusCode(200);
    }

}
