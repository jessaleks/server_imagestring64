package com.aleksanderjess;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/api/image?imageUrl=https%3A%2F%2Fik.imagekit.io%2F1dljx5jdv9do%2Fblog%2Fsuplementy.jpg&w=1920&q=75")
                .then()
                .statusCode(200);

    }

}
