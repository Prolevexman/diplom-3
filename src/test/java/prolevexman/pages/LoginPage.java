package prolevexman.pages;

import org.openqa.selenium.WebDriver;

import static prolevexman.locators.LoginPageLocators.LOGIN_FORM;

public class LoginPage extends BasePage {

    private static final String URL = "https://stellarburgers.nomoreparties.site/login";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static String getUrl() {
        return URL;
    }

    public boolean isVisibleLoginPage() {
        return isVisible(LOGIN_FORM);

    }


}
