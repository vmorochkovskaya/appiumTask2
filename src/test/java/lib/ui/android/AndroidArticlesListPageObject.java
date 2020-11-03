package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlesListPageObject;

public class AndroidArticlesListPageObject extends ArticlesListPageObject {
    static {
        ARTICLE_BY_SUBSTRING_TPL =
                "xpath:(//*[@resource-id='org.wikipedia:id/page_list_item_container']//android.widget.LinearLayout)[.//android.widget.TextView[@text='%1$s']][2]";

    }

    public AndroidArticlesListPageObject(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public void removeArticle(String article) {
        this.swipeElementToLeft(getArticleElement(article), String.format("'%1$s' article isn't displayed in articles list", article));
        this.waitForArticleToDisappearByTitle(article);
    }
}
