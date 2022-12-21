package org.example.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class CourerAssertion {
    @Step("Создание курьера")
    public void createdSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("ok", is(true));
    }
    @Step("Полечк Id созданного курьера")
    public int courierGetId(ValidatableResponse response) {
        return response.extract()
                .path("id");
    }
    @Step("Log in курьера")
    public int loggedInSuccessfully(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("id", greaterThan(0))
                .extract()
                .path("id");
    }
    @Step("Ошибка Log in курьера c незаполнеными полями")
    public String loggedInFailedInsufficientData(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(400)
                .body("message", notNullValue())
                .extract()
                .path("message");
    }
    @Step("Ошибка создание курьера c незаполнеными полями")
    public String creationFaildInsufficientData(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(400)
                .body("message", notNullValue())
                .extract()
                .path("message");
    }
    @Step("Ошибка создание двух курьеров c одинаковыми полями")
    public String creationFaildLoginRepeated(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(409)
                .body("message", notNullValue())
                .extract()
                .path("message");
    }
    @Step("Ошибка login")
    public String loginFaild(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(404)
                .body("message", notNullValue())
                .extract()
                .path("message") ;
    }
}
