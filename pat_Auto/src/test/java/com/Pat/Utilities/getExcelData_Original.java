package com.Pat.Utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class getExcelData_Original {

	@DataProvider(name= "getXLData")
	public Object[][] getXLData() throws IOException{
		
		String filepath= System.getProperty("user.dir")+"\\src\\test\\java\\com\\Pat\\TestData\\LoginData.xlsx";
		
		//FileInputStream fis= new FileInputStream("D:\\MyWorkspace\\pat_Auto\\src\\test\\java\\com\\Pat\\TestData\\LoginData.xlsx");
		
		FileInputStream fis= new FileInputStream(filepath);
		
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet ws=wb.getSheet("Sheet1");
		int totalRows= ws.getLastRowNum();
		int totalColumns= ws.getRow(0).getPhysicalNumberOfCells();
		
		Object obj[][]= new Object[totalRows][totalColumns];
		//Hashtable<String,String> table = new Hashtable<String,String>();
		
		for(int i=0; i<totalRows; i++) {
			for (int j = 0; j < totalColumns; j++) {

				obj[i][j] = ws.getRow(i + 1).getCell(j).toString();

				//table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				/*table.put("LoginType", ws.getRow(i + 1).getCell(j).getStringCellValue());
				table.put("username", ws.getRow(i + 1).getCell(j+1).getStringCellValue());
				table.put("password", ws.getRow(i + 1).getCell(j+2).getStringCellValue());
*/

				//obj[i][1] = ws.getRow(i + 1).getCell(1).toString();
				//obj[i][2] = ws.getRow(i + 1).getCell(2).toString();
				//obj[i][j] = ws.getRow(i + 1).getCell(j).
				System.out.println(obj[i][j]);

				//(obj[i][j])=table;
			}
		}
		return obj;
	}

}
