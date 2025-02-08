package com.ui.pages;

import com.constants.Browser;
import com.constants.Env;
import com.utility.BrowserUtility;
import com.utility.PropertiesUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.utility.JSONUtility.getEnvFromJsonFile;

//This class follows the Page Object Design Pattern
public final class HomePage extends BrowserUtility {

    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[@class='login']");
    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);

    public HomePage(String environment, Browser browserName, boolean isHeadless) {
        super(browserName, isHeadless);
        goToWebsite(PropertiesUtility.readFromPropertiesFile(Env.valueOf(environment.toUpperCase()), "URL"));
        goToWebsite(getEnvFromJsonFile(Env.valueOf(environment.toUpperCase())).getUrl());
    }

    public HomePage(String environment, WebDriver driver) {
        super(driver);
        goToWebsite(getEnvFromJsonFile(Env.valueOf(environment.toUpperCase())).getUrl());
    }

    public LoginPage goToLoginPage() {
        LOGGER.info("Trying to perform click to go to sign-in page");
        clickOn(SIGN_IN_LINK_LOCATOR);
        return new LoginPage(getDriver());
    }


}
