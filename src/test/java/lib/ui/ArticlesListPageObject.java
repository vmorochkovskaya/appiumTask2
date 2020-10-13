package lib.ui;

import io.appium.java_client.AppiumDriver;

public class ArticlesListPageObject extends MainPageObject {
    private static final String ARTICLE_BY_SUBSTRING_TPL =
            "(//*[@resource-id='org.wikipedia:id/page_list_item_container']//android.widget.LinearLayout)[.//android.widget.TextView[@text='%1$s']][2]";

    public ArticlesListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void removeArticle(String article) {
        this.swipeElementToLeft(getArticleElement(article), String.format("'%1$s' article isn't displayed in articles list", article));
    }

    public void assertArticleDisplayed(String article) {
        this.assertElementsDisplayed(getArticleElement(article),  String.format("'%1$s' article isn't displayed in articles list", article));
    }

    public void clickOnArticle(String article) {
        this.waitForElementAndClick(getArticleElement(article), String.format("'%1$s' article isn't displayed in articles list", article), 5);
    }

    private String getArticleElement(String article) {
        return String.format(ARTICLE_BY_SUBSTRING_TPL, article);
    }
}
