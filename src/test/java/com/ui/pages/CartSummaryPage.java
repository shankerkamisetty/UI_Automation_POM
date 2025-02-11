package com.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartSummaryPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(CartSummaryPage.class);

    private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.
            xpath("//p[contains(@class,'cart_navigation')]//a[contains(@title,'checkout')]//span");

    public CartSummaryPage(WebDriver driver) {
        super(driver);
    }

    public ConfirmAddressPage checkoutOnCartSummaryPage() {
        LOGGER.info("Checkout from Cart Summary Page");
        clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
        return new ConfirmAddressPage(getDriver());
    }

}
