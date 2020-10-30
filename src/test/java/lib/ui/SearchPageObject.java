package lib.ui;

import io.appium.java_client.AppiumDriver;

public abstract class SearchPageObject extends MainPageObject {
    protected static String SEARCH_INPUT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULTS,
            FILLED_SEARCH_ELEMENT;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INPUT_ELEMENT, "Cannot find search Wiki input", 5);
        this.waitForElementPresent(SEARCH_INPUT, "Cannot find search input after clicking search init element", 5);
    }

    public void typeSearchLine(String value) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, value, "Cannot find and type search input", 5);
    }

    public void assertSearchResultsDisplayed(String substring) {
        this.assertElementDisplayed(String.format(SEARCH_RESULT_BY_SUBSTRING_TPL, substring),
                String.format("Articles were not found searching by '%1$s'", substring));
    }

    public void assertSearchResultsNotDisplayed(String substring) {
        this.assertElementsNotDisplayed(String.format(SEARCH_RESULT_BY_SUBSTRING_TPL, substring),
                "Articles are still displayed");
    }

    public void clearSearch() {
        this.waitForElementAndClear(FILLED_SEARCH_ELEMENT, "Cannot find search field", 5);
    }

    public void clickArticle(String article) {
        this.waitForElementAndClick(String.format(SEARCH_RESULT_BY_SUBSTRING_TPL, article),
                String.format("Article '%1$s' wasn't found", article), 5);
    }
}
