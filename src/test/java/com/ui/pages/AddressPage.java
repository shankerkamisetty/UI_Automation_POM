package com.ui.pages;

import com.ui.pojo.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddressPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(AddressPage.class);

    private static final By FIRST_NAME_TEXTBOX_LOCATOR = By.id("firstname");
    private static final By LAST_NAME_TEXTBOX_LOCATOR = By.id("lastname");
    private static final By COMPANY_TEXTBOX_LOCATOR = By.id("company");
    private static final By ADDRESS_LINE_1_TEXTBOX_LOCATOR = By.id("address1");
    private static final By ADDRESS_LINE_2_TEXTBOX_LOCATOR = By.id("address2");
    private static final By CITY_TEXTBOX_LOCATOR = By.id("city");
    private static final By STATE_DROPDOWN_LOCATOR = By.id("id_state");
    private static final By ZIPCODE_TEXTBOX_LOCATOR = By.id("postcode");
    private static final By HOME_PHONE_TEXTBOX_LOCATOR = By.id("phone");
    private static final By MOBILE_NUMBER_TEXTBOX_LOCATOR = By.id("phone_mobile");
    private static final By ADDITIONAL_INFORMATION_TEXTAREA_LOCATOR = By.id("other");
    private static final By ADDRESS_ALIAS_TEXTBOX_LOCATOR = By.id("alias");
    private static final By SAVE_BUTTON_LOCATOR = By.id("submitAddress");



    public AddressPage(WebDriver driver) {
        super(driver);
    }


    public MyAddressesPage saveAddress(Address address) {
        enterText(COMPANY_TEXTBOX_LOCATOR, address.getCompany());
        enterText(ADDRESS_LINE_1_TEXTBOX_LOCATOR, address.getAddressLine1());
        enterText(ADDRESS_LINE_2_TEXTBOX_LOCATOR, address.getAddressLine2());
        enterText(CITY_TEXTBOX_LOCATOR, address.getCity());
        selectFromDropDown(STATE_DROPDOWN_LOCATOR, address.getState());
        enterText(ZIPCODE_TEXTBOX_LOCATOR, address.getZipcode());
        enterText(HOME_PHONE_TEXTBOX_LOCATOR, address.getHomePhoneNumber());
        enterText(MOBILE_NUMBER_TEXTBOX_LOCATOR, address.getMobileNumber());
        enterText(ADDITIONAL_INFORMATION_TEXTAREA_LOCATOR, address.getAdditionalInformation());
        enterText(ADDRESS_ALIAS_TEXTBOX_LOCATOR, address.getAliasAddress());
        clickOn(SAVE_BUTTON_LOCATOR);


        LOGGER.info("A first new address is created...");
        return new MyAddressesPage(getDriver());


    }

}
