package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI {
    static {
        MY_LISTS = "id:Saved";
    }

    public IOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void navigateToMyLists() {
        this.waitForElementAndClick(MY_LISTS, "'Lists' button didn't appear", 5);
    }
}
