package org.example.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.data.Order;

import static io.restassured.RestAssured.given;

public class OrderClient {
    @Step("Метод вывода списка заказов")
    public static Response getOrdersList() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get("/api/v1/orders");
    }

    @Step("Метод создания заказа")
    public static Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders");
    }
}
