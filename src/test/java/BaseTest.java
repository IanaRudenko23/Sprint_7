import io.restassured.RestAssured;
import org.junit.Before;

public class BaseTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = BaseClient.BASE_URI;
    }
}
