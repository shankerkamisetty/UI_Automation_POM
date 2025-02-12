package com.ui.pages;

import com.constants.Browser;
import com.ui.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class BasePage extends BrowserUtility {

    protected static final By SEARCH_TEXTBOX = By.id("search_query_top");
    protected static final By SEARCH_BUTTON = By.xpath("//button[@name='submit_search']");

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public BasePage(Browser browserName, boolean isHeadless) {
        super(browserName, isHeadless);
    }

    public SearchResultPage searchForProduct(String searchText) {
        enterText(SEARCH_TEXTBOX, searchText);
        enterSpecialKey(SEARCH_TEXTBOX, Keys.ENTER);
        return new SearchResultPage(getDriver());
    }


}
