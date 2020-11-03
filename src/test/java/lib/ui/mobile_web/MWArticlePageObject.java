package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        MORE_OPTIONS = "accessibilityId:More options";
        ADD_TO_READING_LIST =
                "xpath://a[@id='ca-watch' and contains(@class, 'menu__item--page-actions-watch') and not(contains(@class, 'watched'))]";
        ADDED_TO_READING_LIST_ICON = "xpath://a[@id='ca-watch' and contains(@class, 'watched')]";
        TITLE = "xpath://div[contains(@class, 'page-heading')]//h1[contains(text(), '%1$s')]";
        LIST_BY_SUBSTRING_TEMPLATE = "xpath://android.widget.TextView[@text='%1$s']";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public void addArticlesToReadingList(String listName) {
        this.waitForElementClickable(ADD_TO_READING_LIST, "'Add article to reading list' button isn't clickable", 10);
        this.waitForElementAndClick(ADD_TO_READING_LIST, "Cannot find option to add article to reading list", 10);
    }
}
