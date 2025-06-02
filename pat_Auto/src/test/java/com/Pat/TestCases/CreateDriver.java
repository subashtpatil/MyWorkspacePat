/*
package com.Pat.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class CreateDriver {
    private WebDriver driver;
    private static CreateDriver INSTANCE;
    private   ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    private CreateDriver()  {

    }

    public static synchronized CreateDriver getInstance(){
        if(INSTANCE == null){
            INSTANCE = new CreateDriver();
        }
        return INSTANCE;
    }

    public void setDriver(String browser){

        switch (browser.toLowerCase()){
            case "chrome":
                driver.set(new ChromeDriver());
                break;
            case "edge":
                driver.set(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("invalid browser");
        }

        driver = (DriverManager.getBrowserManager(browser).getDriver());
//        threadLocal.set(DriverManager.getBrowserManager(browser).getDriver());
    }

    public  WebDriver getDriver() {
        return driver.get();
         */
/* return threadLocal.get();*//*

    }

}


*/
