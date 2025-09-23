package prolevexman.locators;

import org.openqa.selenium.By;

public class RegistrationPageLocators {

    public static final By REGISTRATION_FORM = By.xpath(".//div[@class='Auth_login__3hAey']/h2[normalize-space(text())='Регистрация']");
    public static final By REGISTRATION_NAME_FIELD = By.xpath(".//div[label[normalize-space(text())='Имя']]/input");
    public static final By REGISTRATION_EMAIL_FIELD = By.xpath(".//div[label[normalize-space(text())='Email']]/input");
    public static final By REGISTRATION_PASSWORD_FIELD = By.xpath(".//div[label[normalize-space(text())='Пароль']]/input");
    public static final By INCORRECT_PASSWORD_MESSAGE = By.xpath(".//p[normalize-space(text())='Некорректный пароль']");
    public static final By REGISTER_BUTTON = By.xpath(".//button[normalize-space(text())='Зарегистрироваться']");
    public static final By LOGIN_LINK_REG_PAGE = By.xpath(".//a[@href='/login']");
    


}
