package com.ui.pojo;

import java.util.Map;

// to map with the data in config.json file
public class Config {
    Map<String, Environment> environments;

    public Map<String, Environment> getEnvironments() {
        return environments;
    }

    public void setEnvironments(Map<String, Environment> environments) {
        this.environments = environments;
    }
}
