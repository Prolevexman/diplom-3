package prolevexman.utils;

import com.github.javafaker.Faker;
import prolevexman.model.User;

public class UserGenerator {

    private static final Faker faker = new Faker();

    public static User randomUserWithPassLength(int passwordLength) {
        return new User(
                faker.internet().emailAddress(),
                faker.regexify("[A-Za-z0-9]{" +passwordLength + "}"),
                faker.name().firstName()
        );
    }
}
