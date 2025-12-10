package prolevexman.tests.navigation;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import prolevexman.annotations.Browser;
import prolevexman.api.UserClient;
import prolevexman.config.Config;
import prolevexman.extensions.WebDriverExtension;
import prolevexman.model.User;
import prolevexman.pages.LoginPage;
import prolevexman.pages.MainPage;
import prolevexman.pages.PagesHeader;
import prolevexman.pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static prolevexman.utils.UserGenerator.randomUserWithPassLength;

@ExtendWith(WebDriverExtension.class)
@Browser(perClass = true)
@DisplayName("Тесты навигации авторизованного пользователя")
public class SiteNavigationTest {

    private static User user;
    private static final int PASSWORD_LENGTH = 8;

    private static ProfilePage profilePage;
    private static MainPage mainPage;
    private static LoginPage loginPage;
    private static PagesHeader pagesHeader;

    private enum NavigationTarget {
        CONSTRUCTOR, LOGO
    }

    @BeforeAll
    public static void setUp(WebDriver driver) {

        Config.setUp();

        profilePage = new ProfilePage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        pagesHeader = new PagesHeader(driver);

        user = randomUserWithPassLength(PASSWORD_LENGTH);
        UserClient userClient = new UserClient();
        userClient.createUser(user);
        User loginUser = new User(user.getEmail(), user.getPassword(), null);
        Response response = userClient.loginUser(loginUser);
        String token = response.jsonPath().getString("accessToken");
        loginPage.openLoginPage();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('accessToken', arguments[0]);", token);
        driver.navigate().refresh();



    }

    @DisplayName("Переход в личный кабинет")
    @Test
    void navigationToProfile() {

        pagesHeader.clickProfileButton();

        assertTrue(profilePage.isVisibleProfilePage(), () -> "Не удалось перейти в личный кабинет");
    }

    @DisplayName("Переход из личного кабинета в конструктор")
    @ParameterizedTest(name = "Переход из профиля через {0}")
    @EnumSource(NavigationTarget.class)
    void navigationFromProfileToConstructor(NavigationTarget target) {

        pagesHeader.clickProfileButton();
        profilePage.waitPageLoad();

        switch (target) {
            case CONSTRUCTOR:
                pagesHeader.clickConstructorButton();
                break;
            case LOGO:
                pagesHeader.clickLogoLink();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pagesHeader.clickLogoLink();
                break;
        }
        assertTrue(mainPage.isVisibleCreateBurger(), () -> "Не удалось перейти в конструктор " + target);
    }

    @AfterAll
    static void cleanUp() {
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
