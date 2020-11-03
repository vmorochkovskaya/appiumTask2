package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INPUT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text, 'Search')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='%1$s']";
        SEARCH_RESULTS = "id:org.wikipedia:id/page_list_item_container";
        FILLED_SEARCH_ELEMENT = "id:org.wikipedia:id/search_src_text";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
