package lib.ui;

import io.appium.java_client.AppiumDriver;
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

public class MainPageObject {
    public AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public void assertElementPresent(By by, String errorMsg) {
        List elementsList = driver.findElements(by);
        Assert.assertTrue(errorMsg, elementsList.size() > 0);
    }

    public void assertElementHasText(By by, String expectedText, String errorMsg) {
        WebElement element = waitForElementPresent(by, errorMsg, 5);
        Assert.assertEquals(errorMsg, expectedText, element.getText());
    }

    public void assertElementsDisplayed(By by, String errorMsg) {
        Assert.assertTrue(errorMsg, isElementsPresent(by, errorMsg, 5));
    }

    public void assertElementsNotDisplayed(By by, String errorMsg) {
        Assert.assertTrue(errorMsg, isElementsNotPresent(by, errorMsg, 5));
    }

    public boolean isElementsPresent(By by, String errorMsg, long timeoutSec) {
        try {
            waitForElementsPresent(by, errorMsg, timeoutSec);
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public boolean isElementsNotPresent(By by, String errorMsg, long timeoutSec) {
        try {
            waitForElementNotPresent(by, errorMsg, timeoutSec);
            return driver.findElements(by).size() == 0;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public WebElement waitForElementPresent(By by, String errorMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> waitForElementsPresent(By by, String errorMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public boolean waitForElementNotPresent(By by, String errorMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(By by, String errorMsg, long timeoutSec) {
        WebElement element = waitForElementPresent(by, errorMsg, timeoutSec);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorMsg, long timeoutSec) {
        WebElement element = waitForElementPresent(by, errorMsg, timeoutSec);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClear(By by, String errorMsg, long timeoutSec) {
        WebElement element = waitForElementPresent(by, errorMsg, timeoutSec);
        element.clear();
        return element;
    }

    public void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage, 15);
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
