package com.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShippingDetailsPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(ShippingDetailsPage.class);

    private static final By TERMS_OF_SERVICE_CHECK_BOX_LOCATOR = By.id("uniform-cgv");
    private static final By CONFIRM_CHECKOUT_BUTTON_LOCATOR = By.name("processCarrier");

    public ShippingDetailsPage(WebDriver driver) {
        super(driver);
    }

    public ShippingDetailsPage agreeTermsOfService() {
        LOGGER.info("Check the \"Agree to the shipping carrier terms of service\" checkbox");
        clickOn(TERMS_OF_SERVICE_CHECK_BOX_LOCATOR);
        return new ShippingDetailsPage(getDriver());
    }

    public PaymentMethodPage checkoutOnShippingPage() {
        LOGGER.info("Checkout from Shipping Details Page");
        clickOn(CONFIRM_CHECKOUT_BUTTON_LOCATOR);
        return new PaymentMethodPage(getDriver());
    }
}
