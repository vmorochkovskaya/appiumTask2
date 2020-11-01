package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlesListPageObject;
import lib.ui.android.AndroidArticlesListPageObject;
import lib.ui.ios.IOSArticlesListPageObject;

public class ArticlesListPageObjectFactory {
    public static ArticlesListPageObject get(AppiumDriver driver){
        if (Platform.getInstance().isIos()) {
            return new IOSArticlesListPageObject(driver);
        } else {
            return new AndroidArticlesListPageObject(driver);
        }
    }
}
