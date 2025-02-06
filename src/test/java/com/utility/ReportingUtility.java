package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReportingUtility {

    private static final Logger LOGGER = LogManager.getLogger(ReportingUtility.class);
    private static ExtentReports extentReports;
    // make the ExtentTest as thread safe using ThreadLocal for parallel execution
    private static ThreadLocal<ExtentTest> localExtentTest = new ThreadLocal<>();

    public static void setupExtentSparkReporter() {
        LOGGER.info("Setting up ExtentSparkReport...");
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//test-results-report.html");
        extentReports = new ExtentReports();
        LOGGER.info("Attaching report to extent spark reporter");
        extentReports.attachReporter(extentSparkReporter);
    }

    public static void createExtentTest(String testName) {
        LOGGER.info("Creating Extent Test for test: {}", testName);
        ExtentTest extentTest = extentReports.createTest(testName);
        LOGGER.info("Setting up extent test as thread Safe...");
        localExtentTest.set(extentTest);
    }

    public static ExtentTest getLocalExtentTest() {
        return localExtentTest.get();
    }

    public static void flushReport() {
        LOGGER.info("Flushing the extent report...");
        extentReports.flush();
    }

}
