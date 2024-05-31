import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTests extends TemplateCourierTests {
        private final CourierSteps courierSteps = new CourierSteps();
        Courier courier;

        @Before
        public void setUp() {
            courier = new Courier();
            courier.setLogin(RandomStringUtils.randomAlphabetic(10));
            courier.setPassword(RandomStringUtils.randomAlphabetic(10));
        }
        @Test //курьер может авторизоваться
        //успешный запрос возвращает id
        public void loginTest(){
            courierSteps
                    .createCourier(courier);
            courierSteps
                    .loginCourier(courier)
                    .statusCode(200)
                    .body("id", notNullValue())
                    .log().ifError();
        }

}
