package com.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderSummaryPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(OrderSummaryPage.class);

    private static final By CONFIRM_ORDER_BUTTON = By.xpath("//p[@id='cart_navigation']//button[@type='submit']");

    public OrderSummaryPage(WebDriver driver) {
        super(driver);
    }

    public OrderConfirmationPage confirmOrder() {
        LOGGER.info("Confirming the order...");
        clickOn(CONFIRM_ORDER_BUTTON);
        return new OrderConfirmationPage(getDriver());
    }
}
