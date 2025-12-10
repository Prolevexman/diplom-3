Этот проект содержит автоматизированные тесты для веб-приложения с использованием Selenium WebDriver, JUnit 5 и WebDriverManager.

## Поддерживаемые браузеры
- Chrome (по умолчанию)
- Firefox
- Яндекс.Браузер (через ChromeDriver)

## Требования
- Java 11+
- Maven 3.8+
- Chrome / Firefox / Яндекс.Браузер установлен на локальной машине

---

## Запуск тестов

### 1. По умолчанию (Chrome)
```bash
mvn clean test
```
### 2 Firefox
```bash
mvn clean test -Dbrowser=firefox
```
### 3. Yandex

mvn clean test "-Dbrowser=yandex" "-Dchromedriver_version={version}" "-Dyandex_browser_path=path"
