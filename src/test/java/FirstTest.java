import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Android_Emulator");
        desiredCapabilities.setCapability("platformVersion", "8.0");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
        desiredCapabilities.setCapability("app", System.getProperty("user.dir") + "\\src\\test\\resources\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCompareSearchFieldText() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search Wiki input",
                5);

        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search…",
                "Incorrect search field text");
    }

    @Test
    public void testSearchAndCancelSearch() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search Wiki input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

        assertElementsDisplayed(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Articles were not found searching by 'Java'");

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5);

        assertElementsNotDisplayed(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Articles are still displayed clearing search field");
    }

    private void assertElementHasText(By by, String expectedText, String errorMsg) {
        WebElement element = waitForElementPresent(by, errorMsg, 5);
        Assert.assertEquals(errorMsg, expectedText, element.getText());
    }

    private void assertElementsDisplayed(By by, String errorMsg) {
        Assert.assertTrue(errorMsg, isElementsPresent(by, errorMsg, 5));
    }

    private void assertElementsNotDisplayed(By by, String errorMsg) {
        Assert.assertTrue(errorMsg, isElementsNotPresent(by, errorMsg, 5));
    }

    private boolean isElementsPresent(By by, String errorMsg, long timeoutSec) {
        try {
            waitForElementsPresent(by, errorMsg, timeoutSec);
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    private boolean isElementsNotPresent(By by, String errorMsg, long timeoutSec) {
        try {
            waitForElementNotPresent(by, errorMsg, timeoutSec);
            return driver.findElements(by).size() == 0;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    private WebElement waitForElementPresent(By by, String errorMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private List<WebElement> waitForElementsPresent(By by, String errorMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    private boolean waitForElementNotPresent(By by, String errorMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String errorMsg, long timeoutSec) {
        WebElement element = waitForElementPresent(by, errorMsg, timeoutSec);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMsg, long timeoutSec) {
        WebElement element = waitForElementPresent(by, errorMsg, timeoutSec);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String errorMsg, long timeoutSec) {
        WebElement element = waitForElementPresent(by, errorMsg, timeoutSec);
        element.clear();
        return element;
    }
}
