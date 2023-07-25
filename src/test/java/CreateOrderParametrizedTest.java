import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderParametrizedTest {
    Order order = new Order();
    public CreateOrderParametrizedTest(String firstName,String lastName,String address, int metroStation,
                                       String phone,int rentTime,String deliveryDate, String comment,List<String> color){
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
    @Parameterized.Parameters
    public static Object[] createOrderTest() {
        return new Object[][] {
                { "Иван", "Иванов", "ул. Новая, д.1",1,"+7 978 000 90 90", 5,"2020-06-06","Test test",List.of("BLACK")},
                { "Иван", "Иванов", "ул. Новая, д.1",1,"+7 978 000 90 90", 5,"2020-06-06","Test test",List.of("GREY")},
                { "Иван", "Иванов", "ул. Новая, д.1",1,"+7 978 000 90 90", 5,"2020-06-06","Test test",List.of("BLACK","GREY")},
                { "Иван", "Иванов", "ул. Новая, д.1",1,"+7 978 000 90 90", 5,"2020-06-06","Test test",null},
        };
    }


    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void createOrder(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(order)
                        .when()
                        .post("/api/v1/orders");
        response.then().assertThat().body("track", notNullValue())
                .and()
                .statusCode(201);
    }




}
