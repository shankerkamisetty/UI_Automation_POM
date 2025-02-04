package com.ui.dataproviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
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
    public static Iterator<Object[]> getUserDataFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(System.getProperty("user.dir") + "//test-data//loginData.json");
        TestData testData;
        try {
            testData = objectMapper.readValue(file, TestData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Object[]> userLoginData = new ArrayList<>();
        for (User user : testData.getUserLoginData()) {
            userLoginData.add(new Object[]{user});
        }

        return userLoginData.iterator();
    }

    @DataProvider(name = "LoginDataFromPropFile")
    public static Iterator<Object[]> readUserDataFromPropertiesFile() {
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
        List<Object[]> dataToReturn = new ArrayList<>();
        dataToReturn.add(new Object[]{user});

        return dataToReturn.iterator();
    }

}
