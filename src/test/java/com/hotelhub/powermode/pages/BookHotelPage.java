package com.hotelhub.powermode.pages;

import org.apache.logging.log4j.core.pattern.RegexReplacement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import utils.ReportingUtils;

public class BookHotelPage extends Base {
	
	ReportingUtils reportingUtils = new ReportingUtils();
	
	public BookHotelPage()
	{
		PageFactory.initElements(driver(), this);
		
	}
	
	@FindBy(linkText="Contact information")
	public WebElement CONTACT_INFORMATION;
	
	@FindBy(xpath="//span[@class='font-weight-normal font-md']")
	public WebElement TRAVELLER;
	
	@FindBy(id="1_Email")
	public WebElement NOTIFICATION_EMAIL_INPUT;
	
	@FindBy(linkText="Add new card")
	public WebElement ADD_NEW_CARD;
	
	@FindBy(id="1_PaymentMode")
	public WebElement PAYMENT_MODE_DROPDOWN;
	
	public static By CARD_TYPE_DROPDOWN() {
		By byObj = By.xpath("//div/label[contains(text(),'Card type')]/../select");
		return byObj;
	}
	
	@FindBy(xpath="//div/label[contains(text(),'Card type')]/../select")
	public WebElement CARD_TYPE_DROPDOWN;
	
	@FindBy(xpath="//div/label[contains(text(),'Card number')]/../input")
	public WebElement CARD_NUMBER;
	
	@FindBy(xpath="//div/label[contains(text(),'Name on card')]/../input")
	public WebElement NAME_CARD;
	
	@FindBy(xpath="//div/label[contains(text(),'Expiry month and year')]/../div/div[1]/select")
	public WebElement EXPIRY_MONTH_DROPDOWN;
	
	@FindBy(xpath="//div/label[contains(text(),'Expiry month and year')]/../div/div[2]/select")
	public WebElement EXPIRY_YEAR_DROPDOWN;
	
	@FindBy(id="1_CardExpiry")
	public WebElement EXPIRY_DATE;
	
	@FindBy(linkText="Add remarks")
	public WebElement ADD_REMARKS;
	
	@FindBy(xpath="//label[contains(text(),'Remarks for internal use')]/../textarea")
	public WebElement REMARKS_FOR_INTERNAL_USE;
	
	@FindBy(xpath="//label[contains(text(),'SI remarks')]/../textarea")
	public WebElement SI_REMARKS;
	
	@FindBy(id="txtinternalremarks")
	public WebElement INTERNAL_REMARKS;
	
	@FindBy(xpath="//label[contains(text(),'Instructions to hotel')]/../textarea")
	public WebElement INSTRUCTIONS_TO_HOTEL;
	
	@FindBy(id="btnProcessBooking")
	public WebElement PROCEED_BUTTON;
	
	@FindBy(id="imgLoading")
	public WebElement SPINNER;
	
	public static By SPINNER() {
		By byObj = By.id("imgLoading");
		return byObj;
	}
	
	public WebElement ELEMENT(String replace_char )
	{
		String xpath1="", xpath=xpath1.replaceAll("_var", replace_char);
		WebElement element = driver().findElement(By.xpath(xpath));
		return element;
	}
	
//	@FindBy(xpath="//label[@for='FulfillNow']")
//	public WebElement FULFILL_NOW;
	
	@FindBy(xpath="//*[@id='divrbffn']/label/span")
	public WebElement FULFILL_NOW;
	
	@FindBy(xpath="//label[@for='SendToHotel']")
	public WebElement SEND_TO_HOTEL;
	
	@FindBy(id="checkinffn")
	public WebElement CHECK_IN_FULLFILL_NOW;
	
	@FindBy(id="checkoutffn")
	public WebElement CHECK_OUT_FULLFILL_NOW;
	
	@FindBy(id="ddlroomffn")
	public WebElement ROOM_DESCRIPTION_FULLFILL_NOW;
	
	@FindBy(id="ddlrateffn")
	public WebElement RATE_DESCRIPTION_FULLFILL_NOW;
	
	@FindBy(id="rateperdayffn")
	public WebElement RATE_PER_DAY_FULLFILL_NOW;
	
	@FindBy(id="ddlcurrencyffn")
	public WebElement RATE_PER_DAY_CURRENCY_FULLFILL_NOW;
	
	@FindBy(xpath="//div[@class='form-row pt-3']//div[@class='col-lg-2 form-group']//input[@type='text']")
	public WebElement CONFIRMATION_NUMBER_FULLFILL_NOW;
	
	@FindBy(id="1_Email")
	public WebElement NOTIFICATION_EMAIL_FULLFILL_NOW;
	
	@FindBy(id="1_PaymentMode")
	public WebElement PAYMENT_MODE_FULLFILL_NOW;
	
	@FindBy(id="1_PaymentTypes")
	public WebElement PAYMENT_TYPE_FULLFILL_NOW;
	
	@FindBy(id="1_CardNumber")
	public WebElement CARD_NUMBER_FULLFILL_NOW;
	
	@FindBy(id="1_CardExpiry")
	public WebElement EXPIRY_DATE_FULLFILL_NOW;
	
	@FindBy(id="1_CardHolderName")
	public WebElement NAME_ON_CARD_FULLFILL_NOW;
	
	@FindBy(id="ddlonrqstreason")
	public WebElement ON_REQUEST_REASON_FULLFILL_NOW;
	
	@FindBy(xpath="//button[contains(text(),'Proceed')]")
	public WebElement ON_REQUEST_PROCEED_FULLFILL_NOW;
	
	@FindBy(id="txtNewPNR")
	public WebElement EXTRACT_TRAVELLER_INPUT;
	
	@FindBy(id="btnUseThisPNR")
	public WebElement EXTRACT_TRAVELLER_DETAILS;
	
	public static By EXTRACT_TRAVELLER_DETAILS_BY() {
		By byObj = By.id("btnUseThisPNR");
		return byObj;
	}
	
}
