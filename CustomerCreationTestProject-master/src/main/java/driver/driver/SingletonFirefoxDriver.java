package driver.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SingletonFirefoxDriver {
    private static WebDriver driver;

    private SingletonFirefoxDriver() {
    }

    public static WebDriver getFirefoxDriver() {
        if (driver == null) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\Driver\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        return driver;
    }
}
