package com.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LambdaTestUtility {
    public static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<DesiredCapabilities> capabilitiesThreadLocal = new ThreadLocal<>();

    public static WebDriver initializeLambdaTestSession(String browserName, String testName) {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", "132");

        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "skautomate7");
        ltOptions.put("accessKey", "ODMN0v7Dgv949DUMpgeeZE2Y9cfFjvh1ND7ggd8SJl54yXdPsg");
        ltOptions.put("build", "UI_Automation_POM");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.23.0");
        capabilities.setCapability("LT:Options", ltOptions);

        capabilitiesThreadLocal.set(capabilities);

        try {
            WebDriver driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesThreadLocal.get());
            webDriverThreadLocal.set(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return webDriverThreadLocal.get();
    }

    public static void quitSession() {
        if (webDriverThreadLocal.get() != null) {
            webDriverThreadLocal.get().quit();
        }
    }

}
