package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=5;
	
	public static String generateEmailIDWithTimestamp() {
		Date dateemail = new Date();
		return "Testemail" + dateemail.toString().replace(" ", "_").replace(":", "_") + "@coollp.co";
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName) throws Exception
	{
		
		File excelfile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");
		XSSFWorkbook workbook=null;
		
		try {
			FileInputStream newfile = new FileInputStream(excelfile);
			workbook = new XSSFWorkbook(newfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int columns= sheet.getRow(1).getLastCellNum();
		XSSFRow row;
		XSSFCell cell;
		CellType celltype;
		
		
		Object [][] data = new Object[rows][columns];
		
		for (int i=0; i< rows;i++)
		{
			row = sheet.getRow(i+1);
			for (int j=0;j<columns;j++)
			{
				cell = row.getCell(j);
				celltype= cell.getCellType();
				
				switch(celltype) {
				
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]= Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]= cell.getBooleanCellValue();
					break;
				default:
					break;
				
					}
				}
				
			}
	 	return data;
		
	}
	public static String takescreenshot(WebDriver driver, String testName)
	{
		File srcscreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotdestinationpath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
		System.out.println(screenshotdestinationpath);
		try {
			FileHandler.copy(srcscreenshot, new File(screenshotdestinationpath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotdestinationpath;
	}
}


