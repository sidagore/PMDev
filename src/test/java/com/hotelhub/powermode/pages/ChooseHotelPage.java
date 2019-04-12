package com.hotelhub.powermode.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class ChooseHotelPage extends Base{
	
	public ChooseHotelPage()
	{
		PageFactory.initElements(driver(), this);
		
	}
	
//	@FindBy(xpath="(//img[contains(@id,'ImgBusy')])[1]")
//	public WebElement FIRST_RESULT_SPIN;
	
	@FindBy(xpath="(//img[contains(@src,'/v1/Content/images/loading1.gif')])[1]")
	public WebElement FIRST_RESULT_SPIN;
	
	
	
	@FindBy(xpath="//span[@class='badge badge-default']")
	public WebElement hotelSearchResults;
	
	@FindBy(xpath="//button[contains(text,'Get rates')]")
	public WebElement GET_RATES;
	
	@FindBy(xpath="//div[contains(text(),'Aggregator')]")
	public WebElement AGGREGATOR_LINK;
	
	@FindBy(xpath="//div[contains(text(),'RoomIt rates')]")
	public WebElement ROOM_IT_RATES_LINK;
	
	@FindBy(xpath="//div[contains(text(),'On Request Rates')]")
	public WebElement ON_REQUEST_RATES_LINK;
	
	@FindBy(xpath="//div[contains(text(),'Public Rates')]")
	public WebElement PUBLIC_RATES_LINK;
	
	@FindBy(xpath="//div[contains(text(),'Client rates')]")
	public WebElement CLIENT_RATES_LINK;
	
	@FindBy(xpath="(//a[starts-with(@id,'viewdetails')])[1]")
	public WebElement VIEW_DETAILS;
	
	@FindBy(xpath="//a[contains(text(),'View more rates')and contains(@id,'_4')]")
	public WebElement VIEW_ALL_RATES_ROOMIT;
	
	@FindBy(xpath="//a[contains(text(),'View more rates')and contains(@id,'_25')]")
	public WebElement VIEW_ALL_RATES_PUBLIC;
	
	@FindBy(xpath="//a[contains(text(),'View more rates')and contains(@id,'_26')]")
	public WebElement VIEW_ALL_RATES_AGGREGATOR;
	
	@FindBy(xpath="//a[contains(text(),'View more rates')and contains(@id,'_27')]")
	public WebElement VIEW_ALL_RATES_ONRQUEST;
	
	@FindBy(xpath="//p[contains(text(),'FREE cancellation or modification until')]/ancestor::div[7]//span[contains(@data-original-title,'Booking.com')]/ancestor::div[3]//a[contains(text(),'Select Rate')]")
	public WebElement SELECT_RATE_FIRST_BCOM_CANCELLABLE;
	
	@FindBy(xpath="//p[contains(text(),'FREE cancellation or modification until')]/ancestor::div[7]//span[contains(@data-original-title,'Booking.com')]/ancestor::div[3]//a[contains(text(),'Auto Book')]")
	public WebElement AUTO_BOOK_FIRST_BCOM_CANCELLABLE;
	
	@FindBy(xpath="//div[contains(text(),'Cancellable')]/ancestor::div[1]/following-sibling::div/div/a[contains(text(),'Select Rate')]")
	public WebElement SELECT_RATE_FIRST_ONLINE_CANCELLABLE;
	
	@FindBy(xpath="//div[contains(text(),'Cancellable')]/ancestor::div[1]/following-sibling::div/div/a[contains(text(),'Auto Book')]")
	public WebElement AUTO_BOOK_FIRST_ONLINE_CANCELLABLE;
	
	@FindBy(xpath="//div/div/span[contains(@data-original-title,'CRS by CWT')]/ancestor::div[1]/following-sibling::div[contains(text(),'Cancellable')]/ancestor::div[1]/following-sibling::div/div/a[contains(text(),'Select Rate')]")
	public WebElement SELECT_RATE_FIRST_HHE_CANCELLABLE;
	
	@FindBy(xpath="//div/div/span[contains(@data-original-title,'CRS by CWT')]/ancestor::div[1]/following-sibling::div[contains(text(),'Cancellable')]/ancestor::div[1]/following-sibling::div/div/a[contains(text(),'Auto Book')]")
	public WebElement AUTO_BOOK_FIRST_HHE_CANCELLABLE;
	
	
//	@FindBy(xpath="//div/div/span[contains(@data-original-title,'Booking.com')]/ancestor::div[1]/following-sibling::div[contains(text(),'Cancellable')]/ancestor::div[1]/following-sibling::div/div/a[contains(text(),'Select Rate')]")
//	public WebElement SELECT_RATE_FIRST_BCOM_CANCELLABLE;
//	
//	@FindBy(xpath="//div/div/span[contains(@data-original-title,'Booking.com')]/ancestor::div[1]/following-sibling::div[contains(text(),'Cancellable')]/ancestor::div[1]/following-sibling::div/div/a[contains(text(),'Auto Book')]")
//	public WebElement AUTO_BOOK_FIRST_BCOM_CANCELLABLE;
//	
//	@FindBy(xpath="//div[contains(text(),'Cancellable')]/ancestor::div[1]/following-sibling::div/div/a[contains(text(),'Select Rate')]")
//	public WebElement SELECT_RATE_FIRST_ONLINE_CANCELLABLE;
//	
//	@FindBy(xpath="//div[contains(text(),'Cancellable')]/ancestor::div[1]/following-sibling::div/div/a[contains(text(),'Auto Book')]")
//	public WebElement AUTO_BOOK_FIRST_ONLINE_CANCELLABLE;
//	
//	@FindBy(xpath="//div/div/span[contains(@data-original-title,'CRS by CWT')]/ancestor::div[1]/following-sibling::div[contains(text(),'Cancellable')]/ancestor::div[1]/following-sibling::div/div/a[contains(text(),'Select Rate')]")
//	public WebElement SELECT_RATE_FIRST_HHE_CANCELLABLE;
//	
//	@FindBy(xpath="//div/div/span[contains(@data-original-title,'CRS by CWT')]/ancestor::div[1]/following-sibling::div[contains(text(),'Cancellable')]/ancestor::div[1]/following-sibling::div/div/a[contains(text(),'Auto Book')]")
//	public WebElement AUTO_BOOK_FIRST_HHE_CANCELLABLE;
	
	@FindBy(xpath="//button[contains(text(),'On request')]")
	public WebElement ONREQUEST_BUTTTON;
		
	@FindBy(xpath="//h5[@class='has-no-margin']")
	public WebElement ROOMS_RATES;
	
	public static By ROOMS_RATES_BY() {
		By byObj = By.xpath("//h5[@class='has-no-margin']");
		return byObj;
	}
	
	public static By NO_ROOM_RATES_BY() {
		By byObj = By.xpath("//span[contains(text(),'No room or rate found.')]");
		return byObj;
	}
	
	
	@FindBy(xpath="(//div[contains(@id,'hotelinfopm')]/p)[1]")
	public WebElement HOTEL_INFORMATION_ADDRESS;
	
	@FindBy(xpath="(//div[contains(@id,'hotelinfopm')]/div)[1]/div/p")
	public WebElement HOTEL_INFORMATION_CONTACT;
	
	@FindBy(xpath="(//div[contains(@id,'hotelinfopm')]/p)[2]")
	public WebElement HOTEL_INFORMATION_DESCRIPTION;
	
	@FindBy(xpath="//a[contains(text(),'Read more...')]")
	public WebElement HOTEL_INFORMATION_DESCRIPTION_READ_MORE;
	
	
	@FindBy(xpath="//h6[contains(text(),'General Description')]")
	public WebElement HOTEL_INFORMATION_DESCRIPTION_READ_MORE_GENERAL_DESCRIPTION;
	
	@FindBy(xpath="//div[@id='pmmodalpopup']//i[@class='fa fa-1x icon-nm-tct-cancel-1']")
	public WebElement HOTEL_INFORMATION_DESCRIPTION_READ_MORE_CLOSE;
	
		
	@FindBy(xpath="//div[@class='gm-style']")
	public WebElement HOTEL_INFORMATION_MAP;
	
	
	@FindBy(xpath="//button[@title='Zoom in']")
	public WebElement HOTEL_INFORMATION_MAP_ZOOM_IN;

	@FindBy(xpath="//button[@title='Zoom out']")
	public WebElement HOTEL_INFORMATION_MAP_ZOOM_OUT;
	
	@FindBy(xpath="//div[6]//div[2]//div[2]//div[1]//div[2]//img[1]")
	public WebElement HOTEL_INFORMATION_HOTEL_IMAGE;
	
	@FindBy(xpath="//span[contains(text(),'Photos')]")
	public WebElement HOTEL_INFORMATION_HOTEL_IMAGE_MORE_PHOTOS;
	
	@FindBy(xpath="//span[@class='carousel-control-prev-icon']")
	public WebElement HOTEL_INFORMATION_HOTEL_IMAGE_MORE_PHOTOS_PREVIOUS;
	
	@FindBy(xpath="//span[@class='carousel-control-next-icon']")
	public WebElement HOTEL_INFORMATION_HOTEL_IMAGE_PHOTOS_NEXT;

	@FindBy(xpath="//div[@id='morephotos']//i[@class='fa fa-1x icon-nm-tct-cancel-1']")
	public WebElement HOTEL_INFORMATION_HOTEL_IMAGE_MORE_PHOTOS_CLOSE;
	
//	
//	
//	
//	@FindBy(xpath="(//h6[contains(text(),'Cancellation Policy')]//following::div[1])[1]")
//	public List<WebElement> ROOM_DETAILS_CANCELLATION_POLICY;
//	
//	@FindBy(xpath="(//h6[contains(text(),'Average Rate Per Night')]//following::div[1])[1]")
//	public List<WebElement> ROOM_DETAILS_AVERAGE_RATE_PER_NIGHT;
//	
//	@FindBy(xpath="(//h6[contains(text(),'Total Amount (Subject to taxes)')]//following::div[1])[1]")
//	public List<WebElement> ROOM_DETAILS_TOTAL_AMOUNT;
//	
//	@FindBy(xpath="(//h6[(text()='Tax')]//following::div[1])[1]")
//	public List<WebElement> ROOM_DETAILS_TAX;
//	
//	@FindBy(xpath="(//h6[contains(text(),'Room Description')]//following::div[1])[1]")
//	public List<WebElement> ROOM_DETAILS_ROOM_DESCRIPTION;
//	
//	@FindBy(xpath="(//h6[contains(text(),'Rate Description')]//following::div[1])[1]")
//	public List<WebElement> ROOM_DETAILS_RATE_DESCRIPTION;
//	
//	@FindBy(xpath="(//h6[contains(text(),'Additional room/rate details')]//following::div[1])[1]")
//	public List<WebElement> ROOM_DETAILS_ADDITIONAL_DETAILS;
//	
//	@FindBy(xpath="(//h6[contains(text(),'Meal Plan')]//following::div[1])[1]")
//	public List<WebElement> ROOM_DETAILS_MEAL_PLAN;
//	
//	@FindBy(xpath="(//h6[contains(text(),'Tax Description')]//following::div[1])[1]")
//	public List<WebElement> ROOM_DETAILS_TAX_DESCRIPTION;
//	
//	@FindBy(xpath="(//h6[contains(text(),'Agency Information')]//following::div[1])[1]")
//	public List<WebElement> ROOM_DETAILS_AGENCY_INFORMATION;
//	
//	@FindBy(xpath="(//h6[contains(text(),'Check-in instructions')]//following::div[1])[1]")
//	public List<WebElement> ROOM_DETAILS_CHECK_IN_INSTRUCTIONS;
//	
//	@FindBy(xpath="(//h6[contains(text(),'Other Information')]//following::div[1])[1]")
//	public List<WebElement> ROOM_DETAILS_OTHER_INFORMATION;
//	
//	@FindBy(xpath="(//h6[contains(text(),'Guarantee Policy')]//following::div[1])[1]")
//	public List<WebElement> ROOM_DETAILS_GUARANTEE_POLICY;
	
	
	
	
	
	
	
	
	
	@FindBy(xpath="//h6[contains(text(),'Cancellation Policy')]//following::div[1]")
	public List<WebElement> ROOM_DETAILS_CANCELLATION_POLICY;
	
	@FindBy(xpath="//h6[contains(text(),'Average Rate Per Night')]//following::div[1]")
	public List<WebElement> ROOM_DETAILS_AVERAGE_RATE_PER_NIGHT;
	
	@FindBy(xpath="//h6[contains(text(),'Total Amount (Subject to taxes)')]//following::div[1]")
	public List<WebElement> ROOM_DETAILS_TOTAL_AMOUNT;
	
	@FindBy(xpath="//h6[(text()='Tax')]//following::div[1]")
	public List<WebElement> ROOM_DETAILS_TAX;
	
	@FindBy(xpath="//h6[contains(text(),'Room Description')]//following::div[1]")
	public List<WebElement> ROOM_DETAILS_ROOM_DESCRIPTION;
	
	@FindBy(xpath="//h6[contains(text(),'Rate Description')]//following::div[1]")
	public List<WebElement> ROOM_DETAILS_RATE_DESCRIPTION;
	
	@FindBy(xpath="//h6[contains(text(),'Additional room/rate details')]//following::div[1]")
	public List<WebElement> ROOM_DETAILS_ADDITIONAL_DETAILS;
	
	@FindBy(xpath="//h6[contains(text(),'Meal Plan')]//following::div[1]")
	public List<WebElement> ROOM_DETAILS_MEAL_PLAN;
	
	@FindBy(xpath="//h6[contains(text(),'Tax Description')]//following::div[1]")
	public List<WebElement> ROOM_DETAILS_TAX_DESCRIPTION;
	
	@FindBy(xpath="//h6[contains(text(),'Agency Information')]//following::div[1]")
	public List<WebElement> ROOM_DETAILS_AGENCY_INFORMATION;
	
	@FindBy(xpath="//h6[contains(text(),'Check-in instructions')]//following::div[1]")
	public List<WebElement> ROOM_DETAILS_CHECK_IN_INSTRUCTIONS;
	
	@FindBy(xpath="//h6[contains(text(),'Other Information')]//following::div[1]")
	public List<WebElement> ROOM_DETAILS_OTHER_INFORMATION;
	
	@FindBy(xpath="//h6[contains(text(),'Guarantee Policy')]//following::div[1]")
	public List<WebElement> ROOM_DETAILS_GUARANTEE_POLICY;
	
	
	
	

}
