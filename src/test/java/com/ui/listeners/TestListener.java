package com.ui.listeners;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ui.tests.TestBase;
import com.utility.ReportingUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        LOGGER.info("Test suite started!!");
        ReportingUtility.setupExtentSparkReporter();
    }

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info(result.getMethod().getMethodName());
        LOGGER.info(result.getMethod().getDescription());
        ReportingUtility.createExtentTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("{} PASSED", result.getMethod().getMethodName());
        ReportingUtility.getLocalExtentTest().log(Status.PASS,
                MarkupHelper.createLabel(result.getMethod().getMethodName()
                        + " PASSED", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = ((TestBase) result.getInstance())
                .getInstance()
                .takeScreenshotFromBrowser(result.getMethod().getMethodName());

        LOGGER.error("Test FAILED - {} \n Exception: {} \n {}",
                result.getMethod().getMethodName(),
                result.getThrowable().getMessage(),
                result.getThrowable());
        ReportingUtility.getLocalExtentTest().log(Status.FAIL,
                MarkupHelper.createLabel(result.getMethod().getMethodName()
                        + " FAILED", ExtentColor.RED));
        ReportingUtility.getLocalExtentTest().log(Status.FAIL,
                "Exception Message: " + result.getThrowable().fillInStackTrace());
        ReportingUtility.getLocalExtentTest().addScreenCaptureFromPath(screenshotPath);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.warn("{} SKIPPED", result.getMethod().getMethodName());
        ReportingUtility.getLocalExtentTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");
    }

    @Override
    public void onFinish(ITestContext context) {
        LOGGER.info("Test Suite Completed");
        ReportingUtility.flushReport();
    }

}
