package prolevexman.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import prolevexman.model.User;

import static prolevexman.locators.LoginPageLocators.*;

public class LoginPage extends BasePage {

    private static final String URL = "https://stellarburgers.nomoreparties.site/login";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static String getUrl() {
        return URL;
    }

    public LoginPage openLoginPage() {
        openPage(URL);
        waitElementLoad(LOGIN_FORM);
        return this;
    }

    public boolean isVisibleLoginPage() {
        return isVisible(LOGIN_FORM);
    }

    @Step("Нажатие на кнопку логин")
    public LoginPage clickLoginButton() {
        clickElementWithCheck(LOGIN_BUTTON);
        return this;
    }

    @Step("Ввод email пользователя: {email}")
    public LoginPage setEmailField(String userEmail) {
        fillField(LOGIN_EMAIL, userEmail);
        return this;
    }

    @Step("Ввод пароля пользователя")
    public LoginPage setPasswordField(String userPassword) {
        fillField(LOGIN_PASSWORD, userPassword);
        return this;
    }

    public LoginPage login(User user) {
        setEmailField(user.getEmail());
        setPasswordField(user.getPassword());
        clickLoginButton();
        return this;
    }
}
