package lib.ui;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject {
    private static final String SEARCH_INPUT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='%1$s']",
            SEARCH_RESULTS = "org.wikipedia:id/page_list_item_container",
            FILLED_SEARCH_ELEMENT = "org.wikipedia:id/search_src_text";

    private static final String IOS_SEARCH_INPUT_ELEMENT = "accessibilityId:Search Wikipedia",
            IOS_SEARCH_INPUT = "ios_predicate_string:type == 'XCUIElementTypeSearchField'",
            IOS_SEARCH_RESULT_BY_SUBSTRING_TPL = "ios_predicate_string:name beginswith '%1$s'",
            IOS_SEARCH_RESULTS = "ios_predicate_string:type == 'XCUIElementTypeCollectionView'",
            IOS_FILLED_SEARCH_ELEMENT = "ios_predicate_string:type = 'XCUIElementTypeSearchField'";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(isIOs() ? IOS_SEARCH_INPUT_ELEMENT : SEARCH_INPUT_ELEMENT, "Cannot find search Wiki input", 5);
        this.waitForElementPresent(isIOs() ? IOS_SEARCH_INPUT : SEARCH_INPUT, "Cannot find search input after clicking search init element", 5);
    }

    public void typeSearchLine(String value) {
        this.waitForElementAndSendKeys(isIOs() ? IOS_SEARCH_INPUT : SEARCH_INPUT, value, "Cannot find and type search input", 5);
    }

    public void assertSearchResultsDisplayed(String substring) {
        this.assertElementDisplayed(String.format(isIOs() ? IOS_SEARCH_RESULT_BY_SUBSTRING_TPL : SEARCH_RESULT_BY_SUBSTRING_TPL, substring),
                String.format("Articles were not found searching by '%1$s'", substring));
    }

    public void assertSearchResultsNotDisplayed(String substring) {
        this.assertElementsNotDisplayed(String.format(isIOs() ? IOS_SEARCH_RESULT_BY_SUBSTRING_TPL : SEARCH_RESULT_BY_SUBSTRING_TPL, substring),
                "Articles are still displayed");
    }

    public void clearSearch() {
        this.waitForElementAndClear(isIOs() ? IOS_FILLED_SEARCH_ELEMENT : FILLED_SEARCH_ELEMENT, "Cannot find search field", 5);
    }

    public void clickArticle(String article) {
        this.waitForElementAndClick(String.format(isIOs() ? IOS_SEARCH_RESULT_BY_SUBSTRING_TPL : SEARCH_RESULT_BY_SUBSTRING_TPL, article),
                String.format("Article '%1$s' wasn't found", article), 5);
    }
}
