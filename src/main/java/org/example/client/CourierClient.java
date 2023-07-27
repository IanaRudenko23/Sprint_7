package org.example.client;

import io.restassured.response.Response;
import org.example.data.Courier;

import static io.restassured.RestAssured.given;

public class CourierClient {

    private static final String BASE_PATH = "/api/v1/courier";

    public static Response createCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(BASE_PATH);
    }

    public static Response loginCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(BASE_PATH + "/login");
    }
}
