package com.ui.tests;

import com.ui.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.constants.Browser.CHROME;
import static org.testng.Assert.assertEquals;

public class LoginTest {
    HomePage homePage;

    @BeforeMethod(description = "Load the Home Page of the website")
    public void setup() {
        homePage = new HomePage(CHROME);
    }

    @Test(description = "Verify if a valid user is able to login",
            groups = {"sanity", "e2e"})
    public void loginTest() {

        assertEquals(homePage.goToLoginPage()
                        .doLoginWith("lifik11846@numerobo.com", "3vWSpAFUpTgLB@")
                        .getUserName(),
                "Shanker Kamisetty");

    }


}
