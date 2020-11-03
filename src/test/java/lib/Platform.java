package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Platform {
    private static final String ANDROID_PLATFORM = "android";
    private static final String IOS_PLATFORM = "ios";
    private static final String MOBILE_WEB_PLATFORM = "mobile_web";

    private static final String APPIUM_URL = "http://localhost:4723/wd/hub";
    private static Platform instance;

    private Platform() {
    }

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public boolean isAndroid() {
        return isPlatform(ANDROID_PLATFORM);
    }

    public boolean isIos() {
        return isPlatform(IOS_PLATFORM);
    }

    public boolean isMW() {
        return isPlatform(MOBILE_WEB_PLATFORM);
    }

    public RemoteWebDriver getDriver() throws Exception {
        URL url = new URL(APPIUM_URL);
        if (isAndroid()) {
            return new AndroidDriver(url, getAndroidCapabilities());
        } else if (isIos()) {
            return new IOSDriver(url, getIosCapabilities());
        } else if (isMW()) {
            RemoteWebDriver driver = new ChromeDriver(getChromeOptions());
            driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            return driver;
        } else {
            throw new Exception("Cannot detect type of driver");
        }
    }

    private DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Android_Emulator");
        desiredCapabilities.setCapability("platformVersion", "11.0");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("app", System.getProperty("user.dir") + "\\src\\test\\resources\\apks\\org.wikipedia.apk");
        return desiredCapabilities;
    }

    private DesiredCapabilities getIosCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("deviceName", "iPhone 11 Pro");
        desiredCapabilities.setCapability("platformVersion", "13.3");
        desiredCapabilities.setCapability("automationName", "XCUITest");
        desiredCapabilities.setCapability("bundleId", "org.wikimedia.wikipedia");
        return desiredCapabilities;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");
        return chromeOptions;
    }

    public String getPlatform() {
        return System.getProperty("PLATFORM");
    }

    private boolean isPlatform(String platform) {
        return platform.equals(getPlatform());
    }
}
