package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INPUT_ELEMENT = "css:#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://a[@data-title='%1$s']";
        SEARCH_RESULTS = "css:ul.page-list";
        FILLED_SEARCH_ELEMENT = "css:#searchIcon";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
