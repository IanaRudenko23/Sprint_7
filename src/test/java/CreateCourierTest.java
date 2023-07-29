import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.data.Courier;
import org.junit.Test;

import static org.example.client.CourierClient.createCourier;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class CreateCourierTest extends BaseCourierTest {

    @Test
    @DisplayName("Успешно создали нового курьера")
    public void createNewCourier() {//проверили успешный кейс создания курьера
        Courier courier = new Courier(login, password, "saske");
        Response response = createCourier(courier);
        response.then().assertThat().statusCode(201).and().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Невозможно создать курьера без ввода обязательных полей")
    public void createCourierWithoutData() {//проверили что нельзя создать курьера без обязательных полей
        Courier courier = new Courier();
        Response response = createCourier(courier);
        response.then().assertThat().statusCode(400).and().body("data.name", nullValue());
    }

    @Test
    @DisplayName("Невозможно создать двух одинаковых курьеров")
    public void createCourierWithLoginExisted() {//проверили что нельзя создать двух одинаковых курьеров
        Courier courier = new Courier(login, password, "auuuu");
        Response response = createCourier(courier);
        response.then().assertThat().statusCode(201).and().body("ok", equalTo(true));
        response = createCourier(courier);
        response.then().assertThat().statusCode(409);
    }
}
