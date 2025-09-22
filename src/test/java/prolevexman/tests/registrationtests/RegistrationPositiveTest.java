package prolevexman.tests.registrationtests;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import prolevexman.annotations.Browser;
import prolevexman.api.UserClient;
import prolevexman.config.Config;
import prolevexman.extensions.WebDriverExtension;
import prolevexman.model.User;
import prolevexman.pages.LoginPage;
import prolevexman.pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static prolevexman.utils.UserGenerator.randomUserWithPassLength;

@ExtendWith(WebDriverExtension.class)
@Browser
@DisplayName("Успешная регистрация")
public class RegistrationPositiveTest {

    private static final int PASSWORD_LENGTH = 6;
    private RegistrationPage registrationPage;
    private User user;

    @BeforeAll
    public static void setUp() {
        Config.setUp();
    }

    @BeforeEach
    void setUP(WebDriver driver) {
        registrationPage = new RegistrationPage(driver)
                .openRegistrationPage();
        user = randomUserWithPassLength(PASSWORD_LENGTH);
    }

    @Test
    void successfulRegistration() {

        LoginPage loginPage = registrationPage
                .setNameField(user.getName())
                .setEmailField(user.getEmail())
                .setPasswordField(user.getPassword())
                .clickRegistrationButton();

        assertTrue(loginPage.isVisibleLoginPage(), () -> "После регистрации ожидается перенаправление на стрницу входа");
    }

    @AfterEach
    void cleanUp() {
        if (user != null) {
            UserClient userClient = new UserClient();
            User loginUser = new User(user.getEmail(), user.getPassword(), null);

            Response loginResponse = userClient.loginUser(loginUser);
            String token = loginResponse.jsonPath().getString("accessToken");

            if (token != null && !token.isEmpty()) {
                Response response = userClient.deleteUser(token);
                assertEquals(202, response.statusCode(), () -> "Юзер не удалился: " + user.getEmail());

            }
        }
    }
}
