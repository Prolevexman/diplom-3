package prolevexman.data;

import org.openqa.selenium.WebDriver;
import prolevexman.pages.ForgotPasswordPage;
import prolevexman.pages.MainPage;
import prolevexman.pages.PagesHeader;
import prolevexman.pages.RegistrationPage;

import java.util.function.Consumer;

public enum LoginNavigationScenario {

    REGISTRATION("Переход из регистрации", driver -> new RegistrationPage(driver).openRegistrationPage().clickLoginLink()),
    MAIN("Переход с главной страницы", driver -> new MainPage(driver).openMainPage().clickRegistrationButton()),
    PROFILE("Переход по кнопке профиля", driver -> {
        new MainPage(driver).openMainPage();
        new PagesHeader(driver).clickProfileButton();
    }),
    RECOVERY("Переход со страницы восстановления пароля", driver -> new ForgotPasswordPage(driver).openForgotPasswordPage().clickLoginLink());

    private final String description;
    private final Consumer<WebDriver> action;

    LoginNavigationScenario(String description, Consumer<WebDriver> action) {
        this.description = description;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void perform(WebDriver driver) {
        action.accept(driver);
    }
}
