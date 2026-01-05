package Activities;

import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity_6 {

    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");

        // REQUIRED for Chrome 134+
        options.setCapability("chromedriverAutodownload", true);

        options.noReset();

        URL serverURL = new URI("http://localhost:4723").toURL();
        driver = new AndroidDriver(serverURL, options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://training-support.net/webelements/sliders");

        // Switch to WEBVIEW safely
        for (String context : driver.getContextHandles()) {
            if (context.contains("WEBVIEW")) {
                driver.context(context);
                break;
            }
        }
    }

    @Test
    public void volume75Test() {

        WebElement slider = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input[type='range']")
                )
        );

        slider.sendKeys(Keys.HOME);
        slider.sendKeys("75");

        WebElement value = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("rangeValue")
                )
        );

        System.out.println(value.getText());
        assertTrue(value.getText().contains("75"));
    }

    @Test
    public void volume25Test() {

        WebElement slider = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input[type='range']")
                )
        );

        slider.sendKeys(Keys.HOME);
        slider.sendKeys("25");

        WebElement value = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("rangeValue")
                )
        );

        System.out.println(value.getText());
        assertTrue(value.getText().contains("25"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
