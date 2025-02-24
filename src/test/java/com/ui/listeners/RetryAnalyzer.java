package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.constants.Env.QA;
import static com.ui.utility.JSONUtility.getEnvFromJsonFile;

public class RetryAnalyzer implements IRetryAnalyzer {

    private final int MAX_NUMBER_OF_ATTEMPTS = getEnvFromJsonFile(QA).getMaxRetryAttempts();
    private int currentAttempt = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (currentAttempt <= MAX_NUMBER_OF_ATTEMPTS) {
            currentAttempt++;
            return true;
        }
        return false;
    }
}
