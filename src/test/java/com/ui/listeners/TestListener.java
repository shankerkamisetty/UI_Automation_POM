package com.ui.listeners;

import com.aventstack.extentreports.Status;
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
        ReportingUtility.createExtentTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("{} PASSED", result.getMethod().getMethodName());
        ReportingUtility.getLocalExtentTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.error("Test FAILED - {} \n Exception: {} \n {}",
                result.getName(),
                result.getThrowable().getMessage(),
                result.getThrowable());
        ReportingUtility.getLocalExtentTest().log(Status.FAIL, result.getName() + " " + "FAILED");
        ReportingUtility.getLocalExtentTest().log(Status.FAIL, "Exception Message: " + result.getThrowable().fillInStackTrace());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.warn("{} SKIPPED", result.getMethod().getMethodName());
        ReportingUtility.getLocalExtentTest().log(Status.SKIP, result.getName() + " " + "SKIPPED");
    }

    @Override
    public void onFinish(ITestContext context) {
        LOGGER.info("Test Suite Completed");
        ReportingUtility.flushReport();
    }

}
