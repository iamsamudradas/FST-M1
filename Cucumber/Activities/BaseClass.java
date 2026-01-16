package stepDefinitions;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    protected void initDriver(WebDriver webDriver) {
        driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    protected void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
