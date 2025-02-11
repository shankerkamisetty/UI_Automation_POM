package com.ui.tests;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.ui.utility.BrowserUtility;
import com.ui.utility.LambdaTestUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {

    private static final Logger LOGGER = LogManager.getLogger(TestBase.class);
    protected HomePage homePage;
    private boolean isLambdaTest;

    @Parameters({"environment", "browser", "isLambdaTest", "isHeadless"})
    @BeforeMethod(description = "Load the Home Page of the website")
    public void setup(
            @Optional("qa") String environment,
            @Optional("chrome") String browserName,
            @Optional("false") boolean isLambdaTest,
            @Optional("true") boolean isHeadless,
            ITestResult result) {

        this.isLambdaTest = isLambdaTest;
        WebDriver lambdaDriver;

        LOGGER.info("Tests are run in {} browser in {} environment with" +
                        " isLambdaTest = {} and isHeadless mode = {}",
                browserName,
                environment,
                isLambdaTest,
                isHeadless);

        if (isLambdaTest) {
            lambdaDriver = LambdaTestUtility.
                    initializeLambdaTestSession(browserName, result.getMethod().getMethodName());
            homePage = new HomePage(environment, lambdaDriver);
        } else {
            LOGGER.info("Setting up Home Page...");
            homePage = new HomePage(environment, Browser.valueOf(browserName.toUpperCase()), isHeadless);
        }
    }

    // this method will help in getting the instance of BrowserUtility
    // and will help in accessing takeScreenshotFromBrowser method from TestListener class
    public BrowserUtility getInstance() {
        return homePage;
    }

    @AfterMethod(description = "Tear down the browser")
    public void tearDown() {
        if (isLambdaTest) {
            LambdaTestUtility.quitSession();
        } else {
            homePage.killBrowser();
        }
    }
}
