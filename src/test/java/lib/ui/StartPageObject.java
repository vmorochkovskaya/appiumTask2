package lib.ui;

import io.appium.java_client.AppiumDriver;

public abstract class StartPageObject extends MainPageObject {
    protected static String MY_LISTS;

    protected static String LIST_BY_SUBSTRING_TPL;

    public StartPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickOnMyLists() {
        this.waitForElementAndClick(MY_LISTS, "'Lists' button didn't appear", 5);
    }

    public abstract void clickOnList(String list);
}
