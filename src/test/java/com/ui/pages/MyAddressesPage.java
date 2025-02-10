package com.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAddressesPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(MyAddressesPage.class);

    private static final By ADDRESS_HEADING_LOCATOR = By.tagName("h3");
    private static final By DELETE_ADDRESS_LOCATOR = By.xpath("//a[@title='Delete']");

    public MyAddressesPage(WebDriver driver) {
        super(driver);
    }

    public String getExistingAddress() {
        return getVisibleText(ADDRESS_HEADING_LOCATOR);
    }

    public void deleteExistingAddress() {
        LOGGER.info("Deleting the existing address...");
        clickOn(DELETE_ADDRESS_LOCATOR);
        acceptBrowserAlert();
    }

}
