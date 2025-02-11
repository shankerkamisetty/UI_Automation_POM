package com.ui.dataproviders;

import com.ui.pojo.User;
import com.ui.utility.CSVReaderUtility;
import com.ui.utility.ExcelReaderUtility;
import com.ui.utility.JSONUtility;
import com.ui.utility.PropertiesUtility;
import org.testng.annotations.DataProvider;

import java.util.Iterator;

public class LoginDataProvider {

    @DataProvider(name = "LoginTestFromJsonFile")
    public static Iterator<User> getUserDataFromJson() {
        return JSONUtility.readDataFromJsonFile();
    }

    @DataProvider(name = "LoginDataFromPropFile")
    public static Iterator<User> getUserDataFromPropertiesFile() {
        return PropertiesUtility.readDataFromPropertiesFile();
    }

    @DataProvider(name = "LoginDataFromCSVFile")
    public Iterator<User> readLoginDataFromCSVFile() {
        return CSVReaderUtility.getUserFromCSVFile("LoginData.csv");
    }

    @DataProvider(name = "LoginDataFromExcelFile")
    public Iterator<User> readLoginDataFromExcelFile() {
        return ExcelReaderUtility.getUserFromExcelFile("LoginData.xlsx", "LoginTestData");
    }

}
