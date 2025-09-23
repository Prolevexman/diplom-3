package prolevexman.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

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
    public ProfilePage clickSignoutButton() {
        clickElementWithCheck(SIGNOUT_BUTTON);
        return this;
    }

    @Step("Ожидание загрузки страницы")
    public ProfilePage waitPageLoad() {
        waitElementLoad(NAME_LABEL);
        return this;
    }
}
