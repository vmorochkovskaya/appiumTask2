package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public abstract class ArticlePageObject extends MainPageObject {
    protected static String MORE_OPTIONS;
    protected static String ADD_TO_READING_LIST;
    protected static String GOT_IT;
    protected static String OVERLAY_INPUT;
    protected static String OK;
    protected static String CLOSE;
    protected static String TITLE;
    protected static String CONTENT;
    protected static String LIST_BY_SUBSTRING_TEMPLATE;
    protected static String ADDED_TO_READING_LIST_ICON;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickMoreOptions(){
        this.waitForElementAndClick(MORE_OPTIONS, "'More options' button didn't appear", 5);
    }

    public void clickAddToReadingList(){
        this.waitForElementAndClick(ADD_TO_READING_LIST, "'Add to reading list' button didn't appear", 15);
    }

    public boolean isGotItPresent(){
        return this.isElementsPresent(GOT_IT, "'Got it' button didn't appear", 5);
    }

    public void clickGotIt(){
        this.waitForElementAndClick(GOT_IT, "'Got it' button didn't appear", 5);
    }

    public void clickOnList(String list){
        this.waitForElementAndClick(String.format(LIST_BY_SUBSTRING_TEMPLATE, list),
                String.format("Suggested list %1$s didn't appear", list), 5);
    }

    public void clearOverlay(){
        this.waitForElementAndClear(OVERLAY_INPUT, "'Overlay input' didn't appear", 5);
    }

    public void typeInOverlay(String list){
        this.waitForElementAndSendKeys(OVERLAY_INPUT, list, "'Overlay input' didn't appear", 5);
    }

    public void clickOk(){
        this.waitForElementAndClick(OK, "'OK' button didn't appear", 5);
    }

    public void clickClose(){
        this.waitForElementAndClick(CLOSE, "'Close' button didn't appear", 5);
    }

    public String waitAndGetArticleTitle() {
        WebElement elementArticleTitle = this.waitForElementPresent(TITLE, "Article title didn't appear", 5);
        return elementArticleTitle.getText();
    }

    public void waitForArticleTitle(String title) {
        this.waitForElementPresent(String.format(TITLE, title), String.format("Article title %1$s didn't appear", title), 5);
    }

    public boolean isArticleAddedToList() {
        return this.isElementPresent(ADDED_TO_READING_LIST_ICON, "Article doesn't have 'Saved' icon", 5);
    }

    public abstract void addArticlesToReadingList(String listName);

    public void waitForScreenToBeOpened() {
        this.waitForElementPresent(CONTENT, "Article screen didn't appear", 5);
    }

    public void assertTitleCurrentlyPresent(String article) {
        this.assertElementPresent(TITLE, String.format("'%1$s' article doesn't have title", article));
    }
}
