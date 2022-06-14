package driver.driver;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static WebDriver createWebDriver(String driver) {
        if (driver == "firefox") {
            return SingletonFirefoxDriver.getFirefoxDriver();
        } else
            return SingletonChromeDriver.getChromeDriver();
    }
}
