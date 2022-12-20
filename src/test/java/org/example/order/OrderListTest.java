package org.example.order;

import org.junit.Test;


public class OrderListTest {

    private final StepOrder orderclient = new StepOrder();

    @Test
    public void CreateOrder() {

        OrderList ordersList = orderclient.createOrderList();

    }


}



