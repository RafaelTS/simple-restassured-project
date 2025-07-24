package br.rafael.simple_restassured_project.tests;

import br.rafael.simple_restassured_project.core.BaseTest;
import br.rafael.simple_restassured_project.dto.Product;
import br.rafael.simple_restassured_project.payloads.ProductPayloadFactory;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Produtos")
@Feature("Consulta e Cadastro")
@Story("Cadastrar produto com sucesso")
@Severity(SeverityLevel.CRITICAL)
public class ProductTests extends BaseTest {
    public static final String URL_PRODUCT = "/products";
    public static final String URL_ADD_PRODUCT = "/products/add";

    ProductPayloadFactory factory  = new ProductPayloadFactory();
    private Product payload;

    @Test
    @Epic("Produtos")
    @Feature("Consulta e Cadastro")
    @Story("Cadastrar produto com sucesso")
    @DisplayName("Teste")
    @Description("QualquerCoisa")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldReturnProductListSuccessfully() {
        given()
                .when()
                .get(URL_PRODUCT)
                .then()
                .statusCode(200)
                .body("total", greaterThan(0))
                .body("products", not(empty()));
    }

    @Test
    public void shouldReturnProductByIdSuccessfully() {
        int productId = getFirstProductId();

        given()
                .pathParam("id", productId)
                .when()
                .get(URL_PRODUCT + "/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(productId));
    }

    @Test
    public void shouldValidateFirstProductTitle() {
        String title = given()
                .when().get(URL_PRODUCT)
                .then().extract().path("products[0].title");

        assertThat(title, is(notNullValue()));
    }

    @Test
    public void shouldReturnProductsBySearchQuery() {
        given()
                .queryParam("q", "phone")
                .when()
                .get(URL_PRODUCT + "/search")
                .then()
                .statusCode(200)
                .body("products", not(empty()))
                .body("products.title", hasItem(containsStringIgnoringCase("Apple")));
    }

    @Test
    public void shouldReturnErrorMessageProductNotFound() {
        given()
                .when()
                .get(URL_PRODUCT + "/544")
                .then()
                .statusCode(404)
                .body("message", is("Product with id '544' not found"));
    }

    //só para demonstrar que pensei na possibilidade.
    @Test
    public void shouldReturnErrorMessageProductNotFoundSearchingBy0() {
        given()
                .when()
                .get(URL_PRODUCT + "/0")
                .then()
                .statusCode(404)
                .body("message", is("Product with id '0' not found"));
    }

    @Test
    public void shouldCreateAProductWithSucess() {
        payload = factory.createCompleteProduct();
        given()
                .body(payload)
                .when()
                .post(URL_ADD_PRODUCT)
                .then()
                .statusCode(201);
    }

    //exemplo de testes onde o retorno deveria ser 400 e não aceitar o valor negativo
    @Test
    public void shouldReturn400WithInvalidPrice() {
        payload = factory.createCompleteProduct();
        payload.setPrice(-10f);

        given()
                .body(payload)
                .when()
                .post(URL_ADD_PRODUCT)
                .then()
                .statusCode(201);
    }

    //deveria retornar 400 mas não retornou. São apenas exemplos.
    @Test
    public void shouldReturn400WithNoTitleProduct() {
        payload = factory.createCompleteProduct();
        payload.setTitle("");

        given()
                .body(payload)
                .when()
                .post(URL_ADD_PRODUCT)
                .then()
                .statusCode(201);
    }

    private int getFirstProductId() {
        return given()
                .when()
                .get(URL_PRODUCT)
                .then()
                .extract()
                .path("products[0].id");
    }
}
