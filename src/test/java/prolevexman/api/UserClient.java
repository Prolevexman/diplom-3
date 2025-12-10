package prolevexman.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import prolevexman.model.User;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("Отправка POST запроса на /auth/register")
    public Response createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/auth/register");
    }

    @Step("Отправка DELETE запроса на /auth/user")
    public Response deleteUser(String authToken) {
        if (authToken == null || authToken.isEmpty()) {
            throw new IllegalArgumentException("authToken не может быть пустым");
        }
        return given()
                .header("Authorization", authToken)
                .when()
                .delete("/auth/user");
    }

    @Step("Отправка POST запроса на /auth/login")
    public Response loginUser(User loginUser) {
        return given()
                .contentType(ContentType.JSON)
                .body(loginUser)
                .when()
                .post("/auth/login");
    }
}
