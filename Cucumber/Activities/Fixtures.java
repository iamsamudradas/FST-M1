package stepDefinitions;

import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Fixtures extends BaseClass {
    @BeforeAll
    public static void setUp() {
        // Initialize Firefox Driver
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    @AfterAll
    public static void tearDown() {
        // Close the browser
        driver.quit();
    }
}