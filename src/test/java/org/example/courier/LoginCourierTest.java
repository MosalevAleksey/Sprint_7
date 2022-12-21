package org.example.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class LoginCourierTest {
    private final CourierGenerator generator = new CourierGenerator();
    private final StepClient client = new StepClient();
    private final CourerAssertion check = new CourerAssertion();

    private int courierId;

    @After
    public void deletCourer() {
        if (courierId > 0)
            client.delete(courierId);
    }
    @DisplayName("Проверка Лог ин курьера ответт 200 и id !=0")
    @Test
    public void testlogginIsOk() {
        //Проверка Лог ин курьера ответт 200 и id !=0
        var courier = generator.randow();//создаем  объект с полями курьера
        ValidatableResponse creatResponse = client.create(courier);//передаем данные курьера на API создаём курьера на сервере

        Credentials creds = Credentials.from(courier);//создаем  объект с полями  от созданного курьера
        ValidatableResponse loginResponse = client.login(creds);//передаем данные объекта на API для проверки ID
        courierId = check.loggedInSuccessfully(loginResponse);// проверяем что ответ 200 ок и забераем  id чтобы удалить курьера
        Assert.assertThat(courierId, greaterThan(0));
    }
    @DisplayName("Проверка Лог ин курьера без пароля")
    @Test
    public void testlogginFaildWithoutPassword() {
        //Проверка Лог ин курьера без пароля
        var courier = generator.randow();//создаем  объект с полями курьера
        ValidatableResponse creatResponse = client.create(courier);//передаем данные курьера на API создаём курьера на сервере
        Credentials creds = Credentials.from(courier);//создаем  объект с полями  от созданного курьера
        ValidatableResponse loginResponse = client.login(creds);//передаем данные объекта на API для проверки ID
        courierId = check.loggedInSuccessfully(loginResponse);// проверяем что ответ 200 ок и забераем  id чтобы удалить курьера

        creds.setPassword("");//устанавливае пароль ""
        loginResponse = client.login(creds);//передаем данные объекта на API для проверки ошибки
        String message = check.loggedInFailedInsufficientData(loginResponse);// проверяем что ответ 400 "message":  "Недостаточно данных для входа"
        assertEquals("Недостаточно данных для входа", message);
    }
    @DisplayName("Проверка Лог ин курьера без логина")
    @Test
    public void testlogginFaildWithoutlogin() {
        //Проверка Лог ин курьера без логина
        var courier = generator.randow();//создаем  объект с полями курьера
        ValidatableResponse creatResponse = client.create(courier);//передаем данные курьера на API создаём курьера на сервере
        Credentials creds = Credentials.from(courier);//создаем  объект с полями  от созданного курьера
        ValidatableResponse loginResponse = client.login(creds);//передаем данные объекта на API для проверки ID на сервер
        courierId = check.loggedInSuccessfully(loginResponse);// проверяем что ответ 200 ок и забераем  id чтобы удалить курьера

        creds.setLogin(null);//устанавливае логина null
        loginResponse = client.login(creds);//передаем данные объекта на API для проверки ошибки
        String message = check.loggedInFailedInsufficientData(loginResponse);// проверяем что ответ 400 "message":  "Недостаточно данных для входа"
        assertEquals("Недостаточно данных для входа", message);
    }
    @DisplayName("Проверка если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;")
    @Test
    public void loginFails() {
        //Проверка если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
        var courier = generator.randow();//создаем  объект с полями курьера  и НЕ ПЕРЕДПАЁМ его по API
        ValidatableResponse creatResponse = client.create(courier);//передаем данные курьера на API создаём курьера на сервере
        Credentials creds = Credentials.from(courier);//создаем  объект с полями  от созданного курьера
        ValidatableResponse loginResponse = client.login(creds);//передаем данные объекта на API для проверки ID на сервер
        courierId = check.loggedInSuccessfully(loginResponse);// проверяем что ответ 200 ок и забераем  id чтобы удалить курьера

        creds.setLogin(RandomStringUtils.randomAlphanumeric(5));//устанавливае рандомный логин 5 символов
        creds.setPassword(RandomStringUtils.randomAlphanumeric(5));//устанавливае рандомный пароль 5 символов

        loginResponse = client.login(creds);//передаем данные объекта на API для проверки ошибки
        String message = check.loginFaild(loginResponse);// проверяем что ответ 409 "message":  "Учетная запись не найдена"
        assertEquals("Учетная запись не найдена", message);
    }
}


