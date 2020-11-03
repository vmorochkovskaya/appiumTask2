package tests;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTest extends CoreTestCase {
    private MainPageObject mainPageObject;

    protected void setup() {
        mainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearchAndCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String article = "Java";
        searchPageObject.typeSearchLine(article);
        searchPageObject.assertSearchResultsDisplayed(article);
        searchPageObject.clearSearch();
        searchPageObject.assertSearchResultsNotDisplayed(article);
    }

}
