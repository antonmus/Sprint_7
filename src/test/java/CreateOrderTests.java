import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.notNullValue;
@RunWith(Parameterized.class)
public class CreateOrderTests extends  TemplateCourierTests{
    private final OrderSteps orderSteps = new OrderSteps();
    private Order order;
    String[] arrayColor;
    public CreateOrderTests(String newColor){
        if(newColor == null){
            this.arrayColor = null;
        }
        else {
            this.arrayColor = newColor.split(" ");
        }
    }
@Parameterized.Parameters
public static Object[][] data() {
    return new Object[][] {
            {"BLACK"},
            {"GREY"},
            {"BLACK GREY"},
            {""},
            {null}
    };
}
@Before
public void setOrder(){
    order = new Order();
    order.setFirstName("firstName");
    order.setLastname("lastName");
    order.setAddress("Konoha, 142 apt.");
    order.setMetroStation(4);
    order.setPhone("+7 800 355 35 35");
    order.setRentTime(5);
    order.setDeliveryDate("2020-06-06");
    order.setComment("new comment from new aqa");
    order.setColor(arrayColor);
}
@Test
public void setColorCreateOrdersTest(){
        orderSteps
                .createOrder(order)
                .statusCode(201)
                .body("track", notNullValue())
            .log().ifError();
}
@After
public void tearDown() {
    Integer track = orderSteps.canselOrder(order)
            .extract().body().path("track");
    order.setTrack(track);
    orderSteps.canselOrder(order);
}
}
