package project;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class nativeApp {

    AndroidDriver driver;
    WebDriverWait wait;

    // Slider coordinates (based on your device)
    private static final int SLIDER_START_X = 311;
    private static final int SLIDER_END_X = 1000;
    private static final int SLIDER_Y = 1294;

    @BeforeClass
    public void setUp() throws Exception {

        File appFile = new File("src/test/resources/ts-todo-list-v1.apk");

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setApp(appFile.getAbsolutePath());
        options.noReset();

        URL serverURL = new URI("http://localhost:4723").toURL();
        driver = new AndroidDriver(serverURL, options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ==========================================================
    // TEST 1: Add tasks (NO completion)
    // ==========================================================
    @Test(priority = 1)
    public void SetToDoAndVerifyTasks() {

        addTask("Completed Activity 1", "High", false);
        addTask("Completed Activity 2", "Medium", false);
        addTask("Completed Activity 3", "Low", false);

        assertTaskPresent("Completed Activity 1");
        assertTaskPresent("Completed Activity 2");
        assertTaskPresent("Completed Activity 3");
    }

    // ==========================================================
    // TEST 2: Edit first task and set deadline
    // ==========================================================
    @Test(priority = 2, dependsOnMethods = "SetToDoAndVerifyTasks")
    public void editFirstTaskAndSetDeadline() {

        By firstTask = AppiumBy.xpath(
                "(//android.widget.TextView[contains(@text,'Completed')])[1]");

        longPressElement(firstTask);

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Edit To-Do Task']")))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.app.todolist:id/tv_todo_list_deadline"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Next month']")))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.view.View[@content-desc=\"10 February 2026\"]")))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.app.todolist:id/bt_deadline_ok"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.app.todolist:id/bt_new_task_ok"))).click();

        String deadlineText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("com.app.todolist:id/tv_exlv_task_deadline")))
                .getText();

        Assert.assertFalse(deadlineText.isBlank(),
                "Deadline was NOT set for the task");
    }

    // ==========================================================
    // TEST 3: Complete tasks with different progress
    // ==========================================================
    @Test(priority = 3, dependsOnMethods = "editFirstTaskAndSetDeadline")
    public void completeTasksWithDifferentProgress() {

        // Task 1 → 100%
        slideTaskByIndex(1, SLIDER_END_X);

        // Task 2 → 100%
        slideTaskByIndex(2, SLIDER_END_X);

        // Task 3 → 50%
        int fiftyPercentX = SLIDER_START_X + ((SLIDER_END_X - SLIDER_START_X) / 2);
        slideTaskByIndex(3, fiftyPercentX);

        // ✅ ASSERT: only TWO tasks are completed
        assertCompletedTaskCount(2);
    }

    // ==========================================================
    // ADD TASK (slider optional)
    // ==========================================================
    private void addTask(String taskName, String priority, boolean moveSlider) {

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("fab_new_task"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("com.app.todolist:id/et_new_task_name")))
                .sendKeys(taskName);

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.app.todolist:id/tv_new_task_priority"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.TextView[@text='" + priority + "']")))
                .click();

        if (moveSlider) {
            Slider.swipeByCoordinates(
                    driver,
                    SLIDER_START_X, SLIDER_Y,
                    SLIDER_END_X, SLIDER_Y,
                    600
            );
        }

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.app.todolist:id/bt_new_task_ok"))).click();
    }

    // ==========================================================
    // SLIDE TASK BY INDEX
    // ==========================================================
    private void slideTaskByIndex(int index, int endX) {

        By task = AppiumBy.xpath(
                "(//android.widget.TextView[contains(@text,'Completed')])[" + index + "]");

        longPressElement(task);

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Edit To-Do Task']")))
                .click();

        Slider.swipeByCoordinates(
                driver,
                SLIDER_START_X, SLIDER_Y,
                endX, SLIDER_Y,
                600
        );

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.app.todolist:id/bt_new_task_ok"))).click();
    }

    // ==========================================================
    // ASSERT TASK EXISTS
    // ==========================================================
    private void assertTaskPresent(String taskName) {

        By taskLocator = AppiumBy.xpath(
                "//android.widget.TextView[@text='" + taskName + "']");

        Assert.assertTrue(
                driver.findElements(taskLocator).size() > 0,
                "Task NOT found in list: " + taskName
        );
    }

    // ==========================================================
    // ASSERT COMPLETED TASK COUNT
    // ==========================================================
    private void assertCompletedTaskCount(int expectedCount) {

        By completedTasks = AppiumBy.xpath(
                "//android.widget.TextView[contains(@text,'Completed')]");

        int actualCount = driver.findElements(completedTasks).size();

        Assert.assertEquals(
                actualCount,
                expectedCount,
                "Mismatch in completed task count"
        );
    }

    // ==========================================================
    // LONG PRESS HELPER
    // ==========================================================
    private void longPressElement(By locator) {

        var element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));

        int centerX = element.getLocation().getX() + element.getSize().getWidth() / 2;
        int centerY = element.getLocation().getY() + element.getSize().getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPress = new Sequence(finger, 1);

        longPress.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                centerX,
                centerY));

        longPress.addAction(finger.createPointerDown(
                PointerInput.MouseButton.LEFT.asArg()));

        longPress.addAction(new Pause(finger, Duration.ofSeconds(1)));

        longPress.addAction(finger.createPointerUp(
                PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(longPress));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
