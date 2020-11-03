package lib.ui.android;

import lib.ui.ArticlesListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlesListPageObject extends ArticlesListPageObject {
    static {
        ARTICLE_BY_SUBSTRING_TPL =
                "xpath:(//*[@resource-id='org.wikipedia:id/page_list_item_container']//android.widget.LinearLayout)[.//android.widget.TextView[@text='%1$s']][2]";
        LIST_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'%1$s')]";
    }

    public AndroidArticlesListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public void removeArticle(String article) {
        this.swipeElementToLeft(getArticleElement(article), String.format("'%1$s' article isn't displayed in articles list", article));
        this.waitForArticleToDisappearByTitle(article);
    }

    @Override
    public void clickOnList(String list) {
        this.waitForElementAndClick(String.format(LIST_BY_SUBSTRING_TPL, list), String.format("'%1$s' didn't appear", list), 15);
    }
}
