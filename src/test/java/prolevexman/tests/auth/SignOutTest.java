package prolevexman.tests.auth;

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
import prolevexman.pages.PagesHeader;
import prolevexman.pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static prolevexman.utils.UserGenerator.randomUserWithPassLength;

@ExtendWith(WebDriverExtension.class)
@Browser
public class SignOutTest {

    private  User user;
    private  final int PASSWORD_LENGTH = 8;

    private  ProfilePage profilePage;
    private  LoginPage loginPage;
    private  PagesHeader pagesHeader;

    @BeforeEach
    public void setUp(WebDriver driver) {

        Config.setUp();

        profilePage = new ProfilePage(driver);
        loginPage = new LoginPage(driver);
        pagesHeader = new PagesHeader(driver);

        user = randomUserWithPassLength(PASSWORD_LENGTH);
        UserClient userClient = new UserClient();
        userClient.createUser(user);
        User loginUser = new User(user.getEmail(), user.getPassword(), null);
        loginPage.openLoginPage();
        loginPage.login(loginUser);
    }

    @DisplayName("Выход из профиля")
    @Test
    void signOutFromProfile() {

        pagesHeader.clickProfileButton();
        profilePage.clickSignoutButttonActions();

        assertTrue(loginPage.isVisibleLoginPage(), () -> "Не удалось выйти из личного кабинета");
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
