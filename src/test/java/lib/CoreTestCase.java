package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;
    protected Platform platform;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.platform = Platform.getInstance();
        driver = this.platform.getDriver();
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Override
    public void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }
}
