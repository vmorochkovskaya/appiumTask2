package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.ArticlesListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.StartPageObjectFactory;
import org.junit.Test;

public class DeletionFromListTest extends CoreTestCase {

    protected void setup(){
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
