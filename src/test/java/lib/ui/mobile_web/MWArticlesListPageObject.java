package lib.ui.mobile_web;

import lib.ui.ArticlesListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlesListPageObject extends ArticlesListPageObject {
    static {
        ARTICLE_BY_SUBSTRING_TPL = "xpath://li[@title='%1$s']";
        LIST_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'%1$s')]";
        REMOVE_FROM_LIST_BUTTON = "xpath://li[@title='%1$s']//a[contains(@class, 'mw-ui-icon-wikimedia-unStar-progressive')]";
    }

    public MWArticlesListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public void removeArticle(String article) {
        this.waitForElementClickable(String.format(REMOVE_FROM_LIST_BUTTON, article),
                String.format("'Unstar %1$s title' button isn't clickable", article), 10);
        this.waitForElementAndClick(String.format(REMOVE_FROM_LIST_BUTTON, article),
                String.format("Cannot find and click 'Unstar %1$s title' button", article), 10);
        this.driver.navigate().refresh();
    }

    @Override
    public void clickOnList(String list) {
        System.out.println("Method 'clickOnList' doesn't have implementation for ios");
    }
}
