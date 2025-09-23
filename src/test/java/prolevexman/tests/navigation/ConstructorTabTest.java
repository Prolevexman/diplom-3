package prolevexman.tests.navigation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import prolevexman.annotations.Browser;
import prolevexman.api.UserClient;
import prolevexman.config.Config;
import prolevexman.data.ConstructorTabs;
import prolevexman.extensions.WebDriverExtension;
import prolevexman.locators.MainPageLocators;
import prolevexman.model.User;
import prolevexman.pages.LoginPage;
import prolevexman.pages.MainPage;
import prolevexman.pages.PagesHeader;
import prolevexman.pages.ProfilePage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static prolevexman.locators.MainPageLocators.CONSTRUCTOR_CONTAINER;
import static prolevexman.utils.UserGenerator.randomUserWithPassLength;

@ExtendWith(WebDriverExtension.class)
@Browser(perClass = true)
@DisplayName("Тесты переходов по разделам конструктора")
public class ConstructorTabTest {

    private static User user;
    private static final int PASSWORD_LENGTH = 8;

    private static MainPage mainPage;
    private static LoginPage loginPage;
    private static PagesHeader pagesHeader;

    @BeforeAll
    public static void setUp(WebDriver driver) {

        Config.setUp();

        mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }

    @ParameterizedTest
    @EnumSource(ConstructorTabs.class)
    void tabNavigationTest(ConstructorTabs tab, WebDriver driver) {

        By tabLocator = getTabLocator(tab);
        By headerLocator = getHeaderLocator(tab);

        mainPage.clickButtonByLocator(tabLocator);

        assertDoesNotThrow(() -> {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(d -> {
                        WebElement header = d.findElement(headerLocator);
                        WebElement constructorContainer = d.findElement(CONSTRUCTOR_CONTAINER);
                        return header.getLocation().getY() <= constructorContainer.getLocation().getY() + 41;
                    });
        }, "Заголовок " + tab.getName() + " не поднялся наверх");


    }

    private By getTabLocator(ConstructorTabs tab) {
        switch (tab) {
            case BUNS: return MainPageLocators.BUNS;
            case SAUCE: return MainPageLocators.SAUCE;
            case FILLING: return MainPageLocators.FILLING;
            default: throw new IllegalArgumentException("Неизвестный раздел: " + tab);
        }
    }

    private By getHeaderLocator(ConstructorTabs tab) {
        switch (tab) {
            case BUNS: return MainPageLocators.BUNS_HEADER;
            case SAUCE: return MainPageLocators.SAUCE_HEADER;
            case FILLING: return MainPageLocators.FILLING_HEADER;
            default: throw new IllegalArgumentException("Неизвестный заголовок: " + tab);
        }
    }


}
