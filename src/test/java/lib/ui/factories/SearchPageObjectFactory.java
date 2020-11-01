package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.IOSSearchPageObject;

public class SearchPageObjectFactory {

    public static SearchPageObject get(AppiumDriver driver){
        if (Platform.getInstance().isIos()) {
            System.out.println("fksdlkfd;lfk");
            return new IOSSearchPageObject(driver);
        } else {
            return new AndroidSearchPageObject(driver);
        }
    }
}
