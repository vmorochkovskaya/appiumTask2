package lib.ui;

import io.appium.java_client.AppiumDriver;

public abstract class ArticlesListPageObject extends MainPageObject {
    protected static String ARTICLE_BY_SUBSTRING_TPL;

    public ArticlesListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public abstract void removeArticle(String article);

    public void waitForArticleToDisappearByTitle(String article) {
        this.waitForElementNotPresent(getArticleElement(article), "Saved article still present with title " + article, 10);
    }

    public void assertArticleDisplayed(String article) {
        this.assertElementsDisplayed(getArticleElement(article),  String.format("'%1$s' article isn't displayed in articles list", article));
    }

    public void clickOnArticle(String article) {
        this.waitForElementAndClick(getArticleElement(article), String.format("'%1$s' article isn't displayed in articles list", article), 5);
    }

    protected String getArticleElement(String article) {
        return String.format(ARTICLE_BY_SUBSTRING_TPL, article);
    }
}
