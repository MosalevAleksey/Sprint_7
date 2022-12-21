package org.example.order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;


public class OrderListTest {

    private final StepOrder orderclient = new StepOrder();
    @DisplayName("Создание заказа с разными цветами самоката")
    @Test
    public void CreateOrder() {
        OrderList ordersList = orderclient.createOrderList();
    }
}



