package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.StartPageObject;

public class IOSStartPageObject extends StartPageObject {
    static {
        MY_LISTS = "id:Saved";
    }

    public IOSStartPageObject(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public void clickOnList(String list) {
        System.out.println("Method 'clickOnList' doesn't have implementation for ios");
    }
}
