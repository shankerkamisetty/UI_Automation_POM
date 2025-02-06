package com.ui.tests;

import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.constants.Browser.CHROME;

public class TestBase {

    private static final Logger LOGGER = LogManager.getLogger(TestBase.class);
    protected HomePage homePage;

    @BeforeMethod(description = "Load the Home Page of the website")
    public void setup() {
        LOGGER.info("Setting up Home Page...");
        homePage = new HomePage(CHROME);
    }

    public BrowserUtility getInstance() {
        return homePage;
    }

    @AfterMethod(description = "Tear down the browser")
    public void tearDown() {
        homePage.killBrowser();
    }
}
