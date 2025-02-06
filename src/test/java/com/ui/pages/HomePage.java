package com.ui.pages;

import com.constants.Browser;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static com.constants.Env.QA;
import static com.utility.JSONUtility.getEnvFromJsonFile;

//This class follows the Page Object Design Pattern
public final class HomePage extends BrowserUtility {

    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[@class='login']");
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public HomePage(Browser browserName) {
        super(browserName);
      /*
      To read env property value from properties file,
      use readFromPropertiesFile(Env, String) method
      */
        goToWebsite(getEnvFromJsonFile(QA).getUrl());
    }

    public LoginPage goToLoginPage() {
        logger.info("Trying to perform click to got o sign-in page");
        clickOn(SIGN_IN_LINK_LOCATOR);
        return new LoginPage(getDriver());
    }


}
