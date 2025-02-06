package com.ui.listeners;

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
    }

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info(result.getMethod().getMethodName());
        LOGGER.info(result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info(result.getMethod().getMethodName() + " " + "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.error(result.getMethod().getMethodName() + " " + "FAILED");
        LOGGER.error(result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
    }


    @Override
    public void onFinish(ITestContext context) {
        LOGGER.info("Test Suite Completed");
    }

}
