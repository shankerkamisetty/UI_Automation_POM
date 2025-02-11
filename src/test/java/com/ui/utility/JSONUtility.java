package com.ui.utility;

import com.constants.Env;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Read the content from .json file
public class JSONUtility {

    private static final Logger LOGGER = LogManager.getLogger(JSONUtility.class);

    public static Environment getEnvFromJsonFile(Env env) {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(String.valueOf(Paths.get("./config", "config.json")
                .toAbsolutePath()
                .normalize()));
        Config config;
        LOGGER.info("Reading {} environment specific config value from JSON file {}", env, jsonFile);
        try {
            config = objectMapper.readValue(jsonFile, Config.class);
        } catch (IOException e) {
            LOGGER.error("Unable to read the JSON file {}", jsonFile);
            throw new RuntimeException(e);
        }
        return config.getEnvironments().get(env.toString());
    }

    public static Iterator<User> readDataFromJsonFile(){
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(String.valueOf(Paths.get("./src","test","resources","test-data", "loginData.json")
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
}
