package org.example.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public Courier generic() {
        return new Courier("Jack", "P@ssword", "Sparrow");
    }

    public Courier randow() {
        return new Courier(RandomStringUtils.randomAlphanumeric(7), "P@ssword", "Sparrow");
    }

}
