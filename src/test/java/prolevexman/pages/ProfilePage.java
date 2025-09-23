package prolevexman.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static prolevexman.locators.LoginPageLocators.LOGIN_FORM;
import static prolevexman.locators.ProfilePageLocators.NAME_LABEL;
import static prolevexman.locators.ProfilePageLocators.SIGNOUT_BUTTON;
import static prolevexman.locators.RegistrationPageLocators.REGISTRATION_FORM;

public class ProfilePage extends BasePage {

    private static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean isVisibleLoginPage() {
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
}
