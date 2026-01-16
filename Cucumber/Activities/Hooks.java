package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import stepDefinitions.BaseClass;

public class Hooks extends BaseClass {

    @Before
    public void setUp() {
        initDriver(new ChromeDriver());
    }

    @After
    public void tearDown() {
        quitDriver();
    }
}
