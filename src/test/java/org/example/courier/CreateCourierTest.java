package org.example.courier;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateCourierTest {
    private final CourierGenerator generator = new CourierGenerator();
    private final StepClient client = new StepClient();
    private final CourerAssertion check = new CourerAssertion();

    private int courierId;

    @After
    public void deletCourer() {
        if (courierId > 0)
            client.delete(courierId);
    }

    @Test
    public void courierCreataOK() {
        //Проверка создание курьера ответт 201 и id !=0
        var courier = generator.randow();//создаем объект с полями курьера
        ValidatableResponse creatResponse = client.create(courier);//передаем данные курьера на API создаём курьера
        check.createdSuccessfully(creatResponse);//Проверяем ответ 201 ок

        Credentials creds = Credentials.from(courier);//создаем объект с полями курьера
        ValidatableResponse loginResponse = client.login(creds);//передаем данные объекта на API для получения ID
        courierId = check.courierGetId(loginResponse);// получаем id для передачи в after
    }

    @Test
    public void canNotCreateIdenticalCourier() {
        //Проверка невозможности создания двух одинаковых курьеров
        var courier = generator.randow();//создаем объект с полями курьера
        ValidatableResponse creatResponse = client.create(courier);//передаем данные курьера на API создаём курьера
        check.createdSuccessfully(creatResponse);//получаем ответ 201 ок
        ValidatableResponse creatResponse1 = client.create(courier);//передаем данные курьера на API
        String message = check.creationFaildLoginRepeated(creatResponse1);//получаем ответ 409  message "Этот логин уже используется"
        assertEquals("Этот логин уже используется. Попробуйте другой.", message); //проверяем что  message это "Этот логин уже используется. Попробуйте другой.".

        Credentials creds = Credentials.from(courier);//создаем объект с полями курьера
        ValidatableResponse loginResponse = client.login(creds);//передаем данные объекта на API для получения ID
        courierId = check.courierGetId(loginResponse);// получаем id для передачи в after
    }

    @Test
    public void canNotCreateCourierWithoutPassword() {
        //Проверка невозможности создания  курьера без поля password
        var courier = generator.randow();// создали курьера
        courier.setPassword(null);// убираем поле password
        ValidatableResponse creatResponse = client.create(courier);//дернуди ручку  с курьером без password

        String message = check.creationFaildInsufficientData(creatResponse);//получаем ответ 400  message "Недостаточно данных для создания учетной записи"
        assertEquals("Недостаточно данных для создания учетной записи", message); //проверяем что  message это "Недостаточно данных для создания учетной записи".

    }

    @Test
    public void canNotCreateCourierWithoutLogin() {
        //Проверка невозможности создания  курьера без поля password
        var courier = generator.randow();// создали курьера
        courier.setLogin(null);// убираем поле login
        ValidatableResponse creatResponse = client.create(courier);//дернуди ручку  с курьером без login

        String message = check.creationFaildInsufficientData(creatResponse);//получаем ответ 400  message "Недостаточно данных для создания учетной записи"
        assertEquals("Недостаточно данных для создания учетной записи", message); //проверяем что  message это "Недостаточно данных для создания учетной записи".

    }

}