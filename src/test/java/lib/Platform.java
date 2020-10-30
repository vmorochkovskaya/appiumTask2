package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {
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
        return isPlatform("android");
    }

    public boolean isIos() {
        return isPlatform("ios");
    }

    public AppiumDriver getDriver() throws Exception {
        URL url = new URL(APPIUM_URL);
        if (isAndroid()) {
            return new AndroidDriver(url, getAndroidCapabilities());
        } else if (isIos()) {
            return new IOSDriver(url, getIosCapabilities());
        } else throw new Exception("Cannot detect type of driver");
    }

    private DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Android_Emulator");
        desiredCapabilities.setCapability("platformVersion", "8.0");
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

    private String getPlatform() {
        return System.getProperty("PLATFORM");
    }

    private boolean isPlatform(String platform) {
        return platform.equals(getPlatform());
    }
}
