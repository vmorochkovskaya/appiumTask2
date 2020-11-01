package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.ArticlesListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.StartPageObjectFactory;
import org.junit.Test;

public class FirstTest extends CoreTestCase {
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

    @Test
    public void testArticleAfterDeletionOneFromTwo() {
        String listName = "My new List";
        String javaArticle = "Java";
        String appiumArticle = "Appium";

        searchForArticleAndAddToList(javaArticle, listName);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.clickClose();

        searchForArticleAndAddToList(appiumArticle, listName);

        articlePageObject.clickClose();

        StartPageObject startPageObject = StartPageObjectFactory.get(driver);
        startPageObject.clickOnMyLists();
        startPageObject.clickOnList(listName);

        ArticlesListPageObject articlesListPageObject = ArticlesListPageObjectFactory.get(driver);
        articlesListPageObject.removeArticle(javaArticle);
        articlesListPageObject.assertArticleDisplayed(appiumArticle);
        articlesListPageObject.clickOnArticle(appiumArticle);

        articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForArticleTitle(appiumArticle);
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

    private void searchForArticleAndAddToList(String article, String listName) {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(article);
        searchPageObject.clickArticle(article);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForArticleTitle(article);
        articlePageObject.addArticlesToReadingList(listName);
    }
}
