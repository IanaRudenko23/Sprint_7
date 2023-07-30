import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.client.OrderClient;
import org.example.data.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderParametrizedTest extends BaseTest {
    Order order = new Order();

    public CreateOrderParametrizedTest(String firstName, String lastName, String address, int metroStation,
                                       String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setAddress(address);
        order.setMetroStation(metroStation);
        order.setPhone(phone);
        order.setRentTime(rentTime);
        order.setDeliveryDate(deliveryDate);
        order.setComment(comment);
        order.setColor(color);
    }

    @Parameterized.Parameters(name = "Создание заказа, цвет {8}")
    public static Object[] createOrderTest() {
        return new Object[][]{
                {"Иван", "Иванов", "ул. Новая, д.1", 1, "+7 978 000 90 90", 5, "2020-06-06", "Test test", List.of("BLACK")},
                {"Иван", "Иванов", "ул. Новая, д.1", 1, "+7 978 000 90 90", 5, "2020-06-06", "Test test", List.of("GREY")},
                {"Иван", "Иванов", "ул. Новая, д.1", 1, "+7 978 000 90 90", 5, "2020-06-06", "Test test", List.of("BLACK", "GREY")},
                {"Иван", "Иванов", "ул. Новая, д.1", 1, "+7 978 000 90 90", 5, "2020-06-06", "Test test", null},
        };
    }

    @Test
    @DisplayName("Создание заказа самоката")
    public void createOrder() {
        Response response = OrderClient.createOrder(order);
        response.then().assertThat().statusCode(201).and().body("track", notNullValue());
    }


}
