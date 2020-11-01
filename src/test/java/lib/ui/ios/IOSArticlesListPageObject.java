package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlesListPageObject;

public class IOSArticlesListPageObject extends ArticlesListPageObject {
    static {
        ARTICLE_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeCell[.//XCUIElementTypeLink[contains(@name, '%1$s')]]";
    }

    public IOSArticlesListPageObject(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public void removeArticle(String article) {
        this.swipeElementToLeft(getArticleElement(article), String.format("'%1$s' article isn't displayed in articles list", article));
        this.clickElementToRightUpperCorner(String.format(ARTICLE_BY_SUBSTRING_TPL, article), "Cannot find saved article");
        this.waitForArticleToDisappearByTitle(article);
    }
}
