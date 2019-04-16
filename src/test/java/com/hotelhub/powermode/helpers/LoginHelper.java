package com.hotelhub.powermode.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.hotelhub.powermode.pages.HomePage;
import com.hotelhub.powermode.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;

import base.Base;
import base.PowerModeConstants;
import base.Property;


public class LoginHelper extends Base{
	
	
	RegressionHelper regressionHelper = new RegressionHelper();
	private Logger _logger = LogManager.getLogger(LoginHelper.class.getName());
	String Url;
	String env = "";
	public void login(ExtentTest test) throws Throwable
	{
		Property props = new Property();
		if(System.getProperty("env") !=null)
		{
			env = System.getProperty("env");
		}
		else
		{
			env = props.getProperty("env");
		}
		int ngurl=Integer.parseInt(env);
		switch(ngurl)
		{
		case 5055:
			Url=props.getProperty("PowerModeDev5055");
			break;
		case 5051:
			Url=props.getProperty("PowerModeDev5051");
			break;
		default:
			Url=props.getProperty("PowerModeAlpha");
			break;
		}
		
		String UserName=props.getProperty("UserName");
		String PassWord=props.getProperty("PassWord");
		driver().get(Url);
		LoginPage loginPage = new LoginPage();
		HomePage homePage = new HomePage();
		loginPage.userName.sendKeys(UserName);
		loginPage.passWord.sendKeys(PassWord);
		loginPage.signInButton.click();
		
		_logger.info("Login to the application successful");
		Thread.sleep(2000);
		getEcho();
		
//		homePage.HOTEL_HUB_LINK.click();
		
//		if(homePage.POWER_MODE.isDisplayed())
//		homePage.POWER_MODE.click();
//		if(homePage.BOOK_HOTEL.isDisplayed())
//		{
//			
//		}
		
		
	}
	
	
	public void verifyLoginFunctionality(ExtentTest test)throws Throwable
	{
		LoginPage loginPage = new LoginPage();
		Property props = new Property();
		String Url=props.getProperty("PowerMode");
		String UserName=props.getProperty("UserName");
		String PassWord=props.getProperty("PassWord");
		driver().get(Url);
	
		//Invalid UserName, Invalid Password
		loginPage.userName.clear();
		loginPage.userName.sendKeys(PowerModeConstants.INVALID_USERNAME);
		loginPage.passWord.clear();
		loginPage.passWord.sendKeys(PowerModeConstants.INVALID_PASSWORD);
		loginPage.signInButton.click();
		if(loginPage.loginErrorMessage.isDisplayed())
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Invalid Username and Invalid Password", "PASS", test);
		}
		else
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Invalid Username and Invalid Password", "FAIL", test);
		}
		
		//Valid UserName, Invalid Password
		loginPage.userName.clear();
		loginPage.userName.sendKeys(UserName);
		loginPage.passWord.clear();
		loginPage.passWord.sendKeys(PowerModeConstants.INVALID_PASSWORD);
		
		loginPage.signInButton.click();
		
		if(loginPage.loginErrorMessage.isDisplayed())
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Valid Username and Invalid Password", "PASS", test);
		}
		else
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Valid Username and Invalid Password", "FAIL", test);
		}
		
		//Invalid UserName, Valid Password
		loginPage.userName.clear();
		loginPage.userName.sendKeys(PowerModeConstants.INVALID_USERNAME);
		loginPage.passWord.clear();
		loginPage.passWord.sendKeys(PassWord);
		loginPage.signInButton.click();
		if(loginPage.loginErrorMessage.isDisplayed())
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Invalid Username and Valid Password", "PASS", test);
		}
		else
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Invalid Username and Valid Password", "FAIL", test);
		}
		

		//Valid UserName, Password less than 6 characters
		loginPage.userName.clear();
		loginPage.userName.sendKeys(UserName);
		loginPage.passWord.clear();
		loginPage.passWord.sendKeys(PowerModeConstants.INVALID_PASSWORD_LESS_THAN_SIX_CHARACTER);
		loginPage.signInButton.click();
		if(loginPage.loginErrorMessagePassWordLessThanSixCharacter.isDisplayed())
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Valid Username and Password less than SIX characters", "PASS", test);
		}
		else
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Valid Username and Password less than SIX characters", "FAIL", test);
		}
		
		
		//Valid UserName, Password more than 20 characters
		loginPage.userName.clear();
		loginPage.userName.sendKeys(UserName);
		loginPage.passWord.clear();
		loginPage.passWord.sendKeys(PowerModeConstants.INVALID_PASSWORD_MORE_THAN_TWENTY_CHARACTER);
		loginPage.signInButton.click();
		if(loginPage.loginErrorMessagePassWordMoreThanTwentyCharacter.isDisplayed())
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Valid Username and Password more than TWENTY characters", "PASS", test);
		}
		else
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Valid Username and Password more than TWENTY characters", "FAIL", test);
		}
		
		//Blank UserName, Blank Password
		loginPage.userName.clear();
		loginPage.userName.sendKeys("");
		loginPage.passWord.clear();
		loginPage.passWord.sendKeys("");
		loginPage.signInButton.click();
		if(loginPage.loginErrorMessageEmptyUserName.isDisplayed()&&loginPage.loginErrorMessageEmptyPassword.isDisplayed())
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Blank Username and Blank Password ", "PASS", test);
		}
		else
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Blank Username and Blank Password ", "FAIL", test);
		}
		
		
		
		//Valid UserName, Blank Password
		loginPage.userName.clear();
		loginPage.userName.sendKeys(UserName);
		loginPage.passWord.clear();
		loginPage.passWord.sendKeys("");
		loginPage.signInButton.click();
		if(loginPage.loginErrorMessageEmptyPassword.isDisplayed())
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Valid Username and Blank Password ", "PASS", test);
		}
		else
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Valid Username and Blank Password ", "FAIL", test);
		}
		
		//Blank UserName, Valid Password
		loginPage.userName.clear();
		loginPage.userName.sendKeys("");
		loginPage.passWord.clear();
		loginPage.passWord.sendKeys(PassWord);
		loginPage.signInButton.click();
		if(loginPage.loginErrorMessageEmptyUserName.isDisplayed())
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Blank Username and Valid Password ", "PASS", test);
		}
		else
		{
			regressionHelper.ScreenShot("Verification of Login Functionality with Blank Username and Valid Password ", "FAIL", test);
		}
		
	}
	
	
	public void getEcho()
    {
    	  String pageSource = driver().getPageSource();

    	  Pattern pattern = Pattern.compile("EchoToken(.*)TravellerDetails");
    	   Matcher matcher = pattern.matcher(pageSource);

    	  if (matcher.find()) {

    	   String placeholder = matcher.group(1);

    	   System.out.println(placeholder);

    	   String result = placeholder.replaceAll("[\\+\\.\\^:,]", "").replace("\"", "");

    	   reportingUtils.setEchoToken(result);

    

    	  }
    }
	
	@AfterMethod
	public void signOut() throws Throwable
	{
		LoginPage loginPage = new LoginPage();
		loginPage.signOut.click();
	}

	
	
}
