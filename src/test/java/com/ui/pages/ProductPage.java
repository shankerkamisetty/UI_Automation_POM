package com.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(ProductPage.class);

    private static final By SIZE_DROPDOWN_LOCATOR = By.id("group_1");
    private static final By ADD_TO_CART_BUTTON_LOCATOR = By.xpath("//button[@name ='Submit']");
    private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.xpath("//a[contains(@title,'checkout')]//span");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage changeSize(String size) {
        LOGGER.info("Changing the size of the product to {}", size);
        selectFromDropDown(SIZE_DROPDOWN_LOCATOR, size.toUpperCase());
        return new ProductPage(getDriver());
    }

    public ProductPage clickOnAddToCart() {
        LOGGER.info("Adding the product to cart...");
        clickOn(ADD_TO_CART_BUTTON_LOCATOR);
        return new ProductPage(getDriver());
    }

    public CartSummaryPage checkoutOnProductPage() {
        LOGGER.info("Checkout from Product Page");
        clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
        return new CartSummaryPage(getDriver());
    }
}
