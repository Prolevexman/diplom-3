package prolevexman.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, ofSeconds(10));
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait() {
        return wait;
    }

    protected void openPage(String url) {
        driver.get(url);
    }

    protected void waitElementLoad(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Элемент не прогрузился: " + locator);
        }
    }

    protected void clickElementWithCheck(By locator) {
        try {
            Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(ElementClickInterceptedException.class);
            fluentWait.until(driver -> {
                WebElement element = driver.findElement(locator);
                if (element.isDisplayed() && element.isEnabled()) {
                    Actions actions = new Actions(driver);
                    actions.moveToElement(element).pause(Duration.ofMillis(500)).click().perform();
                    return true;
                } else {
                    return false;
                }
            });
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Кнопка не доступна или не найдена: " + locator, e);
        }
    }

    protected void fillField(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.clear();
            element.sendKeys(text);
            element.click();
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Поле для ввода не найдено: " +locator);
        }
    }

    protected String getText(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Не получен текст элемента: " +locator);
        }
    }

    protected boolean isVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


}
