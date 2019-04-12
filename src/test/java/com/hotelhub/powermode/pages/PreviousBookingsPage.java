package com.hotelhub.powermode.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class PreviousBookingsPage extends Base {
	
	public PreviousBookingsPage()
	{
		PageFactory.initElements(driver(), this);
		
	}
	
	@FindBy(xpath="//button[contains(text(),'List bookings')]")
	public WebElement LIST_BOOKINGS;
		
	@FindBy(id="txtbookingnum")
	public WebElement BOOKING_REFERENCE;
	
	@FindBy(id="txtpnr")
	public WebElement PNR_SEARCH;

	@FindBy(id="txtguestname")
	public WebElement GUEST_NAME_SEARCH;
	
	@FindBy(id="ddlstatus")
	public WebElement BOOKING_STATUS;
	
	@FindBy(id="ddlmarket")
	public WebElement MARKET;
	
	@FindBy(id="ddldate")
	public WebElement SELECT_DATE;
	
	@FindBy(id="pbcheckin")
	public WebElement FROM_DATE;
	
	@FindBy(id="pbcheckout")
	public WebElement TO_DATE;
	
	@FindBy(id="txthotel")
	public WebElement HOTEL_NAME;
	
	@FindBy(id="ddlpos")
	public WebElement POINT_OF_SALE;
	
	@FindBy(id="ddlagency")
	public WebElement AGENCY;
	
	@FindBy(id="txtcustomer")
	public WebElement CUSTOMER;
	
	@FindBy(id="txtoidpcc")
	public WebElement OID_OR_PCC;
	
	@FindBy(id="txtagent")
	public WebElement AGENT;
	
	@FindBy(id="ddlcountry")
	public WebElement COUNTRY;
	
	@FindBy(id="txtcity")
	public WebElement CITY;
	
	
//	@FindBy(xpath="//div[@class='col-lg-4 align-self-center']")
//	public WebElement BOOKING_REFERENCE_LIST;
	
	@FindBy(xpath="(//div[@class='col-4'])[2]")
	public WebElement BOOKING_REFERENCE_LIST;
	
	@FindBy(xpath="//div[@class='col-4']")
	public WebElement REFERENCE_LIST;
	
	@FindBy(xpath="//div[@class='col-3']/a")
	public WebElement CHANNEL_LIST;
	
	@FindBy(xpath="//div[@class='col-5']/a")
	public List<WebElement> CUSTOMER_LIST;
	
	@FindBy(xpath="//div[@class='col-5']/ancestor::div[1]/following::div[1]//div[contains(@class,'col-6')]/a")
	public WebElement HOTEL_NAME_LIST;
	
	@FindBy(xpath="//div[@class='col-3']/ancestor::div[1]/following::div[1]//div[contains(@class,'col-6')]/span")
	public WebElement GUEST_NAME_LIST;
	
	@FindBy(xpath="//div[@class='col-6']/ancestor::div[1]/following::div[1]//div[contains(@class,'col-6')]/a")
	public WebElement BOOKED_DATE_LIST;
	
	@FindBy(xpath="//span[@class='pl-5']/a")
	public WebElement PNR_LIST;
	
	@FindBy(xpath="//a[contains(@data-original-title,'GDS PNR')]")
	public WebElement PNR_LINK;
	
	@FindBy(xpath="//textarea[@class='form-control']")
	public WebElement PNR_TEXT;
	
	@FindBy(xpath="//*[@id='pnrCrypticReadContent']/div[1]/button/i")
	public WebElement PNR_PAGE_CLOSE;
	
//	@FindBy(xpath="//div[@class='modal-header']/button")
//	public WebElement PNR_PAGE_CLOSE;
	
	@FindBy(xpath="//button[@class='btn bg-s-base4 btn-sm']")
	public WebElement VIEW_EDIT_BOOKING;
	
	
	@FindBy(xpath="//i[@data-original-title='Cancel booking']")
	public WebElement CANCEL_ICON;

	
	@FindBy(id="btncancelyes")
	public WebElement CANCEL_YES;
	

	@FindBy(id="lblcancelnum")
	public WebElement CANCELLATION_REFRENCE_NUMBER;

	@FindBy(xpath="//div[@id='lblcancelclose']//i[@class='fa fa-1x icon-nm-tct-cancel-1']")
	public WebElement CANCELLATION_CLOSE;
	
	
	
	
	
	
	
	
}
