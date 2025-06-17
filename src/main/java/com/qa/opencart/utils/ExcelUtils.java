package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	public static final String REGISTER_TEST_DATA="/src/test/resources/testdata/OpenCartRegisterTestData.xlsx";
	public static Workbook book;
	public static Sheet sheet;


	public static Object[][] readDataFromExcel(String sheetName) {
		Object data[][]=null;
		try {
			FileInputStream io=new FileInputStream("./src/test/resources/testdata/OpenCartRegisterTestData.xlsx");
			book=WorkbookFactory.create(io);
			sheet=	book.getSheet(sheetName);
			data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i=0;i<sheet.getLastRowNum();i++) {
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				}
			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
