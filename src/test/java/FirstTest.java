import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
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
import java.time.Duration;
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
//        desiredCapabilities.setCapability("appPackage", "org.wikipedia");
//        desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
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

    @Test
    public void testArticleAfterDeletionOneFromTwo() {
        String listName = "My new List";
        String javaArticle = "Java";
        String appiumArticle = "Appium";
        By closeButtonLocator = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");

        searchForArticleAndAddToList(javaArticle, listName);

        waitForElementAndClick(
                closeButtonLocator,
                "'Close' button didn't appear",
                5);

        searchForArticleAndAddToList(appiumArticle, listName);

        waitForElementAndClick(
                closeButtonLocator,
                "'Close' button didn't appear",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "'Lists' button didn't appear",
                5);

        waitForElementAndClick(
                By.xpath(String.format("//*[@text='%1$s']", listName)),
                String.format("'%1$s' didn't appear", listName),
                5);

        String articleLocator =
                "(//*[@resource-id='org.wikipedia:id/page_list_item_container']//android.widget.LinearLayout)[.//android.widget.TextView[@text='%1$s']][2]";
        swipeElementToLeft(
                By.xpath(String.format(articleLocator, javaArticle)),
                String.format("'%1$s' article didn't appear", javaArticle));

        assertElementsDisplayed(
                By.xpath(String.format(articleLocator, appiumArticle)),
                String.format("'%1$s' article isn't displayed in articles list", appiumArticle));

        waitForElementAndClick(
                By.xpath(String.format(articleLocator, appiumArticle)),
                String.format("'%1$s' article isn't displayed in articles list", appiumArticle),
                5);

        WebElement elementArticleTitle = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Article title didn't appear",
                5);

        String articleTitle = elementArticleTitle.getText();

        Assert.assertEquals("Article title isn't equals to expected after choosing from list", appiumArticle, articleTitle);
    }

    @Test
    public void testArticleHasTitle() {
        String article = "Java";

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search Wiki input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                article,
                "Cannot find search input",
                5);

        waitForElementAndClick(
                By.xpath(String.format("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='%1$s']", article)),
                String.format("Articles were not found searching by '%1$s'", article),
                5);

        waitForElementPresent(By.id("org.wikipedia:id/page_contents_container"), "Article screen didn't appear", 5);

        assertElementPresent(By.id("org.wikipedia:id/view_page_title_text"), String.format("'%1$s' article doesn't have title", article));
    }

    private void searchForArticleAndAddToList(String article, String listName) {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search Wiki input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                article,
                "Cannot find search input",
                5);

        waitForElementAndClick(
                By.xpath(String.format("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='%1s']", article)),
                String.format("Articles were not found searching by '%1$s'", article),
                5);

        waitForElementAndClick(
                MobileBy.AccessibilityId("More options"),
                "'More options' button didn't appear",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "'Add to reading list' button didn't appear",
                5);


        if (!isElementsPresent(By.id("org.wikipedia:id/onboarding_button"), "'Got it' button didn't appear", 5)) {


            waitForElementAndClick(
                    By.xpath(String.format("//android.widget.TextView[@text='%1$s']", listName)),
                    String.format("Suggested list %1$s didn't appear", listName),
                    5);
        } else {

            waitForElementAndClick(
                    By.id("org.wikipedia:id/onboarding_button"),
                    "'Got it' button didn't appear",
                    5);

            By overlayInoutBy = By.id("org.wikipedia:id/text_input");

            waitForElementAndClear(
                    overlayInoutBy,
                    "'Overlay input' didn't appear",
                    5);

            waitForElementAndSendKeys(
                    overlayInoutBy,
                    listName,
                    "'Overlay input' didn't appear",
                    5);

            waitForElementAndClick(
                    By.xpath("//android.widget.Button[@text='OK']"),
                    "'OK' button didn't appear",
                    5);
        }
    }

    private void assertElementPresent(By by, String errorMsg) {
        List elementsList = driver.findElements(by);
        Assert.assertTrue(errorMsg, elementsList.size() > 0);
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

    private void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage, 5);
        int elementLeftX = element.getLocation().getX();
        int elementRightX = elementLeftX + element.getSize().getWidth();
        int elementUpperY = element.getLocation().getY();
        int elementLowerY = elementUpperY + element.getSize().getHeight();
        int elementMiddleY = (elementUpperY + elementLowerY) / 2;

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(elementRightX, elementMiddleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
                .moveTo(PointOption.point(elementLeftX, elementMiddleY))
                .release()
                .perform();
    }
}
