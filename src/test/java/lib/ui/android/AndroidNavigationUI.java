package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
    static {
        MY_LISTS = "accessibilityId:My lists";
    }

    public AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void navigateToMyLists() {
        this.waitForElementAndClick(MY_LISTS, "'Lists' button didn't appear", 5);
    }
}
