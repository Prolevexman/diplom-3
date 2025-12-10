package prolevexman.extensions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import prolevexman.annotations.Browser;

public class WebDriverExtension implements BeforeAllCallback, AfterAllCallback,
        BeforeEachCallback, AfterEachCallback, ParameterResolver {

    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(prolevexman.extensions.WebDriverExtension.class);

    @Override
    public void beforeAll(ExtensionContext context) {
        boolean perClass = isPerClass(context);
        if (perClass) {
            WebDriver driver = createDriver(resolveBrowserName());
            getStore(context).put("driver", driver);
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        boolean perClass = isPerClass(context);
        if (perClass) {
            quitDriver(getDriver(context));
        }
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        boolean perClass = isPerClass(context);
        if (!perClass) {
            WebDriver driver = createDriver(resolveBrowserName());
            getStore(context).put("driver", driver);
        }
    }

    @Override
    public void afterEach(ExtensionContext context) {
        boolean perClass = isPerClass(context);
        if (!perClass) {
            quitDriver(getDriver(context));
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType().equals(WebDriver.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return getDriver(extensionContext);
    }

    private boolean isPerClass(ExtensionContext context) {
        Browser annotation = context.getRequiredTestClass().getAnnotation(Browser.class);
        return annotation != null && annotation.perClass();
    }

    private String resolveBrowserName() {
        return System.getProperty("browser", "chrome");
    }

    private WebDriver createDriver(String browser) {

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "yandex":
                WebDriverManager.chromedriver().driverVersion(System.getProperty("chromedriver_version")).setup();
                ChromeOptions options = new ChromeOptions();
                options.setBinary(System.getProperty("yandex_browser_path"));
                return new ChromeDriver(options);
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }

    private void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(NAMESPACE);
    }

    private WebDriver getDriver(ExtensionContext context) {
        return getStore(context).get("driver", WebDriver.class);
    }
}
