package com.itis.kikoff;

import com.itis.kikoff.models.dto.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class KikoffApplicationTests {

    private String body = "{  \"password\": \"12345\",  \"email\": \"dadada@mail.ru\"}";
    private String token = "";
    private Map<String, String> headers;

    @BeforeEach
    public void setup() {

        token = given()
                .contentType(ContentType.JSON)
                .body(body)
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
    }

    @AfterEach
    public void logout() {
        given().headers(headers)
                .when().request("GET", "/logout").then().statusCode(200);
    }

    @Test
    public void basketControllerGetBasketTest() {
        given().headers(headers).when().request("GET", "/basket/get/4").then().statusCode(200); // 4 ?
    }

    @Test
    public void basketControllerCreateBasketTest() {
        given().headers(headers).with().body(new BasketDto(0L, new ArrayList<>()))
                .when()
                .request("POST", "/basket/createBasket")
                .then()
                .statusCode(200);
    }

    @Test
    public void basketControllerDeleteBasketTest() {
        given().headers(headers).with().body(new BasketIdDto(5L))
                .when()
                .request("POST", "/basket/deleteBasket")
                .then()
                .statusCode(200);
    }

    @Test
    public void categoryControllerGetAllTest() {
        given().headers(headers).when().request("GET", "/categories/getAll").then().statusCode(200);
    }

    @Test
    public void categoryControllerGetProductsTest() {
        given().headers(headers).when().request("GET", "/categories/get/0").then().statusCode(200); // 0 ?
    }

    @Test
    public void categoryControllerCreateTest() {
        given().headers(headers).with().body(new CategoryDto("Напитки"))
                .when()
                .request("POST", "/categories/create")
                .then()
                .statusCode(200);
    }

    @Test
    public void deliveryControllerSaveAddressTest() {
        given().headers(headers).with().body(new AddressDto("Восточная", "14В", 24))
                .when()
                .request("POST", "/delivery")
                .then()
                .statusCode(200);
    }

    @Test
    public void productAndBasketControllerAddToBasketTest() {
        given().headers(headers).with().body(new BasketProductDto(4L, 3L))
                .when()
                .request("POST", "/basket/product/addToBasket")
                .then()
                .statusCode(200);
    }

    @Test
    public void productAndBasketControllerDeleteFromBasketTest() {
        given().headers(headers).with().body(new BasketProductDto(4L, 0L))
                .when()
                .request("POST", "/basket/product/deleteFromBasket")
                .then()
                .statusCode(200);
    }

    @Test
    public void productAndBasketControllerGetProductsListFromBasketTest() {
        given().headers(headers).when().request("GET", "/basket/product/getProducts/4").then().statusCode(200); // 4 ?
    }

    @Test
    public void productControllerGetAllTest() {
        given().headers(headers).when().request("GET", "/product/getAll").then().statusCode(200);
    }

    @Test
    public void productControllerGetProduct() {
        given().headers(headers).when().request("GET", "/product/getProducts/0").then().statusCode(200); // 0 ?
    }

    @Test
    public void productControllerAddTest() {
        given().headers(headers).with().body(new ProductDto(10, 350, "Сыр", 1L))
                .when()
                .request("POST", "/product/add")
                .then()
                .statusCode(200);
    }

    @Test
    public void productControllerDeleteTest() {
        given().headers(headers).with().body(new ProductIdDto(1L))
                .when()
                .request("POST", "product/delete")
                .then()
                .statusCode(200);
    }

    @Test
    public void purchasePreparationControllerCreateBillTest() {
        given().headers(headers).with().body(new BillDto(2L, 1L))
                .when()
                .request("POST", "/bill/create")
                .then()
                .statusCode(200);
    }

    @Test
    public void purchasePreparationControllerFillBillTest() {
        ArrayList<ProductIdDto> list = new ArrayList<>();
        list.add(ProductIdDto.builder()
                .productId(0L)
                .build());
        given().headers(headers).with().body(new BillProductDto(3L, list))
                .when()
                .request("POST", "/bill/fill")
                .then()
                .statusCode(200);
    }

    @Test
    public void purchaseControllerSuccessBillTest() {
        given().headers(headers).with().body(new BillIdDto(3L))
                .when()
                .request("POST", "/bill/success")
                .then()
                .statusCode(200);
    }

    @Test
    void contextLoads() {
    }

}
