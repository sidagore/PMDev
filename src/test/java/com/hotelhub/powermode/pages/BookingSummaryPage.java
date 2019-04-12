package com.hotelhub.powermode.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class BookingSummaryPage extends Base {

	public BookingSummaryPage()
	{
		PageFactory.initElements(driver(), this);
		
	}
	
	
	@FindBy(id="viewbtn")
	public WebElement VIEW_BOOKING;
	
	@FindBy(id="cancelbtn")
	public WebElement CANCEL_BOOKING;
	
	@FindBy(xpath="//button[contains(text(),'Abandon Booking')]")
	public WebElement ABONDON_BOOKING;
	
	
	@FindBy(id="txtRemarks")
	public WebElement ABONDON_BOOKING_REMARKS;
	
	@FindBy(id="txtCancellationRef")
	public WebElement CANCELLATION_REFERENCE_NUMBER;
	
	@FindBy(xpath="//button[contains(text(),'Proceed')]")
	public WebElement CANCELLATION_PROCEED_BUTTON;
	
	
	@FindBy(xpath="//span[contains(text(),'Cancelled')]")
	public WebElement STATUS_CANCEL;
	
	@FindBy(xpath="//button[contains(text(),'Repeat booking')]")
	public WebElement REPEAT_BOOKING;
	
	
}
