import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
public class CreateCourierTests extends TemplateCourierTests{
    private final CourierSteps courierSteps = new CourierSteps();
    Courier courier;
    @Before
    public void setUp() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
    }
    @Test //курьера можно создать
    // в ответе правильный код ответа
    // в ответе правильное тело ответа
    // Была мысль разбить тест на 3 разных, но посчитал, что это лишнее.
    public void possibleCreateTest(){
        courierSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok",is(true))
                .log().ifError();
    }
    @Test //нельзя создать двух одинаковых курьеров
    //если создать пользователя с логином, который уже есть, возвращается ошибка.
    //не стал разбивать тест на 2(кажется излишним)
    public void duplicateCourierTest(){
        courierSteps
                .createCourier(courier);
        courierSteps
                .createCourier(courier)
                .statusCode(409)
                .body("message",is("Этот логин уже используется"));
        //тест падает из-за этой проверки, так как текстовка не соответствует документации
    }
    @After
    public void tearDown() {
        Integer id = courierSteps.loginCourier(courier)
                .extract().body().path("id");
        courier.setId(id);
        courierSteps.deleteCourier(courier);
    }

}