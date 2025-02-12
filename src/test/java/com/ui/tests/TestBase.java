package com.ui.tests;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.ui.utility.BrowserUtility;
import com.ui.utility.LambdaTestUtility;
import com.ui.utility.SeleniumGridUtility;
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
    private boolean runInDockerContainer;

    @Parameters({"environment", "browser", "isLambdaTest", "runInDockerContainer", "isHeadless"})
    @BeforeMethod(description = "Load the Home Page of the website")
    public void setup(
            @Optional("qa") String environment,
            @Optional("chrome") String browserName,
            @Optional("false") boolean isLambdaTest,
            @Optional("true") boolean runInDockerContainer,
            @Optional("true") boolean isHeadless,
            ITestResult result) {

        this.isLambdaTest = isLambdaTest;
        this.runInDockerContainer = runInDockerContainer;
        WebDriver lambdaDriver;
        WebDriver seleniumGridDriver;

        LOGGER.info("Tests are run in {} browser in {} environment with" +
                        " isLambdaTest = {}, runInDockerContainer = {} and isHeadless mode = {}",
                browserName,
                environment,
                isLambdaTest,
                runInDockerContainer,
                isHeadless);

        if (isLambdaTest) {
            lambdaDriver = LambdaTestUtility.
                    initializeLambdaTestSession(browserName, result.getMethod().getMethodName());
            homePage = new HomePage(environment, lambdaDriver);
        } else if (runInDockerContainer) {
            seleniumGridDriver = SeleniumGridUtility
                    .initializeSeleniumGridTestSession(browserName, isHeadless);
            homePage = new HomePage(environment, seleniumGridDriver);
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
        } else if (runInDockerContainer) {
            SeleniumGridUtility.quitSeleniumGridSession();
        } else {
            homePage.killBrowser();
        }
    }
}
