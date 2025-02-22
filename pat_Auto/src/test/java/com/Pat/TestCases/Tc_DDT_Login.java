package com.Pat.TestCases;

import com.Pat.Utilities.getExcelData;
import org.testng.annotations.Test;

import com.Pat.PageObjects.LoginPage;
import com.Pat.Utilities.getExcelData_Original;

import java.util.Hashtable;

public class Tc_DDT_Login extends BaseClass {

	/*public String baseURL= readconfig.getApplicationURL();
	public String strusername= readconfig.getusername();
	public String strpwd= readconfig.getPassword();*/

	@Test(dataProviderClass = getExcelData.class, dataProvider ="getXLData" )
	public void Login(Hashtable<String,String> data) throws Exception {
		System.out.println("CHECKING IF HASHTABLE IS EMPTY  =  "+data.isEmpty());

		System.out.println("CONTENT OF HASHTABLE  "+data);





	/*	String strLoginType = data.get("LoginType");
		//test= extent.createTest(strLoginType);*/
		test= extent.createTest(data.get("LoginType"));
		LoginPage lp= new LoginPage(driver);

		String username = data.get("username");
		String password = data.get("password");
		lp.Login(username, password);
		
	}
	
}