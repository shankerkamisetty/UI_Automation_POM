package com.ui.tests;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.constants.Browser.CHROME;
import static org.testng.Assert.assertEquals;

public class LoginTest {

    private static final Logger LOGGER = LogManager.getLogger(LoginTest.class);
    HomePage homePage;

    @BeforeMethod(description = "Load the Home Page of the website")
    public void setup() {
        LOGGER.info("Setting up Home Page...");
        homePage = new HomePage(CHROME);
    }

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
                "Shanker Kamisetty");
    }

    @Test(description = "Verify if a valid user is able to login using Excel file",
            groups = {"sanity", "e2e"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginDataFromExcelFile",
            retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void loginTestUsingExcelFIle(User user) {
        assertEquals(homePage.goToLoginPage()
                        .doLoginWith(user.getEmailAddress(), user.getPassword())
                        .getUserName(),
                "Shanker Kamisetty");
    }

    @AfterMethod(description = "Tear down the browser")
    public void tearDown() {
        homePage.killBrowser();
    }


}
