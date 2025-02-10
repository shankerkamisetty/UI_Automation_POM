package com.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchResultPage extends BasePage {

    private static final By PRODUCT_LISTING_TITLE_LOCATOR = By.xpath("//span[@class='lighter']");
    private static final By PRODUCT_LIST_LOCATOR = By.xpath("//h5[@itemprop='name']/a");

    private static final Logger LOGGER = LogManager.getLogger(SearchResultPage.class);

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getSearchResultTitle() {
        return getVisibleText(PRODUCT_LISTING_TITLE_LOCATOR);
    }

    public boolean isSearchTermPresentInProductList(String searchTerm) {
        List<String> productNameList = getAllVisibleText(PRODUCT_LIST_LOCATOR);
        List<String> splitKeywordsList = Arrays.asList(searchTerm.toLowerCase().split(" "));
        LOGGER.info("validating if the product list {} contains any value of the search term '{}'", productNameList, searchTerm);
        return productNameList.stream()
                .anyMatch(
                        name -> (splitKeywordsList.stream()
                                .anyMatch(name.toLowerCase()::contains)));

    }

}
