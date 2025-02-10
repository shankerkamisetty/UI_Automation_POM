package com.ui.pages;

import com.utility.BrowserUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class MyAccountPage extends BasePage {

    private static final By USER_NAME_LOCATOR = By.xpath("//a[@class='account']/span");

    private static final Logger LOGGER = LogManager.getLogger(MyAccountPage.class);

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {
        LOGGER.info("Getting the username...");
        return getVisibleText(USER_NAME_LOCATOR);
    }


}
