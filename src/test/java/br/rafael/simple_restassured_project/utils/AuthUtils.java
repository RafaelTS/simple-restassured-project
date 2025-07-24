package br.rafael.simple_restassured_project.utils;

import br.rafael.simple_restassured_project.core.BaseTest;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthUtils extends BaseTest {


    public static String getToken() {
        Response userResponse = given()
                .when()
                .get("/user")
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Map<String, Object>> users = userResponse.path("users");

        if (users == null || users.isEmpty()) {
            throw new RuntimeException("Any User were found!");
        }

        String username = userResponse.path("users[0].username");
        String password = userResponse.path("users[0].password");

        Response loginResponse = given()
                .body("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }")
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String token = loginResponse.path("accessToken");

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Access Token Not Founded!");
        }
        return token;
    }
}

