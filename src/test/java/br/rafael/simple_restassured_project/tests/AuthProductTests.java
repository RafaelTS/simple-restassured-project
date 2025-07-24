package br.rafael.simple_restassured_project.tests;

import br.rafael.simple_restassured_project.core.BaseTest;
import br.rafael.simple_restassured_project.utils.AuthUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthProductTests extends BaseTest {

    public static final String URL_AUTH_PRODUCTS = "/auth/products";
    public static String accessToken;

    @Test
    public void shouldReturnProductsWithSucess() {

        accessToken = AuthUtils.getToken();

        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(URL_AUTH_PRODUCTS)
                .then()
                .statusCode(200);

    }

    @Test
    public void shouldNotReturnProductsWithoutToken() {

        given()
                .when()
                .get(URL_AUTH_PRODUCTS)
                .then()
                .statusCode(401)
                .body("message", Matchers.is("Access Token is required"))        ;
    }

}
