package com.ui.tests;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import org.testng.annotations.AfterMethod;
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
            groups = {"sanity", "e2e"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginTestFromJsonFile")
    public void loginTest(User user) {

        assertEquals(homePage.goToLoginPage()
                        .doLoginWith(user.getEmailAddress(), user.getPassword())
                        .getUserName(),
                "Shanker Kamisetty");

    }

    @AfterMethod(description = "Tear down the browser")
    public void tearDown() {
        homePage.killBrowser();
    }


}
