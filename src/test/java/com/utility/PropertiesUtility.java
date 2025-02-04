package com.utility;

import com.constants.Env;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

//Read the properties file
public class PropertiesUtility {

    public static String readFromPropertiesFile(Env env, String property) {
        File propFile = new File(System.getProperty("user.dir") +
                "//config//" + env + ".properties");

        FileReader fileReader;
        Properties properties = new Properties();
        try {
            fileReader = new FileReader(propFile);
            properties.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(property.toUpperCase());


    }
}
