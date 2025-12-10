package prolevexman.locators;

import org.openqa.selenium.By;

public class LoginPageLocators {

    public static final By LOGIN_FORM = By.xpath(".//div[@class='Auth_login__3hAey']/h2[normalize-space(text())='Вход']");
    public static final By LOGIN_EMAIL = By.xpath(".//div[contains(@class,'input') and .//label[normalize-space(text())='Email']]//input");
    public static final By LOGIN_PASSWORD = By.xpath(".//div[contains(@class,'input') and .//label[normalize-space(text())='Пароль']]//input");
    public static final By LOGIN_BUTTON = By.xpath(".//button[normalize-space(text())='Войти']");


}
