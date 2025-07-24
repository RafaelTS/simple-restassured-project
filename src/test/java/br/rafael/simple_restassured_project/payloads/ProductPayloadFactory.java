package br.rafael.simple_restassured_project.payloads;


import br.rafael.simple_restassured_project.dto.Product;

public class ProductPayloadFactory {

    public static Product createCompleteProduct() {
        Product product = new Product();

        product.setTitle("Essence 2 Mascara Lash Parincess");
        product.setDescription("The Essence Mascara Lash Princess is a popular mascara kn.");
        product.setPrice(9.99);
        product.setDiscountPercentage(7.17);
        product.setRating(4.94);
        product.setStock(5);
        product.setBrand("Essence");
        product.setCategory("Phones");
        product.setThumbnail("https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/thumbnail.png");

        return product;
    }
}
