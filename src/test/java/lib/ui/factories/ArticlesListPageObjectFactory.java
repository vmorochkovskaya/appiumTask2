package lib.ui.factories;

import lib.Platform;
import lib.ui.ArticlesListPageObject;
import lib.ui.android.AndroidArticlesListPageObject;
import lib.ui.ios.IOSArticlesListPageObject;
import lib.ui.mobile_web.MWArticlesListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlesListPageObjectFactory {
    public static ArticlesListPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isIos()) {
            return new IOSArticlesListPageObject(driver);
        } else if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlesListPageObject(driver);
        } else {
            return new MWArticlesListPageObject(driver);
        }
    }
}
