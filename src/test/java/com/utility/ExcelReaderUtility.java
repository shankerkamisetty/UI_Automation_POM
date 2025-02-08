package com.utility;

import com.ui.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {

    private static final Logger LOGGER = LogManager.getLogger(ExcelReaderUtility.class);

    public static Iterator<User> getUserFromExcelFile(String fileName, String sheetName) {

        File excelFile = new File(String.valueOf(Paths.get("./test-data", fileName)
                .toAbsolutePath()
                .normalize()));

        XSSFWorkbook xssfWorkbook;
        List<User> userList = new ArrayList<>();
        User user;
        XSSFSheet xssfSheet;
        Iterator<Row> rowIterator;
        Row row;
        LOGGER.info("Reading the data from Excel file {} ", fileName);
        try {
            //read the Excel(xlsx) file
            xssfWorkbook = new XSSFWorkbook(excelFile);

            //get sheet from Excel file
            xssfSheet = xssfWorkbook.getSheet(sheetName);
            rowIterator = xssfSheet.iterator();
            //read the first row and skip storing as the first row is header
            rowIterator.next();

            while (rowIterator.hasNext()) {
                //read the next row and store in row
                row = rowIterator.next();
                user = new User(row.getCell(0).toString(),
                        row.getCell(1).toString());
                userList.add(user);
            }
            xssfWorkbook.close();
        } catch (IOException | InvalidFormatException e) {
            LOGGER.error("Unable to read the Excel file {} ", fileName);
            throw new RuntimeException(e);
        }

        return userList.iterator();
    }

}
