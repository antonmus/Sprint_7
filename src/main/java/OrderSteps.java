import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static config.OrderEnpoint.*;
import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order){
        return given()
                .body(order)
                .when().post(NEWORDER)
                .then();
    }

    @Step("Отмена заказа")
    public ValidatableResponse canselOrder(Order order){
        return  given()
                .body(order)
                .when().post(CANSELORDER)
                .then();
    }
    @Step("Список заказов")
    public ValidatableResponse orderList(Order order){
        return given()
                .body(order)
                .when().get(NEWORDER)
                .then();
    }
}
