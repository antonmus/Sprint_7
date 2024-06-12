import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.Before;
import static config.CourierConfig.HOST;

public class TemplateCourierTests {
    @Before
    public void setUpCourierTests() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(HOST)
                .setContentType(ContentType.JSON)
                .build();
    }
}