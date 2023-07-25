import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class CreateCourierTest {

    String login = "ninja9_0_1";
    String password = "1234";

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void tearDown() {
        CourierUtils.deleteCourier(login, password);
    }

    @Test
    public void createNewCourier() {//проверили успешный кейс создания курьера
        Courier courier = new Courier(login, password, "saske");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
    }

    @Test
    public void createCourierWithoutData() {//проверили что нельзя создать курьера без обязательных полей
        Courier courier = new Courier();
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("data.name", nullValue())
                .and()
                .statusCode(400);
    }

    @Test
    public void createCourierWithLoginExisted() {//проверили что нельзя создать двух одинаковых курьеров
        Courier courier = new Courier(login, password, "auuuu");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
        response = given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat()
                .statusCode(409);
    }
}
