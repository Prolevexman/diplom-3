package prolevexman.tests.scenario;

import org.openqa.selenium.WebDriver;
import prolevexman.pages.LoginPage;
import prolevexman.pages.MainPage;
import prolevexman.pages.PagesHeader;
import prolevexman.pages.ProfilePage;

import java.util.List;

public class Scenarios {

    public static List<NavigationScenario<?>> getScenario() {
        return List.of(
                new NavigationScenario(
                        "Переход в личный кабинет",
                        driver -> new MainPage(driver).openMainPage(),
                        page -> new PagesHeader(page.get).clickProfileButton(),
                        () -> new ProfilePage(driver).isVisibleLoginPage()
                ),
                new NavigationScenario(
                        "Переход из личного кабинета в конструктор",
                        () -> new ProfilePage(driver).openProfilePage(),
                        page -> new PagesHeader(driver).clickConstructorButton(),
                        () -> new MainPage(driver).isVisibleCreateBurger()
                ),
                new NavigationScenario(
                        "Выход из аккаунта",
                        () -> new ProfilePage(driver).openProfilePage(),
                        page -> ((ProfilePage) page).clickSignoutButtton(),
                        () -> new LoginPage(driver).isVisibleLoginPage()
                )
        );
    }
}
