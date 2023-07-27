package org.example.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderClient {
    public static Response getOrdersList() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get("/api/v1/orders");
    }
}
