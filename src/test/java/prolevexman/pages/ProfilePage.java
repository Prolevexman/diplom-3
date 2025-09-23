package prolevexman.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static prolevexman.locators.ProfilePageLocators.NAME_LABEL;
import static prolevexman.locators.ProfilePageLocators.SIGNOUT_BUTTON;

public class ProfilePage extends BasePage {

    private static final String URL = "https://stellarburgers.nomoreparties.site/account";

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean isVisibleProfilePage() {
        return isVisible(NAME_LABEL);
    }

    @Step("Открытие страницы профиля")
    public ProfilePage openProfilePage() {
        openPage(URL);
        waitElementLoad(NAME_LABEL);
        return this;
    }

    @Step("Выход из профиля")
    public ProfilePage clickSignoutButtton() {
        clickElementWithCheck(SIGNOUT_BUTTON);
        return this;
    }
//кнопка перекрыта
    @Step("Выход из профиля")
    public ProfilePage clickSignoutButttonActions() {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(SIGNOUT_BUTTON));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).pause(Duration.ofMillis(200)).click().perform();
        return this;
    }
}
