package lib.ui.mobile_web;

import lib.ui.MainPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorisationPageObject extends MainPageObject {
    private static final String LOGIN_BUTTON = "xpath://a[text()='Log in']";
    private static final String USERNAME_INPUT = "css:input[name='wpName']";
    private static final String PASSWORD_INPUT = "css:input[name='wpPassword']";
    private static final String SUBMIT_BUTTON = "css:#wpLoginAttempt";

    public AuthorisationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 10);
        this.waitForElementClickable(LOGIN_BUTTON, "auth button isn't clickable", 10);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 10);
    }

    public boolean isAuthorised() {
        return !this.isElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementClickable(USERNAME_INPUT, "''Username' input isn't clickable", 10);
        this.waitForElementAndClick(USERNAME_INPUT, "Cannot find and click 'Username' input", 10);
        this.waitForElementAndSendKeys(USERNAME_INPUT, login, "Cannot find and send keys to 'Username' input", 10);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and send keys to 'Password' input", 10);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit button", 10);
    }
}
