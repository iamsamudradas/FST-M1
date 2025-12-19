package example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class LMS_Activity6 {
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
    public void navigateToMyAccountVerifyTitle() {        
    	WebElement myAccount = driver.findElement(
    		    By.xpath("//a[text()='My Account']")
    		);
    		myAccount.click();
    		// Assert page title
            Assert.assertEquals(driver.getTitle(), "My Account – Alchemy LMS");
    }
    
    @Test(priority = 2) 
    public void Login()
    {
    	
    	// click on login button
    	WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"uagb-column-e9d225cb-cee9-4e02-a12d-073d5f051e91\"]/div[2]/div[2]/a"));
    	loginButton.click();
    	// Find the username field and enter the username
        driver.findElement(By.id("user_login")).sendKeys("root");
        // Find the password field and enter the password
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
        // Find the login button and click it
        driver.findElement(By.id("wp-submit")).click();
        //Find Logout Button
        WebElement logoutBtn = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/main/article/div/section[2]/div[2]/div[2]/div[2]/div[4]/a"));
        Assert.assertTrue(logoutBtn.isDisplayed());
    }
    
 // Teardown function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}