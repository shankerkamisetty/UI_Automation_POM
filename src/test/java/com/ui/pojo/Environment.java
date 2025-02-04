package com.ui.pojo;

// this is part of Config pojo class
public class Environment {
    private String url;
    private int maxNumberOfAttempts;

    public int getMaxNumberOfAttempts() {
        return maxNumberOfAttempts;
    }

    public void setMaxNumberOfAttempts(int maxNumberOfAttempts) {
        this.maxNumberOfAttempts = maxNumberOfAttempts;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
