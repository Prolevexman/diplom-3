package prolevexman.tests.scenario;

import org.openqa.selenium.WebDriver;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class NavigationScenario<T> {
    String name;
    Function<WebDriver, T> startPage;
    Consumer<T> action;
    Supplier<Boolean> check;

    public NavigationScenario(String name, Function<WebDriver, T> startPage, Consumer<T> action, Supplier<Boolean> check) {
        this.name = name;
        this.startPage = startPage;
        this.action = action;
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public Function<WebDriver, T> getStartPage() {
        return startPage;
    }

    public Consumer<T> getAction() {
        return action;
    }

    public Supplier<Boolean> getCheck() {
        return check;
    }
}
