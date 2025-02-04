package com.ui.listeners;

import com.utility.PropertiesUtility;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.constants.Env.QA;
import static com.utility.JSONUtility.getEnvFromJsonFile;

public class RetryAnalyzer implements IRetryAnalyzer {


    private static final int MAX_NUMBER_OF_ATTEMPTS = getEnvFromJsonFile(QA).getMaxNumberOfAttempts();
    private static int currentAttempt = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (currentAttempt <= MAX_NUMBER_OF_ATTEMPTS) {
            currentAttempt++;
            return true;
        }
        return false;
    }
}
