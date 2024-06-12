import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;
public class OrderListTests extends TemplateCourierTests{
    private final OrderSteps orderSteps = new OrderSteps();
    private Order order;
    @Before
    public void setOrder(){//создаем заказ, на случа, если в базе пусто

        order = new Order();
        order.setFirstName("firstName");
        order.setLastname("lastName");
        order.setAddress("Konoha, 142 apt.");
        order.setMetroStation(4);
        order.setPhone("+7 800 355 35 35");
        order.setRentTime(5);
        order.setDeliveryDate("2020-06-06");
        order.setComment("new comment from new aqa");

        orderSteps
                .createOrder(order);
    }
    @Test
    public void returnOrderListTest(){

        orderSteps
                .orderList(order)
                .statusCode(200)
                .body("orders", notNullValue())
                .log().all();
    }
    @Before
    public void tearDown() {
        Integer track = orderSteps.canselOrder(order)
                .extract().body().path("track");
        order.setTrack(track);
        orderSteps.canselOrder(order);
    }
}
