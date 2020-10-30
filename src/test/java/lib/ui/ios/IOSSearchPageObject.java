package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        String SEARCH_INPUT_ELEMENT = "accessibilityId:Search Wikipedia",
                SEARCH_INPUT = "ios_predicate_string:type == 'XCUIElementTypeSearchField'",
                SEARCH_RESULT_BY_SUBSTRING_TPL = "ios_predicate_string:name beginswith '%1$s'",
                SEARCH_RESULTS = "ios_predicate_string:type == 'XCUIElementTypeCollectionView'",
                FILLED_SEARCH_ELEMENT = "ios_predicate_string:type = 'XCUIElementTypeSearchField'";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}