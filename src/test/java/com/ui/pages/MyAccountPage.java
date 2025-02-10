package com.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class MyAccountPage extends BasePage {

    private static final By USER_NAME_LOCATOR = By.xpath("//a[@class='account']/span");
    private static final By ADD_FIRST_ADDRESS_LINK_LOCATOR = By.xpath("//ul[@class='myaccount-link-list']//a[@title='Add my first address']");
    private static final By MY_ADDRESSES_LINK_LOCATOR = By.xpath("//a[@title='Addresses']");

    private static final Logger LOGGER = LogManager.getLogger(MyAccountPage.class);

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {
        LOGGER.info("Getting the username...");
        return getVisibleText(USER_NAME_LOCATOR);
    }

    public AddressPage goToAddAddressPage(){
        LOGGER.info("Click on Add Address link...");
        clickOn(ADD_FIRST_ADDRESS_LINK_LOCATOR);
        return new AddressPage(getDriver());
    }

    public MyAddressesPage goToAddressesPage(){
        LOGGER.info("Click on My Address link...");
        clickOn(MY_ADDRESSES_LINK_LOCATOR);
        return new MyAddressesPage(getDriver());


    }

}
