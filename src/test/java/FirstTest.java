import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

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
    public void firstTest() {
        System.out.print("first test run");
    }
}
