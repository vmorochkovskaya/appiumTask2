package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String MORE_OPTIONS = "accessibilityId:More options";
    private static final String ADD_TO_READING_LIST = "xpath://*[@text='Add to reading list']";
    private static final String GOT_IT = "id:org.wikipedia:id/onboarding_button";
    private static final String OVERLAY_INPUT =  "id:org.wikipedia:id/text_input";
    private static final String OK = "xpath://android.widget.Button[@text='OK']";
    private static final String CLOSE = "accessibilityId:Navigate up";
    private static final String TITLE = "id:org.wikipedia:id/view_page_title_text";
    private static final String CONTENT =  "id:org.wikipedia:id/page_contents_container";
    private static final String LIST_BY_SUBSTRING_TEMPLATE = "xpath://android.widget.TextView[@text='%1$s']";

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

    public void waitForScreenToBeOpened() {
        this.waitForElementPresent(CONTENT, "Article screen didn't appear", 5);
    }

    public void assertTitleCurrentlyPresent(String article) {
        this.assertElementPresent(TITLE, String.format("'%1$s' article doesn't have title", article));
    }
}
