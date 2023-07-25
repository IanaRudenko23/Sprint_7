import io.restassured.response.Response;
import org.example.Courier;
import org.example.Order;

import static io.restassured.RestAssured.given;

public class CourierAndOrderUtils {
    public static void deleteCourier(String login, String password) {//метод удалить курьера после создания
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(new Courier(login, password))
                .when()
                .post("/api/v1/courier/login");
        Integer id = response.body().jsonPath().get("id");

        if (id != null) {
            given().body(new Courier(login, password)).when().delete("/api/v1/courier/" + id);
        }
    }

    public static void cancelOrder (){//метод отменить заказ
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(new Order())
                .when()
                .post("/api/v1/orders");
        Integer track = response.body().jsonPath().get("track");

        if (track != null) {
            given().body(new Order()).when().put("/api/v1/orders/cancel" + track);
        }

    }
}
