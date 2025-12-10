package prolevexman.config;

import io.restassured.RestAssured;

public class Config {

    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String BASE_API = "/api";

    public static void setUp() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_API;
    }
}
