package com.ui.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGridUtility {

    public static final String HUB_URL = "http://selenium-hub:4444/wd/hub";
    private static final Logger LOGGER = LogManager.getLogger(SeleniumGridUtility.class);

    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();


    public static WebDriver initializeSeleniumGridTestSession(String browserName, boolean isHeadless) {

        WebDriver driver;
        LOGGER.info("Launching browser for \"{}\" with headless mode set to \"{}\"", browserName, isHeadless);
        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless");
                }
                driver = new RemoteWebDriver(new URL(HUB_URL), chromeOptions);
                LOGGER.info("{} browser launched successfully!", browserName);
                webDriverThreadLocal.set(driver);
            } else if (browserName.equalsIgnoreCase("firefox")) {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new RemoteWebDriver(new URL(HUB_URL), firefoxOptions);
                LOGGER.info("{} browser launched successfully!", browserName);
                webDriverThreadLocal.set(driver);
            } else if (browserName.equalsIgnoreCase("edge")) {
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) {
                    edgeOptions.addArguments("--headless");
                }
                driver = new RemoteWebDriver(new URL(HUB_URL), edgeOptions);
                LOGGER.info("{} browser launched successfully!", browserName);
                webDriverThreadLocal.set(driver);

            }
        } catch (MalformedURLException e) {
            LOGGER.error("Unable to initialize Selenium Grid session");
            LOGGER.error("Exception Message: {}", e.getMessage());
        }
        return webDriverThreadLocal.get();
    }

    public static void quitSeleniumGridSession() {
        LOGGER.info("Killing SeleniumGrid Session...");
        if (webDriverThreadLocal.get() != null) {
            webDriverThreadLocal.get().quit();
        }
    }

}
