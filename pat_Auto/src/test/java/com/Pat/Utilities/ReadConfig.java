package com.Pat.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties prop;

	public ReadConfig() {
		
		String filepath2=System.getProperty("user.dir")+"\\src\\test\\java\\Configurations\\config.properties";
		File src=  new File (filepath2);
		//File src=  new File ("D:\\MyWorkspace\\pat_Auto\\src\\test\\java\\Configurations\\config.properties");
		try {
			FileInputStream fis= new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getApplicationURL() {
		String url= prop.getProperty("baseURL");
		return url;		
	}
	
	public String getusername() {
		String strusername= prop.getProperty("username");
		return strusername;		
	}
	
	public String getPassword() {
		String strpwd= prop.getProperty("pwd");
		return strpwd;		
	}

}
