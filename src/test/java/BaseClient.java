import io.restassured.response.Response;
import org.example.data.Courier;

import static io.restassured.RestAssured.given;

public class BaseClient {

    public static final String BASE_URI = "http://qa-scooter.praktikum-services.ru/";

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

}

