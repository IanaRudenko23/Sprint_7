import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.data.Courier;
import org.example.data.OnlyLogin;
import org.junit.Test;

import static org.example.client.CourierClient.createCourier;
import static org.example.client.CourierClient.loginCourier;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class LoginCourierTest extends BaseCourierTest {

    @Test
    @DisplayName("Успешная авторизация зарегистрированного курьера")
    public void loginCourierSuccess() {//проверили статус успешной авторизации курьера
        Courier courier = new Courier(login, password, "auuu");
        Response response = createCourier(courier);
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
        courier = new Courier(login, password);
        response = loginCourier(courier);
        response.then().assertThat().statusCode(200)
                .and()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Невозмонжо авторизоваться без пароля")
    public void loginCourierWithoutPassword() {//попытка авторизоваться без пароля
        Courier courier = new Courier(login, password, "auuu");
        Response response = createCourier(courier);
        response.then().assertThat().statusCode(201)
                .and()
                .body("ok", equalTo(true));
        OnlyLogin onlyLogin = new OnlyLogin(login);
        response = loginCourier(courier);
        response.then().assertThat().statusCode(400)
                .and()
                .body("id", nullValue());
    }

    @Test
    @DisplayName("Невозможно авторизоваться без создания учетной записи")
    public void loginCourierWithoutExistedLoginAndPassword() {//попытка залогиниться без существующей учетной записи
        Courier courier = new Courier("amandarin", password);
        Response response = loginCourier(courier);
        response.then().assertThat().statusCode(404)
                .and()
                .body("id", nullValue());
    }

    @Test
    @DisplayName("Невозможно авторизоваться без логина и пароля")
    public void loginCourierWithoutData() {//попытка авторизоваться без логина и пароля
        Courier courier = new Courier(login, password, "auuu");
        Response response = createCourier(courier);
        response.then().assertThat().statusCode(201)
                .and()
                .body("ok", equalTo(true));
        courier = new Courier();
        response = loginCourier(courier);
        response.then().assertThat().statusCode(400)
                .and()
                .body("id", nullValue());
    }

    @Test
    @DisplayName("Невозможно авторизоваться без логина")
    public void loginCourierWithoutLogin() {//попытка авторизоваться без логина
        Courier courier = new Courier(login, password, "auuu");
        Response response = createCourier(courier);
        response.then().assertThat().statusCode(201)
                .and()
                .body("ok", equalTo(true));
        courier = new Courier(null, password);
        response = loginCourier(courier);
        response.then().assertThat().statusCode(400)
                .and()
                .body("id", nullValue());
    }

    @Test
    @DisplayName("Невозможно авторизоваться если в логине допущена ошибка")
    public void loginCourierWithLoginMistake() {//попытка залогиниться с ошибкой в логине
        Courier courier = new Courier(login, password, "auuu");
        Response response = createCourier(courier);
        response.then().assertThat().statusCode(201)
                .and()
                .body("ok", equalTo(true));
        courier = new Courier("ninja9_0_2", password);
        response = loginCourier(courier);
        response.then().assertThat().statusCode(404)
                .and()
                .body("id", nullValue());
    }

}

