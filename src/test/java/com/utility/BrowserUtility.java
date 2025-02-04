package com.utility;

import com.constants.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BrowserUtility {

    private WebDriver driver;

    public BrowserUtility(WebDriver driver) {
        this.driver = driver;
    }

    public BrowserUtility(Browser browserName) {

        if (browserName == Browser.CHROME) {
            driver = new ChromeDriver();
        } else if (browserName == Browser.FIREFOX) {
            driver = new FirefoxDriver();
        } else if (browserName == Browser.EDGE) {
            driver = new EdgeDriver();
        }

        if (driver != null) {
            driver.manage().window().maximize();
        }

    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void goToWebsite(String url) {
        driver.get(url);
    }

    public void clickOn(By locator) {
        WebElement webElement = driver.findElement(locator);
        webElement.click();
    }

    public void enterText(By locator, String textToEnter) {
        WebElement webElement = driver.findElement(locator);
        webElement.sendKeys(textToEnter);
    }

    public String getVisibleText(By locator) {
        WebElement webElement = driver.findElement(locator);
        return webElement.getText();
    }

    public void killBrowser() {
        driver.quit();
    }
}
