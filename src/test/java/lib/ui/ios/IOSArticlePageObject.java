package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        ADD_TO_READING_LIST = "id:Save for later";
        CLOSE = "id:Back";
        TITLE = "id:%1$s";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public void addArticlesToReadingList(String listName) {
        this.waitForElementAndClick(ADD_TO_READING_LIST, "Cannot find option to add article to reading list", 5);
    }
}
