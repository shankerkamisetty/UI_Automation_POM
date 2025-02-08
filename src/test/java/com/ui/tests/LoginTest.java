package com.ui.tests;

import com.ui.pojo.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends TestBase {

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

    @Test(description = "Verify if a valid user is able to login using CSV file",
            groups = {"sanity", "e2e"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginDataFromCSVFile",
            retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void loginTestUsingCSVFile(User user) {
        assertEquals(homePage.goToLoginPage()
                        .doLoginWith(user.getEmailAddress(), user.getPassword())
                        .getUserName(),
                "Random Username");
    }

    /*@Test(description = "Verify if a valid user is able to login using Excel file",
            groups = {"sanity", "e2e"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginDataFromExcelFile",
            retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)*/
    public void loginTestUsingExcelFIle(User user) {
        assertEquals(homePage.goToLoginPage()
                        .doLoginWith(user.getEmailAddress(), user.getPassword())
                        .getUserName(),
                "Shanker Kamisetty");
    }


}
