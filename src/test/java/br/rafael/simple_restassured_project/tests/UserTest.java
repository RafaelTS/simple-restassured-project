package br.rafael.simple_restassured_project.tests;

import br.rafael.simple_restassured_project.core.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserTest extends BaseTest {

    public static final String APP_USER = "/user";

    @Test
    public void shouldReturnListOfUsers() {
        given()
                .when()
                .get(APP_USER)
                .then()
                .statusCode(200)
                .body("total", is(208))
                .body("users[0].username", is("emilys"))
                .body("users.username", everyItem(notNullValue()))
                .body("users[0].image", startsWith("https://dummyjson.com/icon/"))
                .body("users[0].height", equalTo(193.24f))
                .body("users[0].hair.color", equalTo("Brown"));
    }

    @Test
    public void shouldContainExpectedUsername() {
        List<String> usernames =
                given()
                        .when()
                        .get("/users")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("users.username");

        assertThat(usernames, hasItem("emilys"));
    }

    @Test
    public void shouldNotReturnAnEmptyListOfUsers() {
        given()
                .when()
                .get(APP_USER)
                .then()
                .statusCode(200)
                .body("users", hasSize(greaterThan(0)))
                .body("users", not(empty()));

    }
}
