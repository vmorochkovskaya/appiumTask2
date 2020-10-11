package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    private static final String SEARCH_INPUT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='%1$s']",
            SEARCH_RESULTS = "org.wikipedia:id/page_list_item_container",
            FILLED_SEARCH_ELEMENT = "org.wikipedia:id/search_src_text";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INPUT_ELEMENT), "Cannot find search Wiki input", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Cannot find search input after clicking search init element", 5);
    }

    public void typeSearchLine(String value) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), value, "Cannot find and type search input", 5);
    }

    public void assertSearchResultsDisplayed(String substring) {
        this.assertElementsDisplayed(By.xpath(String.format(SEARCH_RESULT_BY_SUBSTRING_TPL, substring)),
                String.format("Articles were not found searching by '%1$s'", substring));
    }

    public void assertSearchResultsNotDisplayed() {
        this.assertElementsNotDisplayed(By.xpath(SEARCH_RESULTS), "Articles are still displayed");
    }

    public void clearSearch() {
        this.waitForElementAndClear(By.id(FILLED_SEARCH_ELEMENT), "Cannot find search field", 5);
    }

    public void clickArticle(String article) {
        this.waitForElementAndClick(By.xpath(String.format(SEARCH_RESULT_BY_SUBSTRING_TPL, article)),
                String.format("Article '%1$s' wasn't found", article),5);
    }
}
