package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final By MORE_OPTIONS = MobileBy.AccessibilityId("More options");
    private static final By ADD_TO_READING_LIST =  By.xpath("//*[@text='Add to reading list']");
    private static final By GOT_IT = By.id("org.wikipedia:id/onboarding_button");
    private static final By OVERLAY_INPUT =  By.id("org.wikipedia:id/text_input");
    private static final By OK = By.xpath("//android.widget.Button[@text='OK']");
    private static final By CLOSE = MobileBy.AccessibilityId("Navigate up");
    private static final By TITLE = By.id("org.wikipedia:id/view_page_title_text");
    private static final By CONTENT =  By.id("org.wikipedia:id/page_contents_container");
    private static final String LIST_BY_SUBSTRING_TEMPLATE = "//android.widget.TextView[@text='%1$s']";

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
        this.waitForElementAndClick(By.xpath(String.format(LIST_BY_SUBSTRING_TEMPLATE, list)),
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
