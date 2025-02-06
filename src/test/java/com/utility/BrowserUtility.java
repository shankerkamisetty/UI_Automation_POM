package com.utility;

import com.constants.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BrowserUtility {

    private static final Logger LOGGER = LogManager.getLogger(BrowserUtility.class);
    private WebDriver driver;

    public BrowserUtility(WebDriver driver) {
        this.driver = driver;
    }

    public BrowserUtility(Browser browserName) {

        LOGGER.info("Launching browser for {} ", browserName);
        if (browserName == Browser.CHROME) {
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--headless");
            driver = new ChromeDriver(co);
        } else if (browserName == Browser.FIREFOX) {
            driver = new FirefoxDriver();
        } else if (browserName == Browser.EDGE) {
            driver = new EdgeDriver();
        }

        if (driver != null) {
            driver.manage().window().maximize();
        } else {
            LOGGER.error("Invalid browser name: {}. Please use chrome or firefox or edge", browserName);
        }

    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void goToWebsite(String url) {
        LOGGER.info("Visiting this website: {} ", url);
        driver.get(url);
    }

    public void clickOn(By locator) {
        LOGGER.info("Finding the element with locator {} ", locator);
        WebElement webElement = driver.findElement(locator);
        LOGGER.info("Performing click operation on the locator {} ", locator);
        webElement.click();
    }

    public void enterText(By locator, String textToEnter) {
        LOGGER.info("Finding the element with locator {} " +
                "and enter this text {} at the locator ", locator, textToEnter);
        WebElement webElement = driver.findElement(locator);
        webElement.sendKeys(textToEnter);
    }

    public String getVisibleText(By locator) {
        LOGGER.info("Finding the element with locator {} ", locator);
        WebElement webElement = driver.findElement(locator);
        LOGGER.info("Return the visible text {} ", webElement.getText());
        return webElement.getText();
    }

    public void killBrowser() {
        LOGGER.info("Killing the browser...");
        try {
            driver.quit();
        } catch (Exception e) {
            LOGGER.error("Unable to kill the browser {}", e.getMessage());

        }
    }
}
