package com.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class LoginPage extends BasePage {

    private static final By EMAIL_TEXTBOX_LOCATOR = By.id("email");
    private static final By PASSWORD_TEXTBOX_LOCATOR = By.id("passwd");
    private static final By SIGN_IN_BUTTON_LOCATOR = By.id("SubmitLogin");
    private static final By AUTHENTICATION_ERROR_LOCATOR = By.xpath("//div[contains(@class,'alert-danger')]//ol//li");

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

    public LoginPage doLoginWithInvalidData(String emailAddress, String password) {
        enterText(EMAIL_TEXTBOX_LOCATOR, emailAddress);
        enterText(PASSWORD_TEXTBOX_LOCATOR, password);
        clickOn(SIGN_IN_BUTTON_LOCATOR);
        return new LoginPage(getDriver());
    }

    public String getErrorMessage() {
        return getVisibleText(AUTHENTICATION_ERROR_LOCATOR);
    }


}
