package com.Pat.PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.Pat.TestCases.BaseClass;
import com.Pat.Utilities.myMethods;
import com.aventstack.extentreports.Status;

import static com.Pat.Utilities.myMethods.clickElement;
import static com.Pat.Utilities.myMethods.enterText;

public class LoginPage extends BaseClass{

	//WebDriverWait wait;

	public LoginPage(WebDriver rdriver) {		
		driver = rdriver;
		//PageFactory.initElements(driver, this);
		//wait = new WebDriverWait(driver, 60);


		//this.signinPage = new SigninPageLocators();
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10);
		PageFactory.initElements(factory, this);
	}

	@FindBy(name="userName")
	WebElement txtusername;

	@FindBy(name="password")
	WebElement txtpwd;	

	@FindBy(name="submit")
	WebElement btnSubmit;

	@FindBy(xpath="//*[contains(text(),'Enter your userName and password correct')]")
	WebElement ErrObj;

	@FindBy(xpath="//*[contains(text(),'Login Successfully')]")
	WebElement HomeObj;

	@FindBy(xpath="//*[contains(text(),'SIGN-OFF')]")
	WebElement LogOut;


	@FindBy(xpath="//*[contains(text(),'SIGN-ON')]")
	WebElement goBackToLogin;

	public void Login(String strusername, String strpwd) throws InterruptedException{
		//wait.until(ExpectedConditions.visibilityOf(txtusername));

		enterText(txtusername, strusername, "UserName");
		enterText(txtpwd, strpwd, "Password");
		clickElement(btnSubmit, "Log In");
		Thread.sleep(8000);

		boolean present= false;
		try {
			if(HomeObj.isDisplayed()) {
				String Homemsgtxt = myMethods.getText(HomeObj, "Home");
				test.log(Status.INFO, "Login successful with Home Page displayed as : "+Homemsgtxt);
				Reporter.log("Login successful with Home Page displayed as : "+Homemsgtxt);
				Thread.sleep(2000);

				if(LogOut.isDisplayed()) {
					myMethods.moveToElementAndClick(LogOut, driver, "LogOut");
				}
				Thread.sleep(5000);

				myMethods.moveToObj(goBackToLogin, driver, "Go Back To Login");
				String actTxt=myMethods.getText(goBackToLogin,  "Go Back To Login");
				Assert.assertEquals(actTxt, "Go back to log in");
				Thread.sleep(2000);
				present=true;
			}
		}catch (NoSuchElementException e) {

			test.log(Status.INFO, "Login UnSuccessful : "+e.getMessage());
			Reporter.log("Login UnSuccessful : "+e.getMessage());
			Thread.sleep(2000);	

		}finally {
			if((present==false)&&(ErrObj.isDisplayed())){
				String Errormsgtxt= myMethods.getText(ErrObj, " Whoops! ");

				test.log(Status.FAIL, "Login failed with Error msg : "+Errormsgtxt);
				Reporter.log("Login failed with Error msg : "+Errormsgtxt);
				Thread.sleep(2000);	
				driver.quit();
			}

		}


	}






}



