package prolevexman.tests.redirection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import prolevexman.annotations.Browser;
import prolevexman.config.Config;
import prolevexman.data.LoginNavigationScenario;
import prolevexman.extensions.WebDriverExtension;
import prolevexman.pages.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(WebDriverExtension.class)
@Browser(perClass = true)
@DisplayName("Тесты перенаправления на страницу входа")
public class ParametrizedRedirectToLoginTest {

    private LoginPage loginPage;
    private static final String REDIRECT_ERROR = "Переход на страницу логина не произошел";

    @BeforeAll
    public static void setUp() {
        Config.setUp();
    }

    @BeforeEach
    void setUP(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(LoginNavigationScenario.class)
    void navigationToLoginPage(LoginNavigationScenario scenario, WebDriver driver) {

        scenario.perform(driver);
        assertTrue(loginPage.isVisibleLoginPage(), () -> REDIRECT_ERROR);
    }
}
