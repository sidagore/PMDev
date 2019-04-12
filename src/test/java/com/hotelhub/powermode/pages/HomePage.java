package com.hotelhub.powermode.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class HomePage extends Base {
	

	
	public HomePage()
	{
		PageFactory.initElements(driver(), this);
		
	}
	
	@FindBy(xpath=".//*[@id='navbarsExampleContainer']/form/button")
	public WebElement POWER_MODE;
	
	
	@FindBy(id="dropdown06")
	public WebElement MORE;
	
	@FindBy(xpath="//div[@class='container-fluid']//div//a[@class='navbar-brand text-primary']")
	public WebElement HOTEL_HUB_LINK;
	
	
//	@FindBy(xpath="//a[@class='nav-link dropdown-toggle']")
//	public WebElement MORE;
	
	@FindBy(xpath="//a[contains(text(),'Previous Bookings')]")
	public WebElement PREVIOUS_BOOKINGS;
	
	@FindBy(id="btnMoveToStep1")
	public WebElement BOOK_HOTEL;
	
	public static By BOOK_HOTEL() {
		By byObj = By.id("btnMoveToStep1");
		return byObj;
	}
	
//	@FindBy(id="dropdown03")
//	public WebElement SETTINGS_ICON;
	
	@FindBy(xpath="//i[@class='fa-4x icon-em-tct-settings-active-1x']")
	public WebElement SETTINGS_ICON;
	
	@FindBy(id="lnkLogout")
	public WebElement LOG_OUT;
	
	
	

}
