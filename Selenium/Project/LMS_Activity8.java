package example;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LMS_Activity8 {
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
    public void contact() {
    	WebElement contact = driver.findElement(
    		    By.xpath("//a[text()='Contact']")
    		);
    	contact.click();
    }
    
    @Test(priority = 2) 
    public void contactUsForm()
    {    	
    	// FullName Field
        driver.findElement(By.id("wpforms-8-field_0")).sendKeys("Samudra");
        // Email Filed
        driver.findElement(By.id("wpforms-8-field_1")).sendKeys("samudra@gmail.com");
        // subject Field
        driver.findElement(By.id("wpforms-8-field_3")).sendKeys("Test");
        // Comment or message
        driver.findElement(By.id("wpforms-8-field_2")).sendKeys("Tested");
        driver.findElement(By.id("wpforms-submit-8")).click();
        
        WebElement message = driver.findElement(By.xpath("/html/body/div/div/div/div/main/article/div/section[4]/div[2]/div[2]/div[2]/div[2]/p"));
        Assert.assertTrue(message.isDisplayed());
        System.out.println(message.getText());
    }
    
 // Teardown function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}