package com.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BrowserUtility {

    private static final Logger LOGGER = LogManager.getLogger(BrowserUtility.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public BrowserUtility(WebDriver driver) {
        BrowserUtility.driver.set(driver);
    }

    public BrowserUtility(Browser browserName) {
        LOGGER.info("Launching browser for {} ", browserName);
        if (browserName == Browser.CHROME) {
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--headless");
            driver.set(new ChromeDriver(co));
        } else if (browserName == Browser.FIREFOX) {
            driver.set(new FirefoxDriver());
        } else if (browserName == Browser.EDGE) {
            driver.set(new EdgeDriver());
        }

        if (driver.get() != null) {
            driver.get().manage().window().maximize();
        } else {
            LOGGER.error("Invalid browser name: {}. Please use chrome or firefox or edge", browserName);
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setDriver(WebDriver driver) {
        BrowserUtility.driver.set(driver);
    }

    public void goToWebsite(String url) {
        LOGGER.info("Visiting this website: {} ", url);
        driver.get().get(url);
    }

    public void clickOn(By locator) {
        LOGGER.info("Finding the element with locator {} ", locator);
        WebElement webElement = driver.get().findElement(locator);
        LOGGER.info("Performing click operation on the locator {} ", locator);
        webElement.click();
    }

    public void enterText(By locator, String textToEnter) {
        LOGGER.info("Finding the element with locator {} " +
                "and enter this text {} at the locator ", locator, textToEnter);
        WebElement webElement = driver.get().findElement(locator);
        webElement.sendKeys(textToEnter);
    }

    public String getVisibleText(By locator) {
        LOGGER.info("Finding the element with locator {} ", locator);
        WebElement webElement = driver.get().findElement(locator);
        LOGGER.info("Return the visible text {} ", webElement.getText());
        return webElement.getText();
    }

    public String takeScreenshotFromBrowser(String screenshotName) {

        File screenshotSource;
        SimpleDateFormat timeStampFormat;
        String timeStamp;
        String screenshotPath = null;
        File screenshotFile;
        LOGGER.info("Trying to capture the screenshot as there is a Test Failure");
        try {
            screenshotSource = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
            timeStampFormat = new SimpleDateFormat("HH-mm-ss");
            timeStamp = timeStampFormat.format(new Date());

            screenshotPath = System.getProperty("user.dir") + "//screenshots//"
                    + screenshotName + "-" + timeStamp + ".png";
            screenshotFile = new File(screenshotPath);

            FileUtils.copyFile(screenshotSource, screenshotFile);
        } catch (IOException e) {
            LOGGER.error("Unable to grab the screenshot");
            e.fillInStackTrace();
        }

        return screenshotPath;
    }

    public void killBrowser() {
        LOGGER.info("Killing the browser...");
        try {
            driver.get().quit();
        } catch (Exception e) {
            LOGGER.error("Unable to kill the browser {}", e.getMessage());
        }
    }
}
