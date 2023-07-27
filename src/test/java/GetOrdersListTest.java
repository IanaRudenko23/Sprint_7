import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.client.OrderClient;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrdersListTest extends BaseTest {
    @Test
    @DisplayName("Получение списка заказов")
    public void getOrdersList() {
        Response response = OrderClient.getOrdersList();
        response.then().assertThat().body("orders", notNullValue())
                .and()
                .statusCode(200);

    }
}