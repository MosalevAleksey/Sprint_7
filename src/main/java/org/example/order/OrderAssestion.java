package org.example.order;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class OrderAssestion {

    public int orderCreateSuccessfully(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(201)
                .body("track", greaterThan(0))
                .extract()
                .path("track")
                ;


    }


}
