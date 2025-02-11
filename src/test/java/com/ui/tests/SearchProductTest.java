package com.ui.tests;

import com.ui.pages.MyAccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchProductTest extends TestBase {

    private static final String PRODUCT_SEARCH_TERM = "Printed Summer Dress";
    private MyAccountPage myAccountPage;

    @BeforeMethod(
            description = "Valid user logs into the application"
    )
    public void setup() {
        myAccountPage = homePage.goToLoginPage()
                .doLoginWith("lifik11846@numerobo.com", "3vWSpAFUpTgLB@");
    }

    @Test(
            testName = "Search for Products and analyze results",
            description = "Verify if the logged in user is able to search for a product and correct products are displayed",
            groups = {"sanity", "e2e"},
            retryAnalyzer = com.ui.listeners.RetryAnalyzer.class
    )
    public void verifyProductSearchTest() {
        boolean result = myAccountPage.searchForProduct(PRODUCT_SEARCH_TERM)
                .isSearchTermPresentInProductList(PRODUCT_SEARCH_TERM);

        Assert.assertTrue(result);

    }

}
