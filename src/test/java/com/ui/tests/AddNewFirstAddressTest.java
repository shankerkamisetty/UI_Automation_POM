package com.ui.tests;

import com.ui.pages.MyAccountPage;
import com.ui.pages.MyAddressesPage;
import com.ui.pojo.Address;
import com.ui.utility.FakeAddressUtility;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewFirstAddressTest extends TestBase {

    private static MyAccountPage myAccountPage;
    private static Address address;

    @BeforeMethod(description = "Valid user logs into the application")
    public void setup() {
        myAccountPage = homePage.goToLoginPage()
                .doLoginWith("lifik11846@numerobo.com", "3vWSpAFUpTgLB@");
        address = FakeAddressUtility.getFakeAddress();
    }

    @Test(
            testName = "Add First New Address",
            description = "Verify the addition of first new address functionality",
            groups = {"sanity", "e2e"},
            retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void addFirstNewAddress() {
        MyAddressesPage myAddressesPage = myAccountPage.goToAddAddressPage()
                .saveAddress(address);
        Assert.assertEquals(
                myAddressesPage.getExistingAddress(),
                address.getAliasAddress().toUpperCase());
    }

    @Test(
            testName = "Delete Existing Address",
            description = "Verify the deletion of an existing address",
            groups = {"sanity", "e2e"},
            retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void deleteAddressTest() {
        myAccountPage.goToAddressesPage()
                .deleteExistingAddress();
    }
}
