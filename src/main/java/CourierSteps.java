import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static config.CourierEndpoint.*;
import static io.restassured.RestAssured.given;

public class CourierSteps {
    @Step("Создание курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .body(courier)
                .when().post(CREATE)
                .then();
    }

    @Step("Авторизация курьером")
    public ValidatableResponse loginCourier(Courier courier) {
        return given()
                .body(courier)
                .when().post(LOGIN)
                .then();
    }

    @Step("Удаление курьера")
    public ValidatableResponse deleteCourier(Courier courier) {
        return given()
                .body(courier)
                .when().delete(DELETE)
                .then();
    }
}