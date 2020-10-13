package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    public AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public void assertElementPresent(String locator, String errorMsg) {
        List elementsList = driver.findElements(getLocatorByType(locator));
        Assert.assertTrue(errorMsg, elementsList.size() > 0);
    }

    public void assertElementDisplayed(String locator, String errorMsg) {
        Assert.assertTrue(errorMsg, isElementPresent(locator, errorMsg, 15));
    }

    public void assertElementsDisplayed(String locator, String errorMsg) {
        Assert.assertTrue(errorMsg, isElementsPresent(locator, errorMsg, 15));
    }

    public void assertElementsNotDisplayed(String locator, String errorMsg) {
        Assert.assertTrue(errorMsg, isElementsNotPresent(locator, errorMsg, 5));
    }

    public boolean isElementPresent(String locator, String errorMsg, long timeoutSec) {
        try {
            waitForElementPresent(locator, errorMsg, timeoutSec);
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public boolean isElementsPresent(String locator, String errorMsg, long timeoutSec) {
        try {
            waitForElementsPresent(locator, errorMsg, timeoutSec);
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public boolean isElementsNotPresent(String locator, String errorMsg, long timeoutSec) {
        try {
            waitForElementNotPresent(locator, errorMsg, timeoutSec);
            return driver.findElements(getLocatorByType(locator)).size() == 0;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public WebElement waitForElementPresent(String locator, String errorMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(getLocatorByType(locator)));
    }

    public List<WebElement> waitForElementsPresent(String locator, String errorMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getLocatorByType(locator)));
    }

    public boolean waitForElementNotPresent(String locator, String errorMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(getLocatorByType(locator)));
    }

    public WebElement waitForElementAndClick(String locator, String errorMsg, long timeoutSec) {
        WebElement element = waitForElementPresent(locator, errorMsg, timeoutSec);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMsg, long timeoutSec) {
        WebElement element = waitForElementPresent(locator, errorMsg, timeoutSec);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClear(String locator, String errorMsg, long timeoutSec) {
        WebElement element = waitForElementPresent(locator, errorMsg, timeoutSec);
        element.clear();
        return element;
    }

    public void swipeElementToLeft(String locator, String errorMessage) {
        WebElement element = waitForElementPresent(locator, errorMessage, 15);
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

    protected boolean isIOs() {
        return System.getProperty("PLATFORM").equals("iOS");
    }

    private By getLocatorByType(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String type = explodedLocator[0];
        String locator = explodedLocator[1];
        switch (type) {
            case "xpath":
                return By.xpath(locator);
            case "id":
                return By.id(locator);
            case "accessibilityId":
                return MobileBy.AccessibilityId(locator);
            case "ios_predicate_string":
                return MobileBy.iOSNsPredicateString(locator);
            default:
                throw new IllegalArgumentException("Cannot get type of locator");
        }
    }
}
