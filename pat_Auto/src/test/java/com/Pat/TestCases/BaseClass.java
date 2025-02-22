package com.Pat.TestCases;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;*/
import com.Pat.Utilities.Constants;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.Pat.Utilities.ReadConfig;
/*import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;*/

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;




import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass  {
	ReadConfig readconfig= new ReadConfig();

	public String baseURL= readconfig.getApplicationURL();
	/*public String strusername= readconfig.getusername();
	public String strpwd= readconfig.getPassword();*/
	public static WebDriver driver;

	//public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public static ExtentTest test;

	public ExtentSparkReporter htmlReporter;
	//public ExtentHtmlReporter htmlReporter;

	public static String screenshotName;
	public static String destination;

	@BeforeTest
	public void setExtent() {

		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName= "Test-Report-"+timeStamp+".html";

		htmlReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		//htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		htmlReporter.config().setDocumentTitle("PAT Automation Report");
		htmlReporter.config().setReportName("PAT UI Automation");
		//htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent= new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Application Name", "PAT");
		extent.setSystemInfo("User Name", "Shubash Patil");
		extent.setSystemInfo("Environment", "QA");		
	}

	@AfterTest
	public void endReport() {
		extent.flush();	
	}


	@Parameters("browser")
	@BeforeMethod
	public void setup(String br) throws Exception {
		if (br.equals("chrome"))
		{

			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			Thread.sleep(5000);			
		}else if (br.equals("edge"))
		{			
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			driver.manage().window().maximize();
			Thread.sleep(5000);			
		}

		driver.get(Constants.testsiteurl);       //this is getting data from my Constants file
		//driver.get(baseURL);
	}

	@AfterClass
	public void tearDown()
	{driver.quit();		
	}


	public static String getScreenshot( String screenshotName) throws IOException {
	//public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		// destination= System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName+dateName+".png";
		destination= System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName+dateName+".png";
		
		//String destination= System.getProperty("user.dir")+"/Screenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}


	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS "+result.getName().toUpperCase());
			test.log(Status.FAIL, "TEST CASE FAILED IS "+result.getThrowable());

			screenshotName = result.getName();

			String destination = getScreenshot(screenshotName);
			//getScreenshot(screenshotName);
			//getScreenshot(driver, screenshotName);
			test.addScreenCaptureFromPath(destination);

			//test.addScreenCaptureFromPath(destination);

		}else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "TEST CASE SKIPPED IS "+result.getName().toUpperCase());
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "TEST CASE PASSED IS "+result.getName().toUpperCase());
		}
	}
/*
	public void onFinish(ISuite arg0) {

		MonitoringMail mail = new MonitoringMail();

		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/LiveProject-PageObjectWithFactories/Extent_Report/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}*/


}
