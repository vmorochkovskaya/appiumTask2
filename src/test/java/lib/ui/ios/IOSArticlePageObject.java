package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        ADD_TO_READING_LIST = "id:Save for later";
        CLOSE = "id:Back";
        TITLE = "id:%1$s";
        ADDED_TO_READING_LIST_ICON = "id:Saved. Activate to unsave.";
    }

    public IOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public void addArticlesToReadingList(String listName) {
        this.waitForElementAndClick(ADD_TO_READING_LIST, "Cannot find option to add article to reading list", 5);
    }
}
