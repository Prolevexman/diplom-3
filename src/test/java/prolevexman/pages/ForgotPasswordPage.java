package prolevexman.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static prolevexman.locators.ForgotPasswordPageLocators.LOGIN_LINK_REC_PAGE;
import static prolevexman.locators.ForgotPasswordPageLocators.PASSWORD_RECOVERY;
import static prolevexman.locators.MainPageLocators.CREATE_BURGER;
import static prolevexman.locators.RegistrationPageLocators.LOGIN_LINK_REG_PAGE;

public class ForgotPasswordPage extends BasePage {

    private static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public ForgotPasswordPage openForgotPasswordPage() {
        openPage(URL);
        waitElementLoad(PASSWORD_RECOVERY);
        return this;
    }

    @Step("Переход по ссылке на страницу логина")
    public LoginPage clickLoginLink() {
        clickElementWithCheck(LOGIN_LINK_REC_PAGE);
        return new LoginPage(getDriver());
    }
}
