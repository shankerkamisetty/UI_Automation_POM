package com.ui.tests;

import com.ui.pages.MyAccountPage;
import com.ui.pages.MyAddressesPage;
import com.ui.pojo.Address;
import com.utility.FakeAddressUtility;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewFirstAddressTest extends TestBase {

    private static MyAccountPage myAccountPage;
    private static MyAddressesPage myAddressesPage;
    private static Address address;

    @BeforeMethod(description = "Valid user logs into the application")
    public void setup() {
        myAccountPage = homePage.goToLoginPage()
                .doLoginWith("lifik11846@numerobo.com", "3vWSpAFUpTgLB@");
        address = FakeAddressUtility.getFakeAddress();
    }

    @Test(description = "Verify the addition of first new address functionality",
            retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void addFirstNewAddress() {
        myAddressesPage = myAccountPage.goToAddAddressPage()
                .saveAddress(address);
        Assert.assertEquals(
                myAddressesPage
                        .getExistingAddress(),
                address.getAliasAddress().toUpperCase());
    }

    @Test(description = "Verify the deletion of an existing address",
            retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void deleteAddressTest() {
        myAccountPage.goToAddressesPage()
                .deleteExistingAddress();
    }
}
