package com.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentMethodPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(PaymentMethodPage.class);

    private static final By PAY_BY_BANK_WIRE_LINK_LOCATOR = By.xpath("//a[@class='bankwire']");

    public PaymentMethodPage(WebDriver driver) {
        super(driver);
    }

    public OrderSummaryPage choosePaymentByBankWire() {
        LOGGER.info("Selecting pay by Bank wire as the payment option");
        clickOn(PAY_BY_BANK_WIRE_LINK_LOCATOR);
        return new OrderSummaryPage(getDriver());
    }


}
