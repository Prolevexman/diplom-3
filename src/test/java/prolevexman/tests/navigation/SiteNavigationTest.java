package prolevexman.tests.navigation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import prolevexman.annotations.Browser;
import prolevexman.api.UserClient;
import prolevexman.config.Config;
import prolevexman.extensions.WebDriverExtension;
import prolevexman.model.User;
import prolevexman.pages.RegistrationPage;
import prolevexman.tests.scenario.NavigationScenario;
import prolevexman.tests.scenario.Scenarios;

import java.net.HttpURLConnection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static prolevexman.utils.UserGenerator.randomUserWithPassLength;

@ExtendWith(WebDriverExtension.class)
@Browser(perClass = true)
@DisplayName("Тесты навигации авторизованного пользователя")
public class SiteNavigationTest {

    private static User user;
    private static final int PASSWORD_LENGTH = 8;

    @BeforeAll
    public static void setUp(WebDriver driver) {

        Config.setUp();

        RegistrationPage registrationPage = new RegistrationPage(driver)
                .openRegistrationPage();
        user = randomUserWithPassLength(PASSWORD_LENGTH);
        UserClient userClient = new UserClient();
        userClient.createUser(user);
        User loginUser = new User(user.getEmail(), user.getPassword(), null);
        userClient.loginUser(loginUser).then().statusCode(HttpURLConnection.HTTP_OK);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("scenarioProvider")
    void navigationScenarioTest(WebDriver driver, NavigationScenario scenario) {
        T startPage = scenario.getStartPage().apply(driver);

        scenario.getAction().accept(startPage);

        assertTrue(scenario.getCheck().get(), () -> "Ошибка сценария: " + scenario.getName());
    }

    static Stream<NavigationScenario> scenarioProvider() {
        return Scenarios.getScenario(driver).stream();
    }

}
