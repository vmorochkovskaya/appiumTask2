package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
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
        driver = getDriverByPlatform();
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    private AppiumDriver getDriverByPlatform() throws Exception {
        DesiredCapabilities desiredCapabilities = getCapabilitiesByPlatform();
        return System.getProperty("PLATFORM").equals("iOS") ? new IOSDriver(new URL(URL), desiredCapabilities) : new AndroidDriver(new URL(URL), desiredCapabilities);
    }

    private DesiredCapabilities getCapabilitiesByPlatform() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if (System.getProperty("PLATFORM").equals("iOS")) {
            desiredCapabilities.setCapability("platformName", "iOS");
            desiredCapabilities.setCapability("deviceName", "iPhone 11 Pro");
            desiredCapabilities.setCapability("platformVersion", "13.3");
            desiredCapabilities.setCapability("automationName", "XCUITest");
            desiredCapabilities.setCapability("bundleId", "org.wikimedia.wikipedia");
        } else {
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("deviceName", "Android_Emulator");
            desiredCapabilities.setCapability("platformVersion", "8.0");
            desiredCapabilities.setCapability("automationName", "Appium");
            desiredCapabilities.setCapability("app", System.getProperty("user.dir") + "\\src\\test\\resources\\apks\\org.wikipedia.apk");
        }
        return desiredCapabilities;
    }

    @Override
    public void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }
}
