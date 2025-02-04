package com.utility;

import com.ui.pojo.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {
    public static Iterator<User> getUserFromExcelFile(String fileName, String sheetName) {

        File excelFile = new File(System.getProperty("user.dir") + "//test-data//" + fileName);

        XSSFWorkbook xssfWorkbook;
        List<User> userList = new ArrayList<>();
        User user;
        XSSFSheet xssfSheet;
        Iterator<Row> rowIterator;
        Row row;

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
            throw new RuntimeException(e);
        }

        return userList.iterator();
    }

}
