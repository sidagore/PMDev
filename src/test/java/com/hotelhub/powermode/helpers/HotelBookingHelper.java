package com.hotelhub.powermode.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.SimpleAttributeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hotelhub.powermode.pages.BookHotelPage;
import com.hotelhub.powermode.pages.BookingConfirmationPage;
import com.hotelhub.powermode.pages.BookingSummaryPage;
import com.hotelhub.powermode.pages.ChooseHotelPage;
import com.hotelhub.powermode.pages.HomePage;
import com.hotelhub.powermode.pages.LoginPage;
import com.hotelhub.powermode.pages.PreviousBookingsPage;
import com.hotelhub.powermode.pages.SearchHotelStep1Page;
import com.hotelhub.powermode.pages.TravellersDetailsStep1Page;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.Base;
import base.PowerModeConstants;
import base.Property;


public class HotelBookingHelper extends Base {

	private Logger _logger = LogManager.getLogger(HotelBookingHelper.class.getName());
	
	public static String PNR;
	public static String Hotel;
	public static String BookingType;
	public static String BookingFlow;
	
	WebDriverWait wait=new WebDriverWait(driver(), 50);
	
	JavascriptExecutor js = (JavascriptExecutor) driver();
	public static String BookingReferenceNumber="";
	public static String BookingReferenceNumber1="";
	public static String ConfirmationNumber="";
	public static String[][] readinput() throws IOException
	{
		
		FileInputStream fi = new FileInputStream("D:\\TCT\\input.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fi);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int totalNoOfRows = sheet.getLastRowNum();
		int count=0;
		for(int k=0;k<totalNoOfRows+1;k++)
		{
			if(sheet.getRow(k).getCell(2).toString().toLowerCase().equals("yes"))
			{
				
				
								count=count+1;
			}
			
		}
	
		String[][] arrayExcel = new String[count][2];
		String[][] arrayExcelData = new String[totalNoOfRows][2];
		for (int i= 0 ; i < totalNoOfRows; i++) {
			
			if(sheet.getRow(i+1).getCell(2).toString().toLowerCase().equals("yes"))
			{
				for (int j=0; j < 2; j++) {
					arrayExcel[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
		}
		return arrayExcel;
	}
	
	public void initializeInputData() throws IOException
	{
		PNR=readinput()[0][0];
		Hotel=readinput()[0][1];
		
		
	}
	
	
	public void enterCustomerOrPNROrReferenceNumber(String PNR, String GDS) throws Throwable
	{
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Enter the PNR and Select Customer" + "<b>");
		HomePage homePage = new HomePage();
		TravellersDetailsStep1Page travellersDetailsStep1Page = new TravellersDetailsStep1Page();
		String[] EchoToken=driver().getPageSource().split("Echo", 12);
		this.PNR=PNR;

		_logger.info("Click on Book a Hotel button");
		wait.until(ExpectedConditions.visibilityOf(homePage.BOOK_HOTEL));
		ScreenShot("Login to the application successful", "PASS", test);
		homePage.BOOK_HOTEL.click();

		travellersDetailsStep1Page.TRAVELLER_DETAILS_INPUT.sendKeys(PNR);
		if(GDS.equals("AMADEUS"))
		{
			Select select = new Select(travellersDetailsStep1Page.SELECT_GDS);
			select.selectByVisibleText("Amadeus");
		}

		reportingUtils.setInputData(PNR);
		Thread.sleep(1000);
		travellersDetailsStep1Page.FIRST_TRAVELLER.click();
		ScreenShot("Traveller selection successful", "INFO", test);
		try
		{
			Thread.sleep(2000);
			travellersDetailsStep1Page.PROCEED_BUTTON.click();
		}
		catch(Exception e)
		{
			
		}

	}
	
	//Search hotel for online or On request Booking
	public void searcHotel(String searchKey) throws InterruptedException
	{
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Enter the hotel search input" + "<b>");
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		this.Hotel=searchKey;
		searchHotelStep1Page.DESTINATION_SEARCH_INPUT.sendKeys(searchKey);
//		searchHotelStep1Page.DESTINATION_SEARCH_INPUT.sendKeys(Hotel);
		Thread.sleep(5000);
		searchHotelStep1Page.FIRST_RESULT.click();
		Thread.sleep(2000);
		ScreenShot("Search input successful", "INFO", test);

	}
	
	
	public void goToCheckInDateOnline()
	{
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_BUTTON.click();
	}
	
	public void selectCheckInCheckOutDate() throws InterruptedException
	{
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Select Check-In and Check-Out date" + "<b>");
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		int randomDay = (int)(Math.random()*(20-10))+10;
//		Random r = new Random();
//		int result = r.nextInt(high-low) + low;
		System.out.println("randomDay "+randomDay);
		
		driver().manage().window().setSize(new Dimension(1920, 1080));

//		JavascriptExecutor js = (JavascriptExecutor) driver();
//		js.executeScript("window.scrollBy(0,-500)");
		
		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_BUTTON.click();
		
		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_STEP1.click();
//		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_STEP2.click();
//		JavascriptExecutor executor = (JavascriptExecutor)driver();
//		executor.executeScript("document.body.style.zoom = '0.95'");
//		Actions actions = new Actions(driver());
//		actions.moveToElement(searchHotelStep1Page.CHECK_IN_DATE_SELECTION_YEAR).perform();
		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_MONTH.click();
//		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_DAY.click();
		String xpath="//tbody/*/td[(@class='day') and (text()="+randomDay+")]";
		Thread.sleep(2000);
		driver().findElement(By.xpath(xpath)).click();
		int checkOutDate=randomDay+1;
		String xpath_checkout="//tbody/*/td[contains(@class,'day') and (text()="+checkOutDate+")]";
		driver().findElement(By.xpath(xpath_checkout)).click();
		ScreenShot("Check in - Check out date selection successful", "INFO", test);
		
		String CheckInCheckOut=searchHotelStep1Page.CHECKIN_CHECKOUT_DATE_HEADER.getText();
		
//		12-May-2019/13-May-2019
		
		System.out.println("Check In Check Out "+CheckInCheckOut);
		
		String CheckIn = CheckInCheckOut.substring(0, CheckInCheckOut.indexOf("/"));
		String CheckOut = CheckInCheckOut.substring(CheckIn.length()+1);
		
		reportingUtils.setCheckInDate(CheckIn);
		reportingUtils.setCheckOutDate(CheckOut);
		
		if(Hotel.contains("Jessica Wonderful"))
			searchHotelStep1Page.RATE_PLAN_CODE.sendKeys(PowerModeConstants.RATE_PLAN_CODE_DATA3);
		Thread.sleep(2000);
		if(Hotel.contains(PowerModeConstants.HHE_BOOKING_TEST_HOTEL)||Hotel.contains(PowerModeConstants.SABRE_BOOKING_TEST_HOTEL_ONLINE)||Hotel.contains(PowerModeConstants.AMADEUS_BOOKING_TEST_HOTEL_ONLINE))
		searchHotelStep1Page.SEARCH_HOTELS.click();
		
		

//		ScreenShot("Hotel Listing successful", "INFO", test);
		
		
	}
	
	public void clickOnSearchHotels() throws InterruptedException
	{
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		searchHotelStep1Page.SEARCH_HOTELS.click();
	}
	
	//Choose Hotel for Online or On request hotel
	public void chooseHotel(String BookingType,String BookingFlow) throws InterruptedException
	{
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Choose the hotel" + "<b>");
		ChooseHotelPage chooseHotelPage = new ChooseHotelPage();
		this.BookingType =BookingType;
		this.BookingFlow=BookingFlow;
		try
		{
			Thread.sleep(5000);
			driver().findElement(By.xpath("//button[contains(text(),'Get rates')]")).click();
		}
		catch(Exception e)
		{
			
		}
			
		
		
		if(BookingType.equals("BCOM"))
		{
			Thread.sleep(2000);
			chooseHotelPage.VIEW_DETAILS.click();
			Thread.sleep(1000);
			chooseHotelPage.VIEW_DETAILS.click();
//			chooseHotelPage.AGGREGATOR_LINK.click();
			
			wait.until(ExpectedConditions.visibilityOf(chooseHotelPage.VIEW_ALL_RATES_AGGREGATOR));
			Thread.sleep(2000);
			chooseHotelPage.VIEW_ALL_RATES_AGGREGATOR.click();
			Thread.sleep(2000);
			if(BookingFlow.toLowerCase().equals("auto book"))
			{
				
				chooseHotelPage.AUTO_BOOK_FIRST_BCOM_CANCELLABLE.click();
			}
			else
			{
				Thread.sleep(2000);
				chooseHotelPage.SELECT_RATE_FIRST_BCOM_CANCELLABLE.click();
			}

			
			
		}
	
		if(BookingType.equals("SABRE"))
		{
			String xpath="//span[contains(text(),\"_hotelName\")]";
			String xpath_replace=xpath.replaceAll("_hotelName", Hotel);
			Thread.sleep(5000);
//			driver().findElement(By.xpath(xpath_replace)).click();
			chooseHotelPage.ROOM_IT_RATES_LINK.click();
			Thread.sleep(5000);
			chooseHotelPage.VIEW_ALL_RATES_ROOMIT.click();
			if(BookingFlow.toLowerCase().equals("auto book"))
			{
				
				chooseHotelPage.AUTO_BOOK_FIRST_BCOM_CANCELLABLE.click();
			}
			else
			chooseHotelPage.SELECT_RATE_FIRST_ONLINE_CANCELLABLE.click();	
		}
		

		
		if(BookingType.equals("AMADEUS"))
		{
//			chooseHotelPage.PUBLIC_RATES_LINK.click();
//			chooseHotelPage.ROOM_IT_RATES_LINK.click();
			wait.until(ExpectedConditions.visibilityOf(chooseHotelPage.VIEW_ALL_RATES_PUBLIC));
			chooseHotelPage.VIEW_ALL_RATES_PUBLIC.click();
			if(BookingFlow.toLowerCase().equals("auto book"))
			{
				
				chooseHotelPage.AUTO_BOOK_FIRST_ONLINE_CANCELLABLE.click();
			}
			else
			chooseHotelPage.SELECT_RATE_FIRST_ONLINE_CANCELLABLE.click();
			
		}
		

		if(BookingType.equals("HHE"))
		{
			
//			chooseHotelPage.PUBLIC_RATES_LINK.click();
			wait.until(ExpectedConditions.visibilityOf(chooseHotelPage.VIEW_ALL_RATES_PUBLIC));
			chooseHotelPage.VIEW_ALL_RATES_PUBLIC.click();

			if(BookingFlow.toLowerCase().equals("auto book"))
			{
				
				chooseHotelPage.AUTO_BOOK_FIRST_HHE_CANCELLABLE.click();
			}
			else

			chooseHotelPage.SELECT_RATE_FIRST_HHE_CANCELLABLE.click();	
		}
		
		if(BookingType.equals("ONREQUEST"))
		{
			wait.until(ExpectedConditions.visibilityOf(chooseHotelPage.ONREQUEST_BUTTTON));
			Thread.sleep(1000);
			chooseHotelPage.ONREQUEST_BUTTTON.click();
		}
	
	
		ScreenShot("Hotel Selection Successful", "INFO", test);
		chooseOtherRoom();
	}
	
	public void chooseOtherRoom() throws InterruptedException
	{
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		ChooseHotelPage chooseHotelPage = new ChooseHotelPage();
		if(driver().findElements(chooseHotelPage.NO_ROOM_RATES_BY()).size()>=1)
		{
			Thread.sleep(5000);
			JavascriptExecutor js = (JavascriptExecutor) driver();
			js.executeScript("window.scrollBy(0,-500)");
			searchHotelStep1Page.CHECKIN_CHECKOUT_DATE_HEADER.click();
			Thread.sleep(5000);
			searchHotelStep1Page.CHECKIN_DATE_HEADER_NEXT_DAY.click();
			searchHotelStep1Page.CHECKIN_CHECKOUT_DATE_HEADER_NEXT_DAY.click();
			Thread.sleep(2000);
			searchHotelStep1Page.CHECKIN_CHECKOUT_DATE_HEADER_PROCEED.click();
			chooseHotel(BookingType,BookingFlow);
		}
	}
	
	public void addNewCard(String CardType) throws InterruptedException
	{
		BookHotelPage bookHotelPage = new BookHotelPage();
		bookHotelPage.ADD_NEW_CARD.click();
		Thread.sleep(1000);
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Enter the new card details" + "<b>");
		
//		Select s=new Select(bookHotelPage.CARD_TYPE_DROPDOWN);
//		s.selectByVisibleText("Visa");
		bookHotelPage.CARD_NUMBER.sendKeys(CardType);
		bookHotelPage.NAME_CARD.sendKeys("Test User");
		bookHotelPage.EXPIRY_DATE.sendKeys("0122");
//		
//
//		Select s1=new Select(bookHotelPage.EXPIRY_MONTH_DROPDOWN);
//		s1.selectByVisibleText("JAN");
//		
//		Select s2=new Select(bookHotelPage.EXPIRY_YEAR_DROPDOWN);
//		s2.selectByVisibleText("2022");
		ScreenShot("Card details entered successfully", "INFO", test);
	}
	
	public void fillOnlineBookingDetails() throws InterruptedException
	{
		
		BookHotelPage bookHotelPage = new BookHotelPage();
		
		String TravellerName = bookHotelPage.TRAVELLER.getText();
		reportingUtils.setTravellerName(TravellerName);
		
		if(bookHotelPage.NOTIFICATION_EMAIL_INPUT.isDisplayed())
		{
			bookHotelPage.NOTIFICATION_EMAIL_INPUT.clear();
		}
		else
		{
			bookHotelPage.CONTACT_INFORMATION.click();
			bookHotelPage.NOTIFICATION_EMAIL_INPUT.clear();
		}
		
	
		Thread.sleep(1000);
		
		bookHotelPage.ADD_REMARKS.click();
		
		Assert.assertTrue((driver().getPageSource().contains(("CANCELLABLE BY")))||
						  (driver().getPageSource().contains(("FREE cancellation")))||
						  (driver().getPageSource().contains(("This booking can be cancelled")))||
						  (driver().getPageSource().contains(("The cancellation time stated below")))
						  ,"Hotel is non-cancellable");
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Enter the SI Remarks" + "<b>");
		bookHotelPage.INTERNAL_REMARKS.sendKeys("This is test booking, please cancel");
		ScreenShot("Booking details input successful", "INFO", test);
		
	}
	
	public void clickOnBookButton() throws InterruptedException
	{
		BookHotelPage bookHotelPage = new BookHotelPage();
		bookHotelPage.PROCEED_BUTTON.click();
		Thread.sleep(2000);
	}
	
	public void fulFillNowDetailsAndClickBookButton() throws InterruptedException
	{
		
		BookHotelPage bookHotelPage = new BookHotelPage();
		bookHotelPage.FULFILL_NOW.click();
		Thread.sleep(2000);
		Select selectRoomDescription = new Select(bookHotelPage.ROOM_DESCRIPTION_FULLFILL_NOW);
		selectRoomDescription.selectByVisibleText(PowerModeConstants.ROOM_DESCRIPTION_SINGLE_ROOM);
		Thread.sleep(2000);
		Select selectRateDescription = new Select(bookHotelPage.RATE_DESCRIPTION_FULLFILL_NOW);
		selectRateDescription.selectByVisibleText(PowerModeConstants.RATE_DESCRIPTION);
		
		bookHotelPage.RATE_PER_DAY_FULLFILL_NOW.sendKeys("10");
		
		Select selectCurrency = new Select(bookHotelPage.RATE_PER_DAY_CURRENCY_FULLFILL_NOW);
		selectCurrency.selectByVisibleText(PowerModeConstants.RATE_PER_CURRENCY);
		
		bookHotelPage.CONFIRMATION_NUMBER_FULLFILL_NOW.sendKeys(PowerModeConstants.CONFIRMATION_NUMBER);
		bookHotelPage.NOTIFICATION_EMAIL_FULLFILL_NOW.clear();
		
		ScreenShot("Confirmation number entered successfully", "INFO", test);
		
		Select selectPaymentMode =new Select(bookHotelPage.PAYMENT_MODE_FULLFILL_NOW);
		selectPaymentMode.selectByIndex(7);
		
		
//		selectPaymentMode.selectByVisibleText(PowerModeConstants.PAYMENT_MODE);
		Thread.sleep(2000);
		Select selectPaymentType =new Select(bookHotelPage.PAYMENT_TYPE_FULLFILL_NOW);
		selectPaymentType.selectByIndex(5);
//		selectPaymentType.selectByVisibleText(PowerModeConstants.PAYMENT_TYPE);
		
		bookHotelPage.CARD_NUMBER_FULLFILL_NOW.sendKeys(PowerModeConstants.PAYMENT_CARD_VISA);
		bookHotelPage.EXPIRY_DATE_FULLFILL_NOW.sendKeys(PowerModeConstants.EXPIRY_DATE);
		bookHotelPage.NAME_ON_CARD_FULLFILL_NOW.sendKeys(PowerModeConstants.NAME_ON_CARD);
		
		ScreenShot("New card added successfully", "INFO", test);
		
		Select selectReasonOnRequeest =new Select(bookHotelPage.ON_REQUEST_REASON_FULLFILL_NOW);
		selectReasonOnRequeest.selectByIndex(8);
//		selectReasonOnRequeest.selectByVisibleText(PowerModeConstants.REASON_ONREQUEST);
	
	}
	
	public void proceedButtonFulFillNow()
	{
		
		BookHotelPage bookHotelPage = new BookHotelPage();
		bookHotelPage.ON_REQUEST_PROCEED_FULLFILL_NOW.click();
		
	}
	
	
	public void verifyBookingConfirmation() throws InterruptedException, ParseException
	{
		BookingConfirmationPage bookingConfirmationPage = new BookingConfirmationPage();
		wait.until(ExpectedConditions.visibilityOf(bookingConfirmationPage.BOOKING_CONFIRMATION_NUMBER));
		ScreenShot("Booking Confirmation Page", "INFO", test);
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Verify the booking cofirmation" + "<b>");
		Assert.assertTrue(bookingConfirmationPage.BOOKING_CONFIRMATION_NUMBER.isDisplayed(),"Booking confirmation failed");
		if(BookingReferenceNumber!="")
			BookingReferenceNumber1=BookingReferenceNumber;
		if(driver().findElementsById("spnPMBookingReference").size()!=0)
//			string s= bookingConfirmationPage.BOOKING_REFERENCE_NUMBER.getAttribute("id")
		BookingReferenceNumber= bookingConfirmationPage.BOOKING_REFERENCE_NUMBER.getText();
		else
		BookingReferenceNumber= bookingConfirmationPage.BOOKING_REFERENCE_NUMBER_ONREQUEST.getText();
//		if(bookingConfirmationPage.BOOKING_CONFIRMATION_NUMBER.isDisplayed())
		ConfirmationNumber = bookingConfirmationPage.BOOKING_CONFIRMATION_NUMBER.getText();
		reportingUtils.setConfirmationNumber(ConfirmationNumber);
		reportingUtils.setBookingReferenceNumber(BookingReferenceNumber);
		ScreenShot("Booking Confirmation successful", "PASS", test);
		System.out.println(BookingReferenceNumber);
		reportingUtils.setOutPutData("Reference# "+BookingReferenceNumber);
		reportingUtils.setOutPutData("Confirmation# "+ConfirmationNumber);
//		this.BookingReferenceNumber=BookingReferenceNumber;
//		this.ConfirmationNumber=ConfirmationNumber;
//		String TravelerName=bookingConfirmationPage.TRAVELLER.getText();
////		Assert.assertTrue(TravelerName.contains(reportingUtils.getTravellerName()), "Traveler Name Not Matching");
//		String CheckInDate=bookingConfirmationPage.CHECK_IN_DATE.getText();
//		String CheckOutDate=bookingConfirmationPage.CHECK_OUT_DATE.getText();
//		
//		System.out.println("CheckInDate "+CheckInDate);
//		System.out.println("getCheckInDate "+reportingUtils.getCheckInDate());
//		
//		String dte=reportingUtils.getCheckInDate();
//		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MMMM-yyyy");
//		Date date1 = dt1.parse(CheckInDate);
//		Date date2 = dt1.parse(CheckOutDate);
//		SimpleDateFormat dt2 = new SimpleDateFormat("dd-MMM-yyyy");
//		String newCheckInDate=dt2.format(date1).toString();
//		String newCheckOutDate=dt2.format(date2).toString();
//		
//		Assert.assertTrue(newCheckInDate.contains(reportingUtils.getCheckInDate()));
		
//		Assert.assertTrue(newCheckOutDate.contains(reportingUtils.getCheckOutDate()));
		
	}
	
	public void verifyBookingConfirmationOnRequest() throws InterruptedException, ParseException
	{
		BookingConfirmationPage bookingConfirmationPage = new BookingConfirmationPage();
		
		wait.until(ExpectedConditions.visibilityOf(bookingConfirmationPage.BOOKING_CONFIRMATION_SUCCESS_TEST_BOOKINGS_ONREQUEST));
		JavascriptExecutor js = (JavascriptExecutor) driver();
		js.executeScript("window.scrollBy(0,-500)");
		ScreenShot("Booking Confirmation Page", "INFO", test);
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Verify the booking cofirmation" + "<b>");
		Assert.assertTrue(bookingConfirmationPage.BOOKING_CONFIRMATION_SUCCESS_TEST_BOOKINGS_ONREQUEST.isDisplayed(),"Booking confirmation failed");
		BookingReferenceNumber= bookingConfirmationPage.BOOKING_REFERENCE_NUMBER_ONREQUEST.getText();
//		if(bookingConfirmationPage.BOOKING_CONFIRMATION_NUMBER.isDisplayed())
//		ConfirmationNumber = bookingConfirmationPage.BOOKING_CONFIRMATION_NUMBER.getText();
		reportingUtils.setConfirmationNumber(ConfirmationNumber);
		reportingUtils.setBookingReferenceNumber(BookingReferenceNumber);
		ScreenShot("Booking Confirmation successful", "PASS", test);
		System.out.println(BookingReferenceNumber);
		reportingUtils.setOutPutData("Reference# "+BookingReferenceNumber);
		reportingUtils.setOutPutData("Confirmation# "+"111");
//		this.BookingReferenceNumber=BookingReferenceNumber;
//		this.ConfirmationNumber=ConfirmationNumber;
//		String TravelerName=bookingConfirmationPage.TRAVELLER.getText();
////		Assert.assertTrue(TravelerName.contains(reportingUtils.getTravellerName()), "Traveler Name Not Matching");
//		String CheckInDate=bookingConfirmationPage.CHECK_IN_DATE.getText();
//		String CheckOutDate=bookingConfirmationPage.CHECK_OUT_DATE.getText();
//		
//		System.out.println("CheckInDate "+CheckInDate);
//		System.out.println("getCheckInDate "+reportingUtils.getCheckInDate());
//		
//		String dte=reportingUtils.getCheckInDate();
//		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MMMM-yyyy");
//		Date date1 = dt1.parse(CheckInDate);
//		Date date2 = dt1.parse(CheckOutDate);
//		SimpleDateFormat dt2 = new SimpleDateFormat("dd-MMM-yyyy");
//		String newCheckInDate=dt2.format(date1).toString();
//		String newCheckOutDate=dt2.format(date2).toString();
//		
//		Assert.assertTrue(newCheckInDate.contains(reportingUtils.getCheckInDate()));
		
//		Assert.assertTrue(newCheckOutDate.contains(reportingUtils.getCheckOutDate()));
		
	}
	
	
	public void segmentVerification() throws InterruptedException
	{
		HomePage homePage = new HomePage();
		PreviousBookingsPage previousBookingsPage = new PreviousBookingsPage();
		homePage.MORE.click();
		homePage.PREVIOUS_BOOKINGS.click();
		wait.until(ExpectedConditions.visibilityOf(previousBookingsPage.BOOKING_REFERENCE));
		previousBookingsPage.BOOKING_REFERENCE.sendKeys(BookingReferenceNumber);
		previousBookingsPage.LIST_BOOKINGS.click();
		Thread.sleep(2000);
		previousBookingsPage.BOOKING_REFERENCE_LIST.click();
		Thread.sleep(2000);
		previousBookingsPage.PNR_LINK.click();
		Thread.sleep(5000);
		previousBookingsPage.PNR_PAGE_CLOSE.click();
		Thread.sleep(5000);
		previousBookingsPage.PNR_LINK.click();
		Thread.sleep(5000);
		ScreenShot("PNR Segment Verification", "INFO", test);
		String PnrText= previousBookingsPage.PNR_TEXT.getText();
		wait.until(ExpectedConditions.textToBePresentInElement(previousBookingsPage.PNR_TEXT, BookingReferenceNumber));
		segmentRead(PnrText);
		Thread.sleep(5000);
		
//		JavascriptExecutor js = (JavascriptExecutor) driver();
//	    js.executeScript("arguments[0].scrollIntoView(true);",previousBookingsPage.PNR_PAGE_CLOSE);
		Actions action = new Actions(driver());
		action.moveToElement(previousBookingsPage.PNR_PAGE_CLOSE).click().perform();
//	    previousBookingsPage.PNR_PAGE_CLOSE.click();
		Thread.sleep(5000);
		
	}
	
	public void repeatBooking(String PNR) throws InterruptedException
	{
		ChooseHotelPage chooseHotelPage = new ChooseHotelPage();
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		HomePage homePage = new HomePage();
		
		PreviousBookingsPage previousBookingsPage = new PreviousBookingsPage();
		BookingSummaryPage bookingSummaryPage = new BookingSummaryPage();
		BookHotelPage bookHotelPage = new BookHotelPage();
		
		homePage.MORE.click();
		homePage.PREVIOUS_BOOKINGS.click();
		wait.until(ExpectedConditions.visibilityOf(previousBookingsPage.BOOKING_REFERENCE));
		previousBookingsPage.BOOKING_REFERENCE.sendKeys(BookingReferenceNumber);
		previousBookingsPage.LIST_BOOKINGS.click();
		Thread.sleep(2000);
		previousBookingsPage.BOOKING_REFERENCE_LIST.click();
		Thread.sleep(5000);
		previousBookingsPage.VIEW_EDIT_BOOKING.click();
		ScreenShot("Click on repeat booking button", "INFO", test);
		bookingSummaryPage.REPEAT_BOOKING.click();
		Thread.sleep(1000);
		ScreenShot("Repeat booking landing page", "INFO", test);
		if(driver().findElements(chooseHotelPage.ROOMS_RATES_BY()).size()>=1)
		{
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver();
			js.executeScript("window.scrollBy(0,-500)");
			searchHotelStep1Page.CHECKIN_CHECKOUT_DATE_HEADER.click();
			Thread.sleep(2000);
			searchHotelStep1Page.CHECKIN_DATE_HEADER_NEXT_DAY.click();
			Thread.sleep(2000);
			searchHotelStep1Page.CHECKIN_CHECKOUT_DATE_HEADER_NEXT_DAY.click();
			Thread.sleep(5000);
			searchHotelStep1Page.CHECKIN_CHECKOUT_DATE_HEADER_PROCEED.click();
			Thread.sleep(5000);
			chooseHotelPage.SELECT_RATE_FIRST_ONLINE_CANCELLABLE.click();
		}
		

//		else
//		if(driver().findElements(bookHotelPage.EXTRACT_TRAVELLER_DETAILS_BY()).size()>=1)
		{
			Thread.sleep(2000);
			bookHotelPage.EXTRACT_TRAVELLER_INPUT.sendKeys(PNR);
			bookHotelPage.EXTRACT_TRAVELLER_DETAILS.click();
			Thread.sleep(5000);
			bookHotelPage.NOTIFICATION_EMAIL_FULLFILL_NOW.clear();
		}
		
		
		
	}
	
	public void segmentRead(String PnrText)
    {
    	  String pageSource = PnrText;
    	  String search= "H‡HHB"
    	  		+ "(.*)"+BookingReferenceNumber;
    	  
    	  // to Get Segment Line
    	  String[] arr = PnrText.split("\\n");
    	  String HHB="";
    	  
    	  
    	  for(int i=0;i<arr.length;i++)
    	  {
    		  if(arr[i].contains(BookingReferenceNumber))
    		  {
    			  HHB=arr[i]+arr[i+1];
    			  break;
    		  }
    	  }
    	  String hhbline=HHB.replaceFirst(".+\\H‡HHB", "H‡HHB");
    	  System.out.println("new hhb line "+hhbline);
    	  reportingUtils.setOutPutData(hhbline);
    	  PrintInfo(hhbline);
//    	  Assert.assertTrue(!hhbline.isEmpty());
    	  String search1= "\\*(.*)\\*";
    	  Pattern pattern1 = Pattern.compile(search1);
    	  Matcher matcher1 = pattern1.matcher(HHB);
    	  String placeholder1="";
    	  String placeholder2="";
    	  String SegmentLine="";
    	  if (matcher1.find()) 
    	  {
    		  placeholder2 = matcher1.group(1);
    		  placeholder1="\\*"+placeholder2+"\\*";
    		  System.out.println("Hotel Key "+placeholder1);
    		  
    	  }
    	  
    	  int countb=0;
    	  int counta=0;
    	  
    	  int k=0;
    	  int m=0;
    	
    	  int countplace=0;
    	  
//    	  for(int l=0;l<arr.length;l++)
//    	  {
//    		  if(arr[l].contains(ConfirmationNumber))
    		  
    	  for(int i=0;i<arr.length;i++)
    	  {
    		  if(arr[i].contains(ConfirmationNumber))
    		  {
    			  countplace=2;
    			 break;
    		  }
    		  
    		  else if(arr[i].contains(placeholder1))
    		  {
    			  countplace++;  
    		  }
    	  }
    	  
    	  System.out.println("countplace "+countplace);
    	  if(countplace>1)
    	  
    	  {
    			  
    	    	  for(int i=0;i<arr.length;i++)
    	    	  {
    	    		  
    	    		  if(arr[i].contains(ConfirmationNumber))
    	    		  {
    	    			  k=i;
    	    			  m=i;
    	    			  while((!arr[k].contains(" HHT "))&&(!arr[k].contains(" HHL "))&&(!arr[k].contains(" HTL ")))
    	    			  {
    	    				  countb++;
    	    				  System.out.println(ConfirmationNumber);
    	    				  System.out.println(arr[k]);
    	    				  k--;
    	    			
    	    			  }
    	    			  k=i;
    	    			   while((!arr[k].contains(" HHT "))&&(!arr[k].contains(" HHL "))&&(!arr[k].contains(" HTL ")))
    	        			  {
    	        				  counta++;
    	        				  System.out.println(arr[k]);
    	        				  k++;
    	        			  }  
    	    			 
    	    		  break;
    	    		  }
    	    		  
    	    	  }
    	    	  
    	    	  if(counta!=0){
    	    		  int start=m-countb;
    	        	  int end=m+counta-1;
    	        	  
    	        	  for(int i=start;i<=end;i++)
    	        	  {

    	        		  SegmentLine=SegmentLine+" "+arr[i];
    	        		  
    	        	  }
//    	        	  String SegmentLine1=SegmentLine.replaceFirst(".+\\HHT", "HHT").replaceFirst(".+\\HHL", "HHL");
    	        	  System.out.println(" Segment Line "+SegmentLine);
    	        	  reportingUtils.setOutPutData(SegmentLine);
    	        	  PrintInfo(SegmentLine);
    	        	  Assert.assertTrue(!SegmentLine.isEmpty());
    	        	  System.out.println("Output Data "+reportingUtils.getOutPutData());
    	    	  }
    	    }
    	  }
//      }
	
	public void segmentReadOld(String PnrText)
    {
    	  String pageSource = PnrText;
    	  String search= "H‡HHB"
    	  		+ "(.*)"+BookingReferenceNumber;
    	  
    	  // to Get Segment Line
    	  String[] arr = PnrText.split("\\n");
    	  String HHB="";
    	  
    	  
    	  for(int i=0;i<arr.length;i++)
    	  {
    		  if(arr[i].contains(BookingReferenceNumber))
    		  {
    			  HHB=arr[i]+arr[i+1];
    			  break;
    		  }
    	  }

    	  System.out.println("HHB Line "+HHB);
    	  String hhbline=HHB.replaceFirst(".+\\H‡HHB", "H‡HHB");
    	  System.out.println("new hhb line "+hhbline);
    	  reportingUtils.setOutPutData(hhbline);
    	  PrintInfo(hhbline);
//    	  Assert.assertTrue(!hhbline.isEmpty());
    	  String search1= "\\*(.*)\\*";
    	  Pattern pattern1 = Pattern.compile(search1);
    	  Matcher matcher1 = pattern1.matcher(HHB);
    	  String placeholder1="";
    	  String SegmentLine="";
    	  if (matcher1.find()) 
    	  {
    		  placeholder1 = matcher1.group(1);
    		  System.out.println(placeholder1);
    		  
    	  }
    	  
    	  int countb=0;
    	  int counta=0;
    	  
    	  int k=0;
    	  int m=0;
    	
    	  for(int l=0;l<arr.length;l++)
    	  {
    		  if(arr[l].contains(ConfirmationNumber))
    		  {
    			  
    	    	  for(int i=0;i<arr.length;i++)
    	    	  {
    	    		  
    	    		  if(arr[i].contains(placeholder1))
    	    		  {
    	    			  k=i;
    	    			  m=i;
    	    			  while((!arr[k].contains("  HHT "))&&(!arr[k].contains("  HHL ")))
    	    			  {
    	    				  countb++;
    	    				  
    	    				  k--;
    	    			  }
    	    			  k=i;
    	    			   while(!arr[k].contains(reportingUtils.getConfirmationNumber()))
    	        			  {
    	        				  counta++;
    	        				  k++;
    	        			  }  
    	    			 
    	    			  
    	    			  break;
    	    		  }
    	    		  
    	    	  }
    	    	  
    	    	  if(counta!=0){
    	    		  int start=m-countb;
    	        	  int end=m+counta;
    	        	  
    	        	  for(int i=start;i<=end;i++)
    	        	  {

    	        		  SegmentLine=SegmentLine+" "+arr[i];
    	        		  
    	        	  }
//    	        	  String SegmentLine1=SegmentLine.replaceFirst(".+\\HHT", "HHT").replaceFirst(".+\\HHL", "HHL");
    	        	  reportingUtils.setOutPutData(SegmentLine);
    	        	  PrintInfo(SegmentLine);
    	        	  Assert.assertTrue(!SegmentLine.isEmpty());
    	        	  System.out.println("Output Data "+reportingUtils.getOutPutData());
    	    	  }
    	    }
    	  }
      }
	
	
	public void cancelBookingPH(String BookingReferenceNumber, ExtentTest test) throws InterruptedException
	{
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Cancel the Booking" + "<b>");
		driver().get("https://dev.hotelhub.net/CWT/v1570/book/default.aspx");
		driver().findElement(By.id("txtUserId")).sendKeys("TCT10290");
		driver().findElement(By.id("txtPwd")).sendKeys("welcome@1");
		driver().findElement(By.id("lnkLogin")).click();
		driver().findElement(By.id("txtCnclOrMdfy")).sendKeys(BookingReferenceNumber);
		driver().findElement(By.xpath("//*[@id='aetmIFrame']/div/div[5]/div[1]/div[2]/ul/li[3]/button")).click();
		
//		driver().findElement(By.id("lnkmore")).click();
//		driver().findElement(By.id("PREVBKNGHSTRY")).click();
//		driver().findElement(By.id("txtRefConfNo")).sendKeys(BookingReferenceNumber);
//		driver().findElement(By.xpath("//span/em[contains(text(),'List bookings')]")).click();
//		driver().findElement(By.xpath("//div[contains(@class,'table_row_sm1')]/p[contains(text(),'Confirmed')]")).click();
//		driver().findElement(By.xpath("//span[contains(text(),'View or edit booking details')]")).click();
		driver().findElement(By.id("btnDivCancel")).click();
		driver().findElement(By.id("btncontinue")).click();
		Thread.sleep(3000);
		ScreenShot("Booking Cancelled sucessfully", "PASS", test);
		driver().findElement(By.id("hhheader_lnkSignout")).click();
	
	}
	

	
	public void cancelBooking() throws InterruptedException
	{
		HomePage homePage = new HomePage();
		BookingConfirmationPage bookingConfirmationPage = new BookingConfirmationPage();
		PreviousBookingsPage previousBookingsPage = new PreviousBookingsPage();
		Property props = new Property();
		String Url=props.getProperty("PowerMode");
		String UserName=props.getProperty("UserName");
		String PassWord=props.getProperty("PassWord");
//		driver().get(Url);
		LoginPage loginPage = new LoginPage();
		
//		loginPage.userName.sendKeys(UserName);
//		loginPage.passWord.sendKeys(PassWord);
//		loginPage.signInButton.click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver();
		js.executeScript("window.scrollBy(0,-500)");
		wait.until(ExpectedConditions.visibilityOf(homePage.MORE));
		homePage.MORE.click();
		homePage.PREVIOUS_BOOKINGS.click();
		wait.until(ExpectedConditions.visibilityOf(previousBookingsPage.BOOKING_REFERENCE));
		previousBookingsPage.BOOKING_REFERENCE.sendKeys(BookingReferenceNumber);
		previousBookingsPage.LIST_BOOKINGS.click();
		Thread.sleep(1000);
		Actions action = new Actions(driver());
		action.moveToElement(previousBookingsPage.BOOKING_REFERENCE_LIST).build().perform();
		Thread.sleep(1000);
		previousBookingsPage.CANCEL_ICON.click();
		wait.until(ExpectedConditions.visibilityOf(previousBookingsPage.CANCEL_YES));
		previousBookingsPage.CANCEL_YES.click();
		wait.until(ExpectedConditions.visibilityOf(previousBookingsPage.CANCELLATION_REFRENCE_NUMBER));
		String CancellationRefrenceNumber=previousBookingsPage.CANCELLATION_REFRENCE_NUMBER.getText();
		ScreenShot("Booking cancelled successfully", "PASS", test);
		System.out.println("CancellationRefrenceNumber "+CancellationRefrenceNumber);
		reportingUtils.setOutPutData("Cancellation Reference# "+CancellationRefrenceNumber);
		previousBookingsPage.CANCELLATION_CLOSE.click();
		if(BookingReferenceNumber1!="")
		BookingReferenceNumber=BookingReferenceNumber1;
		Thread.sleep(5000);
		
	}
	
	
	public void cancelBookingOnRequest() throws InterruptedException
	{
		HomePage homePage = new HomePage();
		BookingSummaryPage bookingSummaryPage = new BookingSummaryPage();
		BookingConfirmationPage bookingConfirmationPage = new BookingConfirmationPage();
		PreviousBookingsPage previousBookingsPage = new PreviousBookingsPage();
		
		wait.until(ExpectedConditions.visibilityOf(homePage.MORE));
		homePage.MORE.click();
		homePage.PREVIOUS_BOOKINGS.click();
		wait.until(ExpectedConditions.visibilityOf(previousBookingsPage.BOOKING_REFERENCE));
		previousBookingsPage.BOOKING_REFERENCE.sendKeys(BookingReferenceNumber);
		previousBookingsPage.LIST_BOOKINGS.click();
		previousBookingsPage.BOOKING_REFERENCE_LIST.click();
		Thread.sleep(1000);
		previousBookingsPage.VIEW_EDIT_BOOKING.click();
		Thread.sleep(1000);
		bookingSummaryPage.CANCEL_BOOKING.click();
		Thread.sleep(1000);
		bookingSummaryPage.CANCELLATION_REFERENCE_NUMBER.sendKeys("12345");
		ScreenShot("Cancellation reference number", "INFO", test);
		Thread.sleep(1000);
		bookingSummaryPage.CANCELLATION_PROCEED_BUTTON.click();
		Thread.sleep(1000);
		String status=bookingSummaryPage.STATUS_CANCEL.getText();
		ScreenShot("Booking Cancelled successfully", "PASS", test);
		
		
	}
	
	
	public void abondonBooking(String bref) throws InterruptedException
	{
		HomePage homePage = new HomePage();
		PreviousBookingsPage previousBookingsPage = new PreviousBookingsPage();
		BookingSummaryPage bookingSummaryPage = new BookingSummaryPage();
		JavascriptExecutor js = (JavascriptExecutor) driver();
		js.executeScript("window.scrollBy(0,-500)");
		wait.until(ExpectedConditions.visibilityOf(homePage.MORE));
		homePage.MORE.click();
		homePage.PREVIOUS_BOOKINGS.click();
		wait.until(ExpectedConditions.visibilityOf(previousBookingsPage.BOOKING_REFERENCE));
		previousBookingsPage.BOOKING_REFERENCE.sendKeys(bref);
		previousBookingsPage.LIST_BOOKINGS.click();
		Thread.sleep(1000);
//		Actions action = new Actions(driver());
//		action.moveToElement(previousBookingsPage.BOOKING_REFERENCE_LIST).build().perform();
		previousBookingsPage.BOOKING_REFERENCE_LIST.click();
		Thread.sleep(1000);
		previousBookingsPage.VIEW_EDIT_BOOKING.click();
		Thread.sleep(1000);
		bookingSummaryPage.ABONDON_BOOKING.click();
		bookingSummaryPage.ABONDON_BOOKING_REMARKS.sendKeys("Test Booking");
		bookingSummaryPage.CANCELLATION_PROCEED_BUTTON.click();
		
		
	}

	public void logOut(ExtentTest test) throws InterruptedException
	{
		HomePage homePage= new HomePage();
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Logout from the application" + "<b>");
		JavascriptExecutor js = (JavascriptExecutor) driver();
		js.executeScript("window.scrollBy(0,-500)");
		Thread.sleep(10000);
		homePage.SETTINGS_ICON.click();
		homePage.LOG_OUT.click();
		ScreenShot("Logged out from the application successfully", "INFO", test);
		
	}
	
	
	
}
