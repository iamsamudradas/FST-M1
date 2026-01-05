package project;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class chrome {

    AndroidDriver driver;
    WebDriverWait wait;

    // =====================================================
    // SETUP
    // =====================================================
    @BeforeClass
    public void setUp() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();

        URL serverURL = new URI("http://localhost:4723").toURL();
        driver = new AndroidDriver(serverURL, options);

       
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    
    @Test(priority = 1)
    public void scrollIntoViewTest() {
    	
    	// Always start from home page (test independence)
        driver.get("https://training-support.net/webelements");

        String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
        driver.findElement(AppiumBy.androidUIAutomator(
                UiScrollable + ".flingForward()"
        ));

        driver.findElement(
            AppiumBy.xpath("//android.widget.TextView[@text=\"To-Do List\"]")
        ).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"todo-input\")"))
              .sendKeys("Add tasks to list");
        
        driver.findElement(
        	    AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"todo-add\")")
        	).click();
        
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"todo-input\")"))
        .sendKeys("Get Number of Tasks");
  
        driver.findElement(
  	          AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"todo-add\")")
  	        ).click();
        
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"todo-input\")"))
        .sendKeys("Clear The List");
        
        driver.findElement(
    	          AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"todo-add\")")
    	        ).click();
        
        driver.findElement(
  	          AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(2)")
  	        ).click();
        
        driver.findElement(
    	          AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(3)")
    	        ).click();
        
        driver.findElement(
    	          AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(4)")
    	        ).click();
        
        int taskCount = driver.findElements(
                AppiumBy.className("android.widget.CheckBox")
        ).size();

        Assert.assertEquals(taskCount, 5, "Task count mismatch");

              
    }
    
    
    @Test(priority = 2)
    public void loginWithValidCredentials() {

        // Always start from home page (test independence)
        driver.get("https://training-support.net/webelements");

        driver.context("NATIVE_APP");

        // Scroll until Login Form card is visible
        driver.findElement(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().text(\"Login Form\"))"
        ));

        // Click Login Form card
        driver.findElement(
            AppiumBy.xpath("//android.widget.TextView[@text=\"Login Form\"]")
        ).click();

        // Enter valid credentials
        driver.findElement(
            AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"username\")")
        ).sendKeys("admin");

        driver.findElement(
            AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"password\")")
        ).sendKeys("password");

        // Submit login
        driver.findElement(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Submit\")")
        ).click();

        // Assertion: successful login message
        Assert.assertTrue(
            driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[contains(@text,'Welcome')]")
            ).isDisplayed(),
            "Valid login success message not displayed"
        );
    }


    @Test(priority = 3)
    public void loginWithInvalidCredentials() {

        // Always start from home page (test independence)
        driver.get("https://training-support.net/webelements");

        driver.context("NATIVE_APP");

        // Scroll until Login Form card is visible
        driver.findElement(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().text(\"Login Form\"))"
        ));

        // Click Login Form card
        driver.findElement(
            AppiumBy.xpath("//android.widget.TextView[@text=\"Login Form\"]")
        ).click();

        // Enter INVALID credentials
        driver.findElement(
            AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"username\")")
        ).sendKeys("wronguser");

        driver.findElement(
            AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"password\")")
        ).sendKeys("wrongpassword");

        // Submit login
        driver.findElement(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Submit\")")
        ).click();

        // Assertion: invalid login message
        Assert.assertTrue(
            driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[contains(@text,'Invalid')]")
            ).isDisplayed(),
            "Invalid login error message not displayed"
        );
    }

    @Test(priority = 4)
    public void popupLoginWithValidCredentials() {

        // Always start from home page (test independence)
        driver.get("https://training-support.net/webelements");

        driver.context("NATIVE_APP");

        // Scroll until Popups card is visible
        driver.findElement(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().text(\"Selects\"))"
        ));

        // Click Popups card
        driver.findElement(
            AppiumBy.xpath("//android.widget.TextView[@text=\"Popups\"]")
        ).click();

        // Click button to open login popup
        driver.findElement(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Open Login Popup\")")
        ).click();

        // Enter credentials inside popup
        driver.findElement(
            AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"username\")")
        ).sendKeys("admin");

        driver.findElement(
            AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"password\")")
        ).sendKeys("password");

        // Submit popup login
        driver.findElement(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Submit\")")
        ).click();

        // Assertion: successful popup login message
        Assert.assertTrue(
            driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[contains(@text,'Welcome')]")
            ).isDisplayed(),
            "Popup login success message not displayed"
        );
    }

 
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
