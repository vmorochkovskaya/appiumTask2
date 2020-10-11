import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;
    private static final String URL = "http://localhost:4723/wd/hub";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Android_Emulator");
        desiredCapabilities.setCapability("platformVersion", "8.0");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("app", System.getProperty("user.dir") + "\\src\\test\\resources\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL(URL), desiredCapabilities);
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Override
    public void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }
}
