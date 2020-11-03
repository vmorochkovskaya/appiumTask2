package lib.ui.ios;

import lib.ui.ArticlesListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlesListPageObject extends ArticlesListPageObject {
    static {
        ARTICLE_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeCell[.//XCUIElementTypeLink[contains(@name, '%1$s')]]";
    }

    public IOSArticlesListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public void removeArticle(String article) {
        this.swipeElementToLeft(getArticleElement(article), String.format("'%1$s' article isn't displayed in articles list", article));
        this.clickElementToRightUpperCorner(String.format(ARTICLE_BY_SUBSTRING_TPL, article), "Cannot find saved article");
        this.waitForArticleToDisappearByTitle(article);
    }

    @Override
    public void clickOnList(String list) {
        System.out.println("Method 'clickOnList' doesn't have implementation for ios");
    }
}
