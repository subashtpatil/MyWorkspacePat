package com.Pat.Utilities;

import com.Pat.TestCases.BaseClass;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;

public class myMethods extends BaseClass {

 
public static synchronized void copyfileFromLocaltoServer(String srcPath, String destnPath)
        throws IOException, InterruptedException {
    if (StringUtils.isBlank(srcPath)) {
        return;
    }
    File srcFile = new File(srcPath);
    File destFile = new File(destnPath, srcFile.getName());


    try {
                ///|| !FileUtils.contentEquals(srcFile, destFile)
        if (!destFile.exists() ) {
            test.log(Status.INFO, "Waiting for 5 milliseconds before copying file to server location : " + destnPath + "  :  From source path : " + srcPath);
            Reporter.log("Waiting for 5 milliseconds before copying file to server location : " + destnPath + "  :  From source path : " + srcPath, true);
             Thread.sleep(5000);
            FileUtils.copyFile(srcFile, destFile);
            test.log(Status.INFO, "Copying file to server location : " + destnPath + "  :  From source path : " + srcPath);
            Reporter.log("Copying file to server location : " + destnPath + "  :  From source path : " + srcPath, true);

        }
    }catch(Exception e){
            test.log(Status.INFO, "Copying file to server location FAILED: " + e.getMessage());
            Reporter.log("Copying file to server location FAILED: " + e.getMessage(), true);
    }
}



//////////######################################################
/**
 * Get text of a web element
 *
 * @param element - WebElement to perform click action
 * @param info    - Information about element
 */
public static String getText(WebElement element, String info) {
    System.out.println("Getting Text on element :: " + info+"      #####################      ");
    String text = null;
    try {
        text = element.getText();
        if (text.length() == 0) {
            text = element.getAttribute("innerText");
        }
        if (!text.isEmpty()) {
           // Reporter.log("The text is : " + text, true);
        } else {
            Reporter.log("Text Not Found"+"      #####################      ", true);
        }
    }catch (Exception e) {
        test.log(Status.INFO, "The getText method failed with msg : " + e.getMessage()+"      #####################      ");
        Reporter.log("The getText method failed with msg : " + e.getMessage()+"      #####################      ");
    }
    return text.trim();
}
///////////////##############################################
public static void enterText(WebElement element, String text, String elementName) {
    try {
        element.sendKeys(text);
        //this is for extent reports
        ChainTestListener.log("The text entered in element  : " +elementName+ " is : "+ text);
        test.log(Status.INFO, "The text entered in element  : " +elementName+ " is : "+ text);
        //this is for Console reports
        Reporter.log(text + " : is the Value entered for WebElement : " + elementName+"      #####################      ", true);
    } catch (Exception e) {
        ChainTestListener.log("The text WAS NOT entered in element  : " +elementName+ " is : "+ text);
        Reporter.log(text + " :  is not entered in the WebElement : " + elementName + " due to exception : " + e.getMessage()+"      #####################      ", true);
        test.log(Status.INFO, "The text  is not entered in the element  : " +elementName+ " Error is : "+ e.getMessage()+"      #####################      ");
        throw new AssertionError("Value is not entered in the WebElement" + elementName, e);
    }
}
//////////#####################################################
//////////######################################################
public static void clickElement(WebElement element, String elementName) {
try {
element.click();
    ChainTestListener.log("WebElement : " + elementName + " is clicked"+"      #####################      ");
Reporter.log("WebElement : " + elementName + " is clicked"+"      #####################      ", true);
test.log(Status.INFO, elementName+ ": is clicked"+"      #####################      " );
} catch (Exception e) {
    ChainTestListener.log("WebElement : " + elementName + " IS NOT clicked"+"      #####################      ");
test.log(Status.INFO, elementName+ ": is not clicked.  Error is :" + e.getMessage()+"      #####################      " );
Reporter.log(elementName+ ": is not clicked.  Error is :"  + e.getMessage()+"      #####################      ", true);
throw new AssertionError("unable to click " + elementName, e);
}
}

//////////######################################################
public static void validateField(WebElement element, String text, String elementName) {
   try {
       String actTxt = element.getText();
       boolean res;
       res = (actTxt == null || actTxt.length() == 0);
       if (res) {
           actTxt = element.getAttribute("value");
       }
       Assert.assertEquals(actTxt, text, "The  Actual and Expected Sub Trail value does not match with mainTrail for the field : " + elementName);
       Reporter.log(text + " : is the Value displayed on : " + elementName, true);
       test.log(Status.INFO, text + " : is the Value displayed on : " + elementName );
       if (text.length() == 0) {
           text = element.getAttribute("innerText");
       }
       if (!text.isEmpty()) {
           Reporter.log("The text is : " + text, true);
       } else {
           Reporter.log("Text Not Found", true);
       }
   }catch (Exception e){
       Reporter.log(text + " : is not the Value displayed on : " + elementName, true);
       test.log(Status.INFO, text + " : is not the Value displayed on : " + elementName );
       test.log(Status.INFO, " Error is : " + e.getMessage() );
   }

}



public static void moveToElementAndClick(/*WebElement from,*/ WebElement to, WebDriver driver, String text) {
    Actions actions = new Actions(driver);
   try {
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", to);
       to.click();
       // actions.clickAndHold(from).moveToElement(to).release(to).build().perform();
       test.log(Status.INFO,  "Scrolled to and clicked on element  : " + text +"      #####################      ");
       Reporter.log("Scrolled to and clicked on element  : " + text +"      #####################      ", true);
   }catch (Exception e){
       Reporter.log("Did not scroll to desired element and click  : " + text +"      #####################      ", true);
       test.log(Status.INFO,  "Did  not scroll to desired element and click:"+text+  "Error is : " + e.getMessage()+"      #####################      " );
   }
}

/*
public static void moveToObj(*/
/*WebElement from,*//*
 WebElement to,  String text) {
    Actions actions = new Actions(driver);
     try {
    	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", to);
         //actions.moveToElement(to).build().perform();
         Reporter.log("Scrolled to element  : " + text+"      #####################      " + true);
         test.log(Status.INFO,  "Scrolled to element  : " + text+"      #####################      " );
     } catch (Exception e){
         Reporter.log("Could not scroll to element  : " + text+"      #####################      ", true);
         test.log(Status.INFO,  "Could not scroll to element  : "+text+  "Error is : " + e.getMessage()+"      #####################      " );
     }
}
*/


//**
//* @param element
// * @param info
//* @return
 //*/

public static void IsSelected(WebElement element, String text, String expStatus) {
    Boolean b1=Boolean.valueOf(expStatus);
    Boolean selected = false;
    try{
        if (element != null) {
            selected = element.isSelected();
            Assert.assertEquals(selected, b1, "The checked/selected status does not match for the field : " + element + "  :  ");
            /*if (selected)
                Reporter.log("Element:  "+element+":: " + text + " is selected", true);
            else
                Reporter.log("Element :: " + text + " is not selected", true);*/
            Reporter.log(text + " checked/selected status is :  " + selected, true);
            test.log(Status.INFO, text + " checked/selected status is :  " + selected);
        }
    }catch (Exception e){
        Reporter.log(text + " checked/selected status is :  " + selected, true);
        test.log(Status.INFO, text + " checked/selected status is :  " + selected + "Error is : "+e.getMessage());
     }
    //return selected;
}


public static void IsEnabled(WebElement element, String text, boolean expStatus) {
    Boolean b1=Boolean.valueOf(expStatus);
    Boolean isenabled = false;
    try{
        if (element != null) {
            isenabled = element.isEnabled();
            Assert.assertEquals(isenabled, b1,  "The enabled status does not match for the button : "+element+ "  :  " );
        /*if (selected)
            Reporter.log("Element:  "+element+":: " + text + " is selected", true);
        else
            Reporter.log("Element :: " + text + " is not selected", true);*/
            Reporter.log( text + " enabled status is :  "+ isenabled, true);
            test.log(Status.INFO, text + " enabled status is :  "+ isenabled);
        }
       }catch (Exception e){
        Reporter.log(text + " enabled status is :  " + isenabled, true);
        test.log(Status.INFO, text +" enabled status is :  " +  e.getMessage());
    }
}

public static void IsRadioChecked(WebElement element, String text, boolean expStatus) {
    Boolean b1=Boolean.valueOf(expStatus);
    Boolean selected = false;
    try {
        if (element != null) {
            //selected = Boolean.valueOf(element.getAttribute("checked"));
            selected = element.isSelected();
            Assert.assertEquals(selected, b1, "The checked status does not match for the field : " + element + "  :  ");
        /*if (selected)
            Reporter.log("Element:  "+element+":: " + text + " is selected", true);
        else
            Reporter.log("Element :: " + text + " is not selected", true);*/
            Reporter.log(text + " radiobutton selected status is :  " + selected, true);
            test.log(Status.INFO, text + " radiobutton selected status is :  " + selected);
        }
    }catch (Exception e){
        Reporter.log(text + " radiobutton selected status is :  " + selected, true);
       // test.log(Status.INFO, text + " radiobutton selected status is :  " + selected,  "Error is : "+e.getMessage());
        test.log(Status.INFO, text + " radiobutton selected status is :  Error is : "+e.getMessage());
    }
}

//////////######################################################
public static void click_rB_cB_dDElement(WebElement element, String elementName, WebDriver driver) {
    try {
        //wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        JavascriptExecutor jse;
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", element);

        Reporter.log( elementName + " is clicked", true);
        test.log(Status.INFO, elementName + " is clicked");
    } catch (Exception e) {
        Reporter.log(elementName + " is not clicked " + e.getMessage(), true);
        test.log(Status.INFO, elementName + " is not clicked.  "+ e.getMessage());
        throw new AssertionError("unable to click " + elementName, e);
    }
}




}
