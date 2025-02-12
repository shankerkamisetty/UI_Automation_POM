package com.ui.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BrowserUtility {

    private static final Logger LOGGER = LogManager.getLogger(BrowserUtility.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private WebDriverWait wait;

    public BrowserUtility(WebDriver driver) {
        BrowserUtility.driver.set(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
    }

    public BrowserUtility(Browser browserName) {
        LOGGER.info("Launching browser for {} ", browserName);
        if (browserName == Browser.CHROME) {
            driver.set(new ChromeDriver());
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
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
    }

    public BrowserUtility(Browser browserName, boolean isHeadless) {
        LOGGER.info("Launching browser for \"{}\" with headless mode set to \"{}\"", browserName, isHeadless);
        try {
            if (browserName == Browser.CHROME) {
                if (isHeadless) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");

                    LOGGER.info("Setting the Thread local to ChromeDriver");
                    driver.set(new ChromeDriver(chromeOptions));
                    LOGGER.info("Get the value of drive thread local: {}",driver.get());
                    LOGGER.info("{} browser launched successfully! without HEADLESS Mode", browserName);
                } else {
                    LOGGER.info("Setting the Thread local to ChromeDriver");
                    driver.set(new ChromeDriver());
                    LOGGER.info("Get the value of drive thread local: {}",driver.get());
                    LOGGER.info("{} Browser launched successfully! with HEADLESS mode", browserName);
                }
            } else if (browserName == Browser.FIREFOX) {
                if (isHeadless) {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    driver.set(new FirefoxDriver(firefoxOptions));
                    LOGGER.info("{} Browser launched successfully! without HEADLESS Mode", browserName);
                } else {
                    driver.set(new FirefoxDriver());
                    LOGGER.info("{} Browser launched successfully! with HEADLESS mode", browserName);
                }
            } else if (browserName == Browser.EDGE) {
                if (isHeadless) {
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    driver.set(new EdgeDriver(edgeOptions));
                    LOGGER.info("{} Browser launched successfully! without HEADLESS Mode", browserName);
                } else {
                    driver.set(new EdgeDriver());
                    LOGGER.info("{} Browser launched successfully! with HEADLESS mode", browserName);
                }
            }

            if (driver.get() != null) {
                driver.get().manage().window().maximize();
            } else {
                LOGGER.error("WebDriver is null. Unable to launch browser {}. Please use chrome or firefox or edge", browserName);
            }
        } catch (Exception e) {
            LOGGER.error("Unable to launch browser {}", browserName);
            LOGGER.error(e.getMessage());
        }

        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
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
        LOGGER.info("Finding the element with locator \"{}\" to click", locator);
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
        LOGGER.info("Performing click operation on the web element \"{}\" ", webElement);
        webElement.click();
    }

    public void clickOn(WebElement webElement) {
        LOGGER.info("Performing click operation on the web element \"{}\" ", webElement);
        webElement.click();
    }

    public void enterText(By locator, String textToEnter) {
        LOGGER.info("Finding the element with locator \"{}\" " +
                "and enter this text \"{}\" at the locator ", locator, textToEnter);
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        webElement.clear();
        webElement.sendKeys(textToEnter);
    }

    public void clearText(By locator) {
        LOGGER.info("Finding the element with locator \"{}\" " +
                "and clear text at the locator ", locator);
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        webElement.clear();
    }

    public void selectFromDropDown(By dropDownLocator, String optionToSelect) {
        LOGGER.info("Finding the dropdown element with locator \"{}\" " +
                "and select this option \"{}\" at the locator ", dropDownLocator, optionToSelect);
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(dropDownLocator));
        Select select = new Select(webElement);
        select.selectByVisibleText(optionToSelect);
    }

    public void enterSpecialKey(By locator, Keys keyToEnter) {
        LOGGER.info("Finding the element with locator \"{}\" " +
                "and enter this key \"{}\" at the locator ", locator, keyToEnter);
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        webElement.sendKeys(keyToEnter);
    }

    public String getVisibleText(By locator) {
        LOGGER.info("Finding the element with locator \"{}\" ", locator);
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        LOGGER.info("Returning the visible text \"{}\" ", webElement.getText());
        return webElement.getText();
    }

    public String getVisibleText(WebElement element) {
        LOGGER.info("Returning the visible text \"{}\" ", element.getText());
        return element.getText();
    }

    public List<String> getAllVisibleText(By locator) {
        LOGGER.info("Finding the elements with locator \"{}\" ", locator);
        List<WebElement> elementList = driver.get().findElements(locator);
        List<String> visibleTextList = new ArrayList<>();

        for (WebElement element : elementList) {
            visibleTextList.add(getVisibleText(element));
        }
        return visibleTextList;
    }

    public List<WebElement> getAllVisibleElements(By locator) {
        LOGGER.info("Find the elements with locator \"{}\" and return the list of web elements", locator);
        return driver.get().findElements(locator);
    }

    public void acceptBrowserAlert() {
        Alert alert = getDriver().switchTo().alert();
        LOGGER.info("Handling browser alert \"{}\"", alert.getText());
        LOGGER.info("Clicking OK to confirm the deletion");
        alert.accept();
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

            screenshotPath = String.valueOf(Paths.get("./screenshots/",
                    screenshotName + "-" + timeStamp + ".png"));
            screenshotFile = new File(screenshotPath);

            FileUtils.copyFile(screenshotSource, screenshotFile);
        } catch (IOException e) {
            LOGGER.error("Unable to capture the screenshot");
            e.fillInStackTrace();
        }

        return screenshotPath;
    }

    public void killBrowser() {
        LOGGER.info("Killing the browser...");
        try {
            driver.get().quit();
        } catch (Exception e) {
            LOGGER.error("Unable to kill the browser. Reason: {}", e.getMessage());
        }
    }
}
