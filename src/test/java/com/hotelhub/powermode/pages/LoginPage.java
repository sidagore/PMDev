package com.hotelhub.powermode.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class LoginPage extends Base{
	
	

	public LoginPage()
	{
		PageFactory.initElements(driver(), this);
	}
	
	
	@FindBy(id="userID")
	public WebElement userName;
	
	@FindBy(id="Password")
	public WebElement passWord;
	
	@FindBy(id="loginErrorText")
	public WebElement loginErrorMessage;
	
	@FindBy(xpath="//span[contains(text(),'Please enter password')]")
	public WebElement loginErrorMessageEmptyPassword;
	
	@FindBy(xpath="//span[contains(text(),'Please enter user id')]")
	public WebElement loginErrorMessageEmptyUserName;
	
	@FindBy(xpath="//span[contains(text(),'Please enter user id')]")
	public WebElement loginErrorMessagePassWordMoreThanTwentyCharacter;
	
	@FindBy(xpath="//span[contains(text(),'Please enter user id')]")
	public By loginErrorMessagePassWordMoreThanTwentyCharacter_By;
	
	@FindBy(xpath="//span[contains(text(),'The Password must be at least 6 characters long.')]")
	public WebElement loginErrorMessagePassWordLessThanSixCharacter;
	
	@FindBy(id="AFT_Login")
	public WebElement signInButton;
	
	@FindBy(xpath="//li[contains(@id,'hhheader_lnkSignout')]/a")
	public WebElement signOut;
	

}
