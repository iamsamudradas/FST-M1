package example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LMS_Activity2 {
    WebDriver driver;
    
    // Setup function
    @BeforeClass
    public void setUp() {
        // Initialize Firefox driver
        driver = new FirefoxDriver();
        // Open the page
        driver.get("https://alchemy.hguy.co/lms");
    }
    
    // Test function
    @Test(priority = 1)
    public void headerTest() {        
         Assert.assertEquals(
        		 driver.findElement(By.tagName("h1")).getText(),
                 "Learn from Industry Experts",
                 "Heading text does not match"
         );
    }
    
    // Teardown function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}