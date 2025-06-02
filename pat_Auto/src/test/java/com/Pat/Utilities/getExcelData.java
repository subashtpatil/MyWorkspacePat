	package com.Pat.Utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

	public class getExcelData {

	@DataProvider(name= "getXLData", parallel=true)
	public static Object[][] getXLData(Method m) throws IOException{

		String sheetName = m.getName();



		String filepath= System.getProperty("user.dir")+"\\src\\test\\java\\com\\Pat\\TestData\\LoginData.xlsx";
		
		//FileInputStream fis= new FileInputStream("D:\\MyWorkspace\\pat_Auto\\src\\test\\java\\com\\Pat\\TestData\\LoginData.xlsx");
		
		FileInputStream fis= new FileInputStream(filepath);
		
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet ws=wb.getSheet("Sheet1");
		int totalRows= ws.getLastRowNum();
		int totalColumns= ws.getRow(0).getPhysicalNumberOfCells();
		
		//Object[][] data= new Object[totalRows][totalColumns];
		Object[][] data= new Object[totalRows-1][1];
		Hashtable<String,String> table= null;
		
		for(int rowNum=2;rowNum<=totalRows; rowNum++) {
			table = new Hashtable<String,String>();

			for (int colNum = 0; colNum < totalColumns; colNum++) {

				table.put(ws.getRow(1).getCell(colNum).toString(), ws.getRow(rowNum).getCell(colNum).toString() );


		data [rowNum-2][0]  = table;

			}
		}
		return data;
	}

}
