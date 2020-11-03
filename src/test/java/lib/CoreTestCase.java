package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase extends TestCase {
    protected RemoteWebDriver driver;
    protected Platform platform;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.platform = Platform.getInstance();
        driver = this.platform.getDriver();
        rotatePortrait();
        navigateToWikiPage();
    }

    private void rotatePortrait() {
        if (driver instanceof AppiumDriver) {
            ((AppiumDriver) driver).rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method 'rotatePortrait' does nothing for platform " + Platform.getInstance().getPlatform());
        }
    }

    private void navigateToWikiPage(){
        if(!(driver instanceof AppiumDriver)){
            driver.get("https://en.m.wikipedia.org");
        }
    }

    @Override
    public void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }
}
