package com.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmAddressPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(ConfirmAddressPage.class);

    private static final By CONFIRM_CHECKOUT_BUTTON_LOCATOR = By.name("processAddress");

    public ConfirmAddressPage(WebDriver driver) {
        super(driver);
    }

    public ShippingDetailsPage checkoutOnConfirmAddressPage() {
        LOGGER.info("Checkout from Product Page");
        clickOn(CONFIRM_CHECKOUT_BUTTON_LOCATOR);
        return new ShippingDetailsPage(getDriver());
    }

}
