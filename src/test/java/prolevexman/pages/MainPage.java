package prolevexman.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static prolevexman.locators.MainPageLocators.CREATE_BURGER;
import static prolevexman.locators.MainPageLocators.SING_IN_BUTTON;
import static prolevexman.locators.ProfilePageLocators.SIGNOUT_BUTTON;

public class MainPage extends BasePage{

    private static final String URL = "https://stellarburgers.nomoreparties.site/";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage openMainPage() {
        openPage(URL);
        waitElementLoad(CREATE_BURGER);
        return this;
    }

    @Step("Нажатие на кнопку входа")
    public LoginPage clickRegistrationButton() {
        clickElementWithCheck(SING_IN_BUTTON);
        return new LoginPage(getDriver());
    }

   public boolean isVisibleCreateBurger() {
        return isVisible(CREATE_BURGER);
   }

    public static String getUrl() {
        return URL;
    }

    @Step("Нажатие на кнопку по локатору")
    public MainPage clickButtonByLocator(By locator) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).pause(Duration.ofMillis(200)).click().perform();
        return this;
    }



}
