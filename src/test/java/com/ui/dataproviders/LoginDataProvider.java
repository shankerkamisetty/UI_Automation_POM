package com.ui.dataproviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class LoginDataProvider {

    private static final Logger LOGGER = LogManager.getLogger(LoginDataProvider.class);

    @DataProvider(name = "LoginTestFromJsonFile")
    public static Iterator<User> getUserDataFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(String.valueOf(Paths.get("./test-data", "loginData.json")
                .toAbsolutePath()
                .normalize()));
        TestData testData;
        LOGGER.info("Reading the JSON file {} ", jsonFile);
        try {
            testData = objectMapper.readValue(jsonFile, TestData.class);
        } catch (IOException e) {
            LOGGER.error("Unable to read/map the JSON file {} with {} pojo class", jsonFile, TestData.class);
            throw new RuntimeException(e);
        }

        List<User> userLoginData = new ArrayList<>();
        for (User user : testData.getUserLoginData()) {
            userLoginData.add(user);
        }

        return userLoginData.iterator();
    }

    @DataProvider(name = "LoginDataFromPropFile")
    public static Iterator<User> readUserDataFromPropertiesFile() {
        File propFile = new File(String.valueOf(Paths.get("./config", "QA.properties")
                .toAbsolutePath()
                .normalize()));

        FileReader fileReader;
        Properties properties = new Properties();
        LOGGER.info("Reading the properties from {}", propFile);
        try {
            fileReader = new FileReader(propFile);
            properties.load(fileReader);
        } catch (IOException e) {
            LOGGER.error("Unable to read data from properties file {}", propFile);
            throw new RuntimeException(e);
        }

        User user = new User(properties.getProperty("ValidUser.email"),
                properties.getProperty("ValidUser.password"));

        List<User> userLoginData = new ArrayList<>();
        userLoginData.add(user);

        return userLoginData.iterator();
    }

    @DataProvider(name = "LoginDataFromCSVFile")
    public Iterator<User> readLoginDataFromCSVFile() {
        return CSVReaderUtility.getUserFromCSVFile("LoginData.csv");
    }

    @DataProvider(name = "LoginDataFromExcelFile")
    public Iterator<User> readLoginDataFromExcelFile() {
        return ExcelReaderUtility.getUserFromExcelFile("LoginData.xlsx", "LoginTestData");
    }

}
