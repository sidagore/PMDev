package com.hotelhub.powermode.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class SearchHotelStep1Page extends Base {

	double randomDate=Math.random();
	
	public SearchHotelStep1Page()
	{
		PageFactory.initElements(driver(), this);
		
	}
	
	@FindBy(partialLinkText="Book a Hotel")
	public WebElement BOOK_HOTEL;
	
	@FindBy(id="txtDestinationType")
	public WebElement DESTINATION_TYPE;
	
	@FindBy(xpath="//*[@id='cboDestinationType']/div[3]/label")
	public WebElement DESTINATION_TYPE_HOTEL;
	
	@FindBy(xpath="//*[@id='cboDestinationType']/div[2]/label")
	public WebElement DESTINATION_TYPE_AIRPORT;
	
	@FindBy(xpath="//*[@id='cboDestinationType']/div[1]/label")
	public WebElement DESTINATION_TYPE_GOOGLE;
	
	@FindBy(id="TypeInDestination")
	public WebElement DESTINATION_SEARCH_INPUT;
	
	
	@FindBy(id="spanLoadingImageDestination")
	public static WebElement DESTINATION_SEARCH_SPINNER;
	
	@FindBy(xpath=".//*[@id='cboDestionList']/a[1]/div")
	public WebElement FIRST_RESULT;
	
//	(//div/i[contains(@class,'google')])[1]
	
	@FindBy(xpath="(//div[@type='GMAP'])[1]")
	public WebElement FIRST_GOOGLE_RESULT;		


	@FindBy(xpath="(//a[@type='IATA'])[1]")
	public WebElement FIRST_IATA_RESULT;
	
	@FindBy(xpath="(//div[@type='HOTEL'])[1]")
	public WebElement FIRST_HOTEL_RESULT;
	
	@FindBy(id="btnSearchHotel")
	public WebElement SEARCH_HOTELS_BUTTON;
	
	public By FIRST_HOTEL_RESULT_BY()
	{
		By FIRST_HOTEL_RESULT=By.xpath("(//div[@type='HOTEL'])[1]");
		return FIRST_HOTEL_RESULT;
		
	}
	public By FIRST_IATA_RESULT_BY()
	{
		By FIRST_HOTEL_RESULT=By.xpath("(//div[@type='IATA'])[1]");
		return FIRST_HOTEL_RESULT;
		
	}
	public By FIRST_GOOGLE_RESULT_BY()
	{
		By FIRST_HOTEL_RESULT=By.xpath("(//div[@type='GMAP'])[1]");
		return FIRST_HOTEL_RESULT;
		
	}
	@FindBy(id="btnSearchHotel")
	public By SEARCH_HOTELS_BUTTON_By;
	
	@FindBy(id="checkindate")
	public WebElement CHECK_IN_DATE;
	
	@FindBy(xpath="//div[@id='divcheckindate']//button[@class='btn btn-plain custom-btn-icon']")
	public WebElement CHECK_IN_DATE_ICON;
	
	@FindBy(xpath="//div[@id='divcheckoutdate']//button[@class='btn btn-plain custom-btn-icon']")
	public WebElement CHECK_OUT_DATE_ICON;
	
	@FindBy(xpath="//div[@id='divcheckindate']/button")
	public WebElement CHECK_IN_DATE_SELECTION_BUTTON;
	
	@FindBy(xpath="//th[@class='datepicker-switch']")
	public WebElement CHECK_IN_DATE_SELECTION_STEP1;
	
	@FindBy(xpath="html/body/div[7]/div[2]/table/thead/tr[2]/th[2]")
	public WebElement CHECK_IN_DATE_SELECTION_STEP2;
	
//	@FindBy(xpath="//tr/td/span[contains(@class,'year') and (text()='2019')]")
//	public WebElement CHECK_IN_DATE_SELECTION_YEAR;
	
	@FindBy(xpath="//div[@class='datepicker-years']//../tr/td/span[contains(@class,'year') and (text()='2019')]")
	public WebElement CHECK_IN_DATE_SELECTION_YEAR;

	
	@FindBy(xpath="//tr/td/span[contains(@class,'month') and (text()='Jul')]")
	public WebElement CHECK_IN_DATE_SELECTION_MONTH;
	
	@FindBy(xpath="//tbody/*/td[(@class='day') and (text()='15')]")
	public WebElement CHECK_IN_DATE_SELECTION_DAY;
	
	
	@FindBy(id="checkoutdate")
	public WebElement CHECK_OUT_DATE;

	@FindBy(id="spanTravllerHeader")
	public WebElement TRAVELLER_HEADER;
	
	@FindBy(id="spanHeaderCheckInCheckOut")
	public WebElement CHECKIN_CHECKOUT_DATE_HEADER;
	
	@FindBy(id="txtRPC3")
	public WebElement RATE_PLAN_CODE;
	
	
	@FindBy(id="btnSearchHotel")
	public WebElement SEARCH_HOTELS;
	
	
	@FindBy(xpath="//td[@class='active day']/following-sibling::td[3]")
	public WebElement CHECKIN_DATE_HEADER_NEXT_DAY;
	
	@FindBy(xpath="//td[@class='active day']")
	public WebElement CHECKIN_CHECKOUT_DATE_HEADER_NEXT_DAY;
	
	@FindBy(xpath="//button[(@id='btnCheckinCheckoutHeader') and contains(text(),'Proceed')]")
	public WebElement CHECKIN_CHECKOUT_DATE_HEADER_PROCEED;
	
	
	

}
