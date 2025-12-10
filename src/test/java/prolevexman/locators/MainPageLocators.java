package prolevexman.locators;

import org.openqa.selenium.By;

public class MainPageLocators {

    public static final By CREATE_BURGER = By.xpath(".//h1[normalize-space(text())='Соберите бургер']");
    public static final By SING_IN_BUTTON = By.xpath(".//button[normalize-space(text())='Войти в аккаунт']");
    public static final By BUNS = By.xpath(".//div//span[text()='Булки']/..");
    public static final By SAUCE = By.xpath(".//div//span[text()='Соусы']/..");
    public static final By FILLING = By.xpath(".//div//span[text()='Начинки']/..");
    public static final By FILLING_HEADER = By.xpath(".//div[contains(@class, 'BurgerIngredients_ingredients')]//h2[normalize-space(text())='Начинки']");
    public static final By SAUCE_HEADER = By.xpath(".//div[contains(@class, 'BurgerIngredients_ingredients')]//h2[normalize-space(text())='Соусы']");
    public static final By BUNS_HEADER = By.xpath(".//div[contains(@class, 'BurgerIngredients_ingredients')]//h2[normalize-space(text())='Булки']");
    public static final By CONSTRUCTOR_CONTAINER = By.xpath(".//div[contains(@class, 'BurgerIngredients_ingredients')]");
}
