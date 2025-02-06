package com.utility;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtility {

    private static final Logger LOGGER = LogManager.getLogger(CSVReaderUtility.class);

    public static Iterator<User> getUserFromCSVFile(String fileName) {
        File csvFile = new File(System.getProperty("user.dir") + "//test-data//" + fileName);
        FileReader fileReader;
        CSVReader csvReader;
        String[] csvLine;
        List<User> loginData;
        User user;
        LOGGER.info("Reading the data from CSV file {} ", fileName);
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
            LOGGER.error("Unable to read the CSV file {} ", fileName);
            throw new RuntimeException(e);
        }

        return loginData.iterator();
    }
}
