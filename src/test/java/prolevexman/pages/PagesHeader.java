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
    public PagesHeader clickProfileButton() {
        clickElementWithCheck(PROFILE);
        return this;
    }

    @Step("Нажатие на кнопку конструктора")
    public PagesHeader clickConstructorButton() {
        clickElementWithCheck(CONSTRUCTOR);
        return this;
    }

    @Step("Нажатие на логотип")
    public PagesHeader clickLogoLink() {
        clickElementWithCheck(LOGO);
        return this;
    }




}
