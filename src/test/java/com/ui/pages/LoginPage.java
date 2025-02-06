package com.ui.pages;

import com.utility.BrowserUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class LoginPage extends BrowserUtility {

    private static final By EMAIL_TEXTBOX_LOCATOR = By.id("email");
    private static final By PASSWORD_TEXTBOX_LOCATOR = By.id("passwd");
    private static final By SIGN_IN_BUTTON_LOCATOR = By.id("SubmitLogin");
    private static final Logger LOGGER = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MyAccountPage doLoginWith(String emailAddress, String password) {
        LOGGER.info("Performing login operation with email address {}", emailAddress);
        enterText(EMAIL_TEXTBOX_LOCATOR, emailAddress);
        enterText(PASSWORD_TEXTBOX_LOCATOR, password);
        clickOn(SIGN_IN_BUTTON_LOCATOR);
        return new MyAccountPage(getDriver());
    }


}
