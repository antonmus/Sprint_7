import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

public class NegativCreateCourierTest extends TemplateCourierTests{
    private final CourierSteps courierSteps = new CourierSteps();
    Courier courier;
    @Before
    public void setUp() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
    }

    @Test //если одного из полей нет, запрос возвращает ошибку(логин)
    public void noLoginCreateTest(){
        courier.setLogin(null);
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message",is("Недостаточно данных для создания учетной записи"));
    }

    @Test //если одного из полей нет, запрос возвращает ошибку(пароль)
    public void noPassCreateTest(){
        courier.setPassword(null);
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message",is("Недостаточно данных для создания учетной записи"));
    }
}
