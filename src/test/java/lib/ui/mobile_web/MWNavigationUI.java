package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {

    static {
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
        MY_LISTS = "css:div#mw-mf-page-left a[data-event-name='menu.unStar']";
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public void navigateToMyLists() {
        this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click 'Open menu' link", 10);
        this.waitForElementClickable(MY_LISTS, "'WatchList' menu item isn't clickable", 10);
        this.waitForElementAndClick(MY_LISTS, "Cannot find and click 'WatchList' link", 10);
    }
}
