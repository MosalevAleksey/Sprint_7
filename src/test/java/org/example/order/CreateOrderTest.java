package org.example.order;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class CreateOrderTest {

    private static final OrderGenerator orderGenerator = new OrderGenerator();
    private final StepOrder orderclient = new StepOrder();
    private final OrderAssestion check = new OrderAssestion();
    private int orderTrack;

    private final Order order;

    public CreateOrderTest(Order order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static Object[][] allColor() {
        return new Object[][]{
                {orderGenerator.genericBlack()},  //создаем объект с полями заказ черный
                {orderGenerator.genericGrey()},   //создаем объект с полями заказ серый
                {orderGenerator.genericBlackGrey()},//создаем объект с полями заказ черный и серый
                {orderGenerator.genericNoColor()},//создаем объект без цветов
        };
    }

    @After
    public void deletCourer() {
        if (orderTrack > 0)
            orderclient.cancel(orderTrack);
    }

    @Test
    public void orderCreateOK() {
        //Проверка создание заказа ответт 201 и track: 124124
        //
        ValidatableResponse creatResponse = orderclient.create(order);//передаем данные курьера на API создаём курьера
        int orderTrack = check.orderCreateSuccessfully(creatResponse);//Проверяем ответ 201  и дастаем track для дальнейшего удаления
        assert orderTrack != 0;
    }
}
