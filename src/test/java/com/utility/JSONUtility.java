package com.utility;

import com.constants.Env;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.pojo.Config;

import java.io.File;
import java.io.IOException;
//Read the content from .json file
public class JSONUtility {

    public static String getEnvFromJsonFile(Env env) {


        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(System.getProperty("user.dir") + "//config//config.json");
        Config config;

        try {
            config = objectMapper.readValue(jsonFile, Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return config.getEnvironments().get(env.toString()).getUrl();
    }
}
