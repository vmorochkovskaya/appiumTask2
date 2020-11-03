package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTitleTest extends CoreTestCase {

    protected void setup() {
    }

    @Test
    public void testArticleHasTitle() {
        String article = "Java";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(article);
        searchPageObject.clickArticle(article);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForScreenToBeOpened();
        articlePageObject.assertTitleCurrentlyPresent(article);
    }
}
