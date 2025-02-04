package com.ui.pojo;

import java.util.List;

/* This pojo is for provide login data that maps with loginData.json
-- this pojo depicts data from the json
*/
public class TestData {
    private List<User> userLoginData;

    public List<User> getUserLoginData() {
        return userLoginData;
    }

    public void setUserLoginData(List<User> userLoginData) {
        this.userLoginData = userLoginData;
    }
}
