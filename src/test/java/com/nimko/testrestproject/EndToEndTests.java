package com.nimko.testrestproject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EndToEndTests {

    @LocalServerPort
    private int port;

    @Value("${test.user}")
    private String testUser;

    @Value("${test.products}")
    private String testProducts;
    private String jwtToken;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    @Order(1)
    void testAddUserAPI() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(testUser)
                .when()
                .post("/user/add")
                .then()
                .statusCode(201);
    }

    @Test
    @Order(2)
    void testAuthenticateUserAPI() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(testUser)
                .when()
                .post("/user/authenticate")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract().response();

        jwtToken = response.path("token");
    }

    @Test
    @Order(3)
    void testUnAuthenticateUserAPI() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{ \"username\": \"user\", \"password\": \"1\"}")
                .when()
                .post("/user/authenticate")
                .then()
                .statusCode(401);
    }

    @Test
    @Order(4)
    void testAddProductAPI() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+jwtToken)
                .body(testProducts)
                .when()
                .post("/products/add")
                .then()
                .statusCode(201);
    }


    @Test
    @Order(5)
    void testGetAllProductAPI() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+jwtToken)
                .when()
                .get("/products/all")
                .then()
                .statusCode(200)
                .extract().response();
        var products = response.getBody().asString();
        assertEquals(testProducts,products);
    }
}