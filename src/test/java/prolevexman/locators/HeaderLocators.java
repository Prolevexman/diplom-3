package prolevexman.locators;

import org.openqa.selenium.By;

public class HeaderLocators {

    public static final By PROFILE = By.xpath(".//a[@href='/account']");
    public static final By CONSTRUCTOR = By.xpath(".//a[@href='/']/p[text()='Конструктор']");
    public static final By LOGO = By.xpath("//div[contains(@class, 'AppHeader_header')]/a");
}
