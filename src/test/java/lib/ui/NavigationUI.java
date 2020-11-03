package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class NavigationUI extends MainPageObject {
    protected static String MY_LISTS;
    protected static String OPEN_NAVIGATION;


    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    abstract public void navigateToMyLists();
}
