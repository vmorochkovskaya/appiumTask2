package lib.ui;

import io.appium.java_client.AppiumDriver;

public class StartPageObject extends MainPageObject {
    private static final String MY_LISTS = "accessibilityId:My lists";

    private static final String LIST_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'%1$s')]";

    public StartPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickOnMyLists() {
        this.waitForElementAndClick(MY_LISTS, "'Lists' button didn't appear", 5);
    }

    public void clickOnList(String list) {
        this.waitForElementAndClick(String.format(LIST_BY_SUBSTRING_TPL, list), String.format("'%1$s' didn't appear", list), 15);
    }
}
