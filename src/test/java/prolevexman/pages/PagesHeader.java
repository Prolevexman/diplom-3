package prolevexman.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static prolevexman.locators.HeaderLocators.*;
import static prolevexman.locators.MainPageLocators.SING_IN_BUTTON;

public class PagesHeader extends BasePage {

    private static final String URL = "https://stellarburgers.nomoreparties.site/";

    public PagesHeader(WebDriver driver) {
        super(driver);
    }

    @Step("Нажатие на кнопку профиля")
    public LoginPage clickProfileButton() {
        clickElementWithCheck(PROFILE);
        return new LoginPage(getDriver());
    }

    @Step("Нажатие на кнопку конструктора")
    public LoginPage clickConstructorButton() {
        clickElementWithCheck(CONSTRUCTOR);
        return new LoginPage(getDriver());
    }

    @Step("Нажатие на логотип")
    public LoginPage clickLogoLink() {
        clickElementWithCheck(LOGO);
        return new LoginPage(getDriver());
    }




}
