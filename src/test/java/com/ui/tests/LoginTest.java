package com.ui.tests;

import com.ui.pojo.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends TestBase {

    private static final String INVALID_EMAIL_ADDRESS = "test@test.com";
    private static final String INVALID_PASSWORD = "test123";

    @Test(description = "Verify if a valid user is able to login using JSON file",
            groups = {"sanity", "e2e"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginTestFromJsonFile",
            retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void loginTestUsingJsonFile(User user) {
        assertEquals(homePage.goToLoginPage()
                        .doLoginWith(user.getEmailAddress(), user.getPassword())
                        .getUserName(),
                "Shanker Kamisetty");
    }

    @Test(description = "Verify proper error message is shown for user when user enters invalid credentials",
            groups = {"sanity", "e2e", "smoke"})
    public void loginTestWithInvalidUser() {
        assertEquals(homePage.goToLoginPage()
                        .doLoginWithInvalidData(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD)
                        .getErrorMessage(),
                "Authentication failed.");
    }


}
