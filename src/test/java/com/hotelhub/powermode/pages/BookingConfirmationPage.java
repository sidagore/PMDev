package com.hotelhub.powermode.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class BookingConfirmationPage extends Base  {
	
	public BookingConfirmationPage()
	{
		PageFactory.initElements(driver(), this);
		
	}
	
	
	@FindBy(xpath="//h2[@id='lblstatTestConfirmed']")
	public WebElement BOOKING_CONFIRMATION_SUCCESS_TEST_BOOKINGS;
	
	@FindBy(id="spnPMConfirmation")
	public WebElement BOOKING_CONFIRMATION_NUMBER;
	
	@FindBy(id="spnPMBookingReference")
	public WebElement BOOKING_REFERENCE_NUMBER;
	
	public static By BOOKING_REFERENCE_NUMBER() {
		By byObj = By.id("spnBookingReference");
		return byObj;
	}
	
	@FindBy(xpath="//span[contains(text(),'Check-in date')]/following-sibling::span")
	public WebElement CHECK_IN_DATE;
	
	@FindBy(xpath="//span[contains(text(),'Check-out date')]/following-sibling::span")
	public WebElement CHECK_OUT_DATE;
	
	@FindBy(xpath="//span[contains(text(),'Traveller')]/following-sibling::span[1]")
	public WebElement TRAVELLER;

	@FindBy(xpath="//span[contains(text(),'Email id')]/following-sibling::span[1]")
	public WebElement EMAIL_ID;
	
	@FindBy(id="txtbookingnum")
	public WebElement BOOKING_REFERENCE_NUMBER_ONREQUEST;
	
	
	
}
