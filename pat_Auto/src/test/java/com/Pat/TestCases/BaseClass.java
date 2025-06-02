package com.Pat.TestCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.Pat.Utilities.ReadConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.manager.SeleniumManager;
//https://www.google.com/search?sca_esv=61711e8701c58ca5&udm=7&sxsrf=AHTn8zqPAx7tQjEqE6YrlTI8Air5l81XXw:1747930038532&q=parallel+testing+using+threadlocal+by+SoftwaretestingbyMKT&sa=X&ved=2ahUKEwiOzqfoureNAxUPUGcHHcZeCxUQ8ccDKAJ6BAgWEAQ&biw=1532&bih=743&dpr=1.25#fpstate=ive&ip=1&vld=cid:2dcbb00e,vid:lOYGQTw9XBA,st:1279
public class BaseClass {
    ReadConfig readconfig = new ReadConfig();

    public String baseURL = readconfig.getApplicationURL();
    public String strusername = readconfig.getusername();
    public String strpwd = readconfig.getPassword();
    //protected static WebDriver driver;
    //private static ActionDriver actionDriver;
//    WebDriver driver;
    public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    // private static ThreadLocal<ActionDriver> actionDriver = new ThreadLocal<>();


    public ExtentSparkReporter htmlReporter;
    public ExtentReports extent;
    public static ExtentTest test;

    @BeforeTest
    public void startExtent() {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String repName = "Test-Report-" + timeStamp + ".html";

        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName);
        htmlReporter.config().setDocumentTitle("PAT Automation Report");
        htmlReporter.config().setReportName("PAT UI Automation");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Host Name", "localhost");
        extent.setSystemInfo("Application Name", "PAT");
        extent.setSystemInfo("User Name", "Shubash Patil");
        extent.setSystemInfo("Environment", "QA");
    }
/*

    //Driver setter method
    public void setDriver(WebDriver driver){
        tdriver.set(driver);
    }

    //Getter method for WebDriver
    public WebDriver getDriver() {
        if (driver == null) {
            //tdriver.get();
            System.out.println("WebDriver is not initialized");
            throw new IllegalStateException("WebDriver is not initialized");
        }
        return tdriver.get();
    }
*/

    //Getter method for ActionDriver
 /*   public static ActionDriver getActionDriver() {
        if (actionDriver.get() == null) {
            System.out.println("ActionDriver is not initialized");
            throw new IllegalStateException("ActionDriver is not initialized");
        }
        return actionDriver.get();
    }

    //Initialize ActionDriver for the current Thread
    actionDriver.set(new ActionDriver(getDriver()));
    Test.info("ActionDriver initialized for thread : "+Thread.currentThread.getThread);

  */

    @AfterTest
    public void endExtent() throws InterruptedException {

        extent.flush();
    }


    @Parameters("browser")
    @BeforeMethod
    public void setup(String br) throws Exception {
        if (br.equals("chrome")) {

            //WebDriverManager.chromedriver().setup();
            /* driver = new ChromeDriver();
             */
            threadLocalDriver.set(new ChromeDriver());
            threadLocalDriver.get().get(baseURL);
            System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
            //CreateDriver.getInstance().setDriver("chrome");
            threadLocalDriver.get().manage().window().maximize();
            Thread.sleep(5000);
        } else if (br.equals("edge")) {
            //WebDriverManager.edgedriver().setup();
//            driver = new EdgeDriver();
            threadLocalDriver.set(new EdgeDriver());
            threadLocalDriver.get().get(baseURL);
            System.out.println("The thread ID for EDGE" + Thread.currentThread().getId());
            threadLocalDriver.get().manage().window().maximize();
            Thread.sleep(5000);
        }
        // getDriver().get(baseURL);
    }

    @AfterClass
    public void tearDown() {
        threadLocalDriver.get().quit();
    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());
            test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());

            //String screenshotPath = getScreenshot(getDriver(), result.getName());
            // test.addScreenCaptureFromPath(screenshotPath);

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "TEST CASE SKIPPED IS " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "TEST CASE PASSED IS " + result.getName());
        }
        threadLocalDriver.get().quit();
    }



   /* public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\Screenshots\\" + screenshotName + dateName + ".png";

        //String destination= System.getProperty("user.dir")+"/Screenshots/"+screenshotName+dateName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }*/

}
