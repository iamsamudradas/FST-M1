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

public class LMS_Activity7 {
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
    public void allCourses() {
    	WebElement allCourses = driver.findElement(
    		    By.xpath("//a[text()='All Courses']")
    		);
    	allCourses.click();
    }
    
    @Test(priority = 2)
    public void countOfCourses() {
    	//Fetch AllCourses
    	List<WebElement> courses = driver.findElements(
                By.cssSelector("article.course"));
    	
    	//Print no of courses
    	int courseCount = courses.size();
        System.out.println("Total number of courses: " + courseCount);
    }
    
    // Teardown function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}