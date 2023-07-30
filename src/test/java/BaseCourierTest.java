import org.junit.After;

public class BaseCourierTest extends BaseTest {
    protected String login = "ninja9_0_1";
    protected String password = "1234";

    @After
    public void tearDown() {
        BaseClient.deleteCourier(login, password);
    }
}
