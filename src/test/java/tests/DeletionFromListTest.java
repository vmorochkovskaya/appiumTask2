package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.ArticlesListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.mobile_web.AuthorisationPageObject;
import org.junit.Assert;
import org.junit.Test;

public class DeletionFromListTest extends CoreTestCase {
    private final static String LOGIN = "AutoTester";
    private final static String PASSWORD = "auto-tester";

    protected void setup() {
    }

    @Test
    public void testArticleAfterDeletionOneFromTwo() {
        String listName = "My new List";
        String javaArticle = "Java";
        String appiumArticle = "Appium";

        searchForArticleAndAddToList(javaArticle, listName);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        if (!Platform.getInstance().isMW()) {
            articlePageObject.clickClose();
        }

        searchForArticleAndAddToList(appiumArticle, listName);

        if (!Platform.getInstance().isMW()) {
            articlePageObject.clickClose();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.navigateToMyLists();
        ArticlesListPageObject articlesListPageObject = ArticlesListPageObjectFactory.get(driver);
        articlesListPageObject.clickOnList(listName);
        articlesListPageObject.removeArticle(javaArticle);
        articlesListPageObject.assertArticleDisplayed(appiumArticle);
        articlesListPageObject.clickOnArticle(appiumArticle);

        articlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            String articleTitle = articlePageObject.waitAndGetArticleTitle();
            Assert.assertEquals("Article title isn't equals to expected after choosing from list", appiumArticle, articleTitle);
        } else {
            Assert.assertTrue(String.format("Article %1$s doesn't have 'Saved' bottom icon", appiumArticle), articlePageObject.isArticleAddedToList());
        }
    }

    private void searchForArticleAndAddToList(String article, String listName) {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(article);

        searchPageObject.clickArticle(article);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForArticleTitle(article);
        articlePageObject.addArticlesToReadingList(listName);
        if (Platform.getInstance().isMW()) {
            AuthorisationPageObject authorisationPageObject = new AuthorisationPageObject(driver);
            if (!authorisationPageObject.isAuthorised()) {
                authorisationPageObject.clickAuthButton();
                authorisationPageObject.enterLoginData(LOGIN, PASSWORD);
                authorisationPageObject.submitForm();
                articlePageObject.waitForArticleTitle(article);
                articlePageObject.addArticlesToReadingList(listName);
            }
        }
    }
}
