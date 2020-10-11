package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class StartPageObject extends MainPageObject {
    private static final By MY_LISTS = MobileBy.AccessibilityId("My lists");

    private static final String LIST_BY_SUBSTRING_TPL = "//*[contains(@text,'%1$s')]";

    public StartPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickOnMyLists(){
        this.waitForElementAndClick(MY_LISTS, "'Lists' button didn't appear", 5);
    }

    public void clickOnList(String list){
        this.waitForElementAndClick(By.xpath(String.format(LIST_BY_SUBSTRING_TPL, list)), String.format("'%1$s' didn't appear", list), 15);
    }
}
