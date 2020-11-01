package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.StartPageObject;
import lib.ui.android.AndroidStartPageObject;
import lib.ui.ios.IOSStartPageObject;

public class StartPageObjectFactory {
    public static StartPageObject get(AppiumDriver driver){
        if (Platform.getInstance().isIos()) {
            return new IOSStartPageObject(driver);
        } else {
            return new AndroidStartPageObject(driver);
        }
    }
}
