package prolevexman.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static prolevexman.locators.MainPageLocators.CREATE_BURGER;
import static prolevexman.locators.MainPageLocators.SING_IN_BUTTON;

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


}
