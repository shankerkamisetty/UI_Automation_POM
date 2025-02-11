package com.ui.utility;

import com.constants.Env;
import com.ui.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

//Read the properties file
public class PropertiesUtility {

    private static final Logger LOGGER = LogManager.getLogger(PropertiesUtility.class);

    public static String readFromPropertiesFile(Env env, String property) {
        File propFile = new File(String.valueOf(Paths.get("./config", env + ".properties")
                .toAbsolutePath()
                .normalize()));
        FileReader fileReader;
        Properties properties = new Properties();
        LOGGER.info("Reading the {} from {}.properties file", property, env);
        try {
            fileReader = new FileReader(propFile);
            properties.load(fileReader);
        } catch (IOException e) {
            LOGGER.error("Unable to read {} from {}.properties file", property, env);
            throw new RuntimeException(e);
        }
        return properties.getProperty(property);
    }

    public static Iterator<User> readDataFromPropertiesFile() {
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
}
