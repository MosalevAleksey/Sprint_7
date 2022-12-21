package org.example.order;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class StepOrder {


    protected final String BASE_URI = "http://qa-scooter.praktikum-services.ru";
    protected final String ROOT = "/api/v1/orders";

    public ValidatableResponse create(Order order) {

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();

    }

    public ValidatableResponse cancel(int track) {

        String json = String.format("{\"track\": \"%d\"}", track);
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(json)
                .when()
                .post(ROOT + "/cancel")
                .then().log().all();

    }
    public OrderList createOrderList() {
        return
                given()
                        .baseUri(BASE_URI)
                        .when()
                        .get(ROOT)
                        .body()
                        .as(OrderList.class);
    }

}
