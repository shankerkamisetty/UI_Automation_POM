package com.ui.tests;

import com.ui.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubmitOrderTest extends TestBase {

    private static final String SEARCH_TERM = "Printed Summer Dress";
    private static SearchResultPage searchResultPage;

    @BeforeMethod(description = "Verify user logs into the application and searches for a product")
    public void setup() {
        searchResultPage = homePage.goToLoginPage()
                .doLoginWith("lifik11846@numerobo.com", "3vWSpAFUpTgLB@")
                .searchForProduct(SEARCH_TERM);
    }

    @Test(
            testName = "Add Product to Cart and continue to Checkout",
            description = "Verify if logged in user is able to buy a product",
            groups = {"sanity", "e2e"},
            retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void orderConfirmationTest() {
        String orderConfirmationMessage = searchResultPage.clickOnProductAtIndex(0)
                .changeSize("L")
                .clickOnAddToCart()
                .checkoutOnProductPage()
                .checkoutOnCartSummaryPage()
                .checkoutOnConfirmAddressPage()
                .agreeTermsOfService()
                .checkoutOnShippingPage()
                .choosePaymentByBankWire()
                .confirmOrder()
                .getConfirmationMessage();

        Assert.assertEquals(orderConfirmationMessage,
                "Your order on My Shop is complete.");
    }
}
