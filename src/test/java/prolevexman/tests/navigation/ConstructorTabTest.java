package prolevexman.tests.navigation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import prolevexman.annotations.Browser;
import prolevexman.config.Config;
import prolevexman.data.ConstructorTabs;
import prolevexman.extensions.WebDriverExtension;
import prolevexman.locators.MainPageLocators;;
import prolevexman.pages.MainPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static prolevexman.locators.MainPageLocators.CONSTRUCTOR_CONTAINER;

@ExtendWith(WebDriverExtension.class)
@Browser(perClass = true)
@DisplayName("Тесты переходов по разделам конструктора")
public class ConstructorTabTest {

    private static MainPage mainPage;

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
                        int headerY = header.getLocation().getY();
                        System.out.println(headerY);
                        int containerY = constructorContainer.getLocation().getY();
                        System.out.println("контейнер" + containerY);
                        return headerY >= containerY && headerY <= containerY + 41;
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
