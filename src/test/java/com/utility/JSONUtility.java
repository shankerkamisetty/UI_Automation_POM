package com.utility;

import com.constants.Env;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

//Read the content from .json file
public class JSONUtility {

    private static final Logger LOGGER = LogManager.getLogger(JSONUtility.class);

    public static Environment getEnvFromJsonFile(Env env) {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(System.getProperty("user.dir") + "//config//config.json");
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
}
