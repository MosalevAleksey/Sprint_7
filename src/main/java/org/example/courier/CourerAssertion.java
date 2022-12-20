package org.example.courier;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class CourerAssertion {

    public void createdSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("ok", is(true))
        ;

    }

    public int courierGetId(ValidatableResponse response) {
        return response.extract()
                .path("id")
                ;

    }

    public int loggedInSuccessfully(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("id", greaterThan(0))
                .extract()
                .path("id")
                ;
    }

    public String loggedInFailedInsufficientData(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(400)
                .body("message", notNullValue())
                .extract()
                .path("message")
                ;

    }

    public String creationFaildInsufficientData(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(400)
                .body("message", notNullValue())
                .extract()
                .path("message")
                ;
    }

    public String creationFaildLoginRepeated(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(409)
                .body("message", notNullValue())
                .extract()
                .path("message")
                ;
    }


    public String loginFaild(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(404)
                .body("message", notNullValue())
                .extract()
                .path("message")
                ;


    }
}
