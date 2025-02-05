package com.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
To create a global logger that can be used across all the required classes
use singleton design pattern -
    create one object of logger and use it across the framework
*/
public class LoggerUtility {

    private static Logger logger;

    // private constructor for singleton design pattern
    private LoggerUtility() {
    }

    public static Logger getLogger(Class<?> clazz) {
        if (logger == null) {
            logger = LogManager.getLogger(clazz);
        }
        return logger;
    }


}
