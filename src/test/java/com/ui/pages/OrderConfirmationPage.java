package com.ui.pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(OrderSummaryPage.class);

    private static final By ORDER_CONFIRMATION_MESSAGE_LOCATOR = By.xpath("//p[contains(@class,'alert-success')]");

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationMessage() {
        LOGGER.info("Reading the confirmation message from order confirmation page");
        return getVisibleText(ORDER_CONFIRMATION_MESSAGE_LOCATOR);
    }


}
