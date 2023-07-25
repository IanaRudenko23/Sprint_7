import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Courier;
import org.example.OnlyLogin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class LoginCourierTest {
    public static String login = "ninja9_0_1";
    public static String password = "1234";
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }
    @After
    public void tearDown() {
        CourierUtils.deleteCourier(login, password);
    }

    @Test
    public void loginCourierSuccess (){//проверили статус успешной авторизации курьера
        Courier courier = new Courier(login,password,"auuu");
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
        courier = new Courier(login,password);
              response =   given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);

    }

    @Test
    public void loginCourierWithoutPassword (){//попытка авторизоваться без пароля
        Courier courier = new Courier(login,password,"auuu");
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
        OnlyLogin onlyLogin = new OnlyLogin(login);
               response =  given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(onlyLogin)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("id", nullValue())
                .and()
                .statusCode(400);
    }

    @Test
    public void loginCourierWithoutExistedLoginAndPassword (){//попытка залогиниться без существующей учетной записи
        Courier courier = new Courier("amandarin",password);
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
        response.then().assertThat().body("id", nullValue())
                .and()
                .statusCode(404);

    }

  @Test
    public void loginCourierWithoutData (){//попытка авторизоваться без логина и пароля
        Courier courier = new Courier(login,password,"auuu");
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
        courier = new Courier();
        response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
        response.then().assertThat().body("id", nullValue())
                .and()
                .statusCode(400);
    }

    @Test
    public void loginCourierWithoutLogin (){//попытка авторизоваться без логина
        Courier courier = new Courier(login,password,"auuu");
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
        courier = new Courier(null,password);
        response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
        response.then().assertThat().body("id", nullValue())
                .and()
                .statusCode(400);
    }
    @Test
    public void loginCourierWithLoginMistake (){//попытка залогиниться с ошибкой в логине
        Courier courier = new Courier(login,password,"auuu");
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
        courier = new Courier("ninja9_0_2",password);
        response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
        response.then().assertThat().body("id", nullValue())
                .and()
                .statusCode(404);
    }

}

