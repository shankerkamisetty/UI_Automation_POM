package com.ui.dataproviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class LoginDataProvider {

    @DataProvider(name = "LoginTestFromJsonFile")
    public static Iterator<User> getUserDataFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(System.getProperty("user.dir") + "//test-data//loginData.json");
        TestData testData;
        try {
            testData = objectMapper.readValue(file, TestData.class);
        } catch (IOException e) {
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
        File propFile = new File(System.getProperty("user.dir") + "//config//QA.properties");

        FileReader fileReader;
        Properties properties = new Properties();
        try {
            fileReader = new FileReader(propFile);
            properties.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String email = properties.getProperty("ValidUser.email");
        String password = properties.getProperty("ValidUser.password");

        User user = new User(email, password);
        List<User> userLoginData = new ArrayList<>();
        userLoginData.add(user);

        return userLoginData.iterator();
    }

    @DataProvider(name = "LoginDataFromCSVFile")
    public Iterator<User> readLoginDataFromCSVFile() {
        return CSVReaderUtility.readCSVFile("LoginData.csv");
    }

}
