package com.utility;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtility {

    public static Iterator<User> getUserFromCSVFile(String fileName) {

        File csvFile = new File(System.getProperty("user.dir") + "//test-data//" + fileName);
        FileReader fileReader;
        CSVReader csvReader;
        String[] csvLine;
        List<User> loginData;
        User user;
        try {
            fileReader = new FileReader(csvFile);
            csvReader = new CSVReader(fileReader);
            loginData = new ArrayList<>();
            csvReader.readNext();

            while ((csvLine = csvReader.readNext()) != null) {
                user = new User(csvLine[0], csvLine[1]);
                loginData.add(user);
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return loginData.iterator();
    }
}
