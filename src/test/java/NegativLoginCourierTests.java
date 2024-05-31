import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.is;
@RunWith(Parameterized.class)
public class NegativLoginCourierTests extends TemplateCourierTests{
    private final CourierSteps courierSteps = new CourierSteps();
    private  Courier courier;
    String testData;
    Integer codError;
    String message;
    public NegativLoginCourierTests(String testData, Integer codError, String message ){
        this.testData = testData;
        this.codError = codError;
        this.message = message;
    }
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"null", 404, "Учетная запись не найдена"},
                {null, 400, "Недостаточно данных для входа"}
        };
    }
   @Before
    public void setUp() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
    }
    @Test //авторизация при кривом логине
    public void noLoginLoginTest(){
        courierSteps
                .createCourier(courier);
        String tempLogin = courier.getLogin();
        courier.setLogin(testData);
        courierSteps
                .loginCourier(courier)
                .statusCode(codError)
                .body("message", is(message));
        courier.setPassword(tempLogin);
    }
  @Test //авторизация при кривом пароле
    public void noPassLoginTest(){
        courierSteps
                .createCourier(courier);
        String tempPass = courier.getPassword();
        courier.setPassword(testData);
        courierSteps
                .loginCourier(courier)
                .statusCode(codError) //падаем по 504, если не вводим пароль(в постмане так же)
                .body("message", is(message));
        courier.setPassword(tempPass);
    }
    @After
    public void tearDown() {
        Integer id = courierSteps.loginCourier(courier)
                .extract().body().path("id");
        courier.setId(id);
        courierSteps.deleteCourier(courier);
    }
}
