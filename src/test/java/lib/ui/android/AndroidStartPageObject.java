package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.StartPageObject;

public class AndroidStartPageObject extends StartPageObject {
    static {
        MY_LISTS = "accessibilityId:My lists";
        LIST_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'%1$s')]";
    }

    public AndroidStartPageObject(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public void clickOnList(String list) {
        this.waitForElementAndClick(String.format(LIST_BY_SUBSTRING_TPL, list), String.format("'%1$s' didn't appear", list), 15);
    }
}
