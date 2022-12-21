package org.example.order;


public class OrderGenerator {


    public Order genericBlackGrey() {
        return new Order("Naruto", "Uchiha", "Konoha, 142 apt.", "4", "+7 800 355 35 35", 3, "2020-06-06", "Saske, come back to Konoha", new String[]{"BLACK", "GREY"});
    }

    public Order genericBlack() {
        return new Order("Naruto", "Uchiha", "Konoha, 142 apt.", "4", "+7 800 355 35 35", 3, "2020-06-06", "Saske, come back to Konoha", new String[]{"BLACK"});
    }

    public Order genericGrey() {
        return new Order("Naruto", "Uchiha", "Konoha, 142 apt.", "4", "+7 800 355 35 35", 3, "2020-06-06", "Saske, come back to Konoha", new String[]{"GREY"});
    }

    public Order genericNoColor() {
        return new Order("Naruto", "Uchiha", "Konoha, 142 apt.", "4", "+7 800 355 35 35", 3, "2020-06-06", "Saske, come back to Konoha");
    }
}



