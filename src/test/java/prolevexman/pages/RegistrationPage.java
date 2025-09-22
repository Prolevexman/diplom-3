package prolevexman.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static prolevexman.locators.RegistrationPageLocators.*;

public class RegistrationPage extends BasePage {

    private static final String URL = "https://stellarburgers.nomoreparties.site/register";

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage openRegistrationPage() {
        openPage(URL);
        waitElementLoad(REGISTRATION_FORM);
        return this;
    }

    @Step("Ввод имени пользователя: {name}")
    public RegistrationPage setNameField(String userName) {
       fillField(REGISTRATION_NAME_FIELD, userName);
       return this;
    }

    @Step("Ввод email пользователя: {email}")
    public RegistrationPage setEmailField(String userEmail) {
       fillField(REGISTRATION_EMAIL_FIELD, userEmail);
       return this;
    }

    @Step("Ввод пароля пользователя")
    public RegistrationPage setPasswordField(String userPassword) {
       fillField(REGISTRATION_PASSWORD_FIELD, userPassword);
       return this;
    }

    @Step("Нажатие на кнопку регистрации")
    public LoginPage clickRegistrationButton() {
       clickElementWithCheck(REGISTER_BUTTON);
       return new LoginPage(getDriver());
    }

    public boolean isVisibleErrorMessage() {
       return isVisible(INCORRECT_PASSWORD_MESSAGE);
    }

    public String getElementText() {
       return getText(INCORRECT_PASSWORD_MESSAGE);
    }



}
