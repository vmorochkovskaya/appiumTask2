package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        MORE_OPTIONS = "accessibilityId:More options";
        ADD_TO_READING_LIST = "xpath://*[@text='Add to reading list']";
        GOT_IT = "id:org.wikipedia:id/onboarding_button";
        OVERLAY_INPUT = "id:org.wikipedia:id/text_input";
        OK = "xpath://android.widget.Button[@text='OK']";
        CLOSE = "accessibilityId:Navigate up";
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        CONTENT = "id:org.wikipedia:id/page_contents_container";
        LIST_BY_SUBSTRING_TEMPLATE = "xpath://android.widget.TextView[@text='%1$s']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public void addArticlesToReadingList(String listName) {
        clickMoreOptions();
        clickAddToReadingList();

        if (!isGotItPresent()) {
            clickOnList(listName);
        } else {
            clickGotIt();
            clearOverlay();
            typeInOverlay(listName);
            clickOk();
        }
    }
}
