package com.hotelhub.powermode.helpers;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.hotelhub.powermode.pages.BookHotelPage;
import com.hotelhub.powermode.pages.BookingConfirmationPage;
import com.hotelhub.powermode.pages.ChooseHotelPage;
import com.hotelhub.powermode.pages.HomePage;
import com.hotelhub.powermode.pages.PreviousBookingsPage;
import com.hotelhub.powermode.pages.SearchHotelStep1Page;
import com.hotelhub.powermode.pages.TravellersDetailsStep1Page;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.Base;
import base.PowerModeConstants;


public class RegressionHelper extends Base {

	private Logger _logger = LogManager.getLogger(RegressionHelper.class.getName());
	
	static int count=0;
	public static String Hotel;
	WebDriverWait wait=new WebDriverWait(driver(), 50);
	JavascriptExecutor js = (JavascriptExecutor) driver();
	public static String BookingType;
	public static String BookingFlow;
	public static String searchBy;
	public static String filterCriteria;
	public static String filterBy;
	public static String input;
	public void clickOnBookAHotel()
	{
		_logger.info("Click on Book a Hotel button");
		HomePage homePage = new HomePage();
		homePage.BOOK_HOTEL.click();
	}
	
	public void selectCustomerOrPNROrReferenceNumber(String PNR, String GDS) throws Throwable
	{
		
		TravellersDetailsStep1Page travellersDetailsStep1Page = new TravellersDetailsStep1Page();

		travellersDetailsStep1Page.TRAVELLER_DETAILS_INPUT.sendKeys(PNR);
		if(GDS.equals("AMADEUS"))
		{
			Select select = new Select(travellersDetailsStep1Page.SELECT_GDS);
			select.selectByVisibleText("Amadeus");
		}
		if(GDS.equals("APOLLO"))
		{
			Select select = new Select(travellersDetailsStep1Page.SELECT_GDS);
			select.selectByVisibleText("Apollo");
		}	
		if(GDS.equals("GALILEO"))
		{
			Select select = new Select(travellersDetailsStep1Page.SELECT_GDS);
			select.selectByVisibleText("Galileo");
		}
		Thread.sleep(2000);	
		travellersDetailsStep1Page.FIRST_TRAVELLER.click();

		ScreenShot("PNR Read successful ","INFO",test);
		
		try
		{
			Thread.sleep(2000);
			travellersDetailsStep1Page.PROCEED_BUTTON.click();
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	public void verifyPNRGDSMismatchError(String PNR) throws InterruptedException
	{
		
		TravellersDetailsStep1Page travellersDetailsStep1Page = new TravellersDetailsStep1Page();
		travellersDetailsStep1Page.TRAVELLER_DETAILS_INPUT.sendKeys(PNR);
		Assert.assertTrue(travellersDetailsStep1Page.TRAVELLER_DETAILS_PNR_GDS_ERROR.getText().contains(PowerModeConstants.GDS_PNR_MISMATCH_ERROR));
		ScreenShot("PNR GDS Error display", "PASS", test);
		
	}
	
	public void verifyPreviouslySelectedPNRorCustomer() throws InterruptedException
	{
		
		TravellersDetailsStep1Page travellersDetailsStep1Page = new TravellersDetailsStep1Page();
		Thread.sleep(2000);
		Assert.assertTrue(travellersDetailsStep1Page.PREVIOUS_SEARCH.isDisplayed());
		ScreenShot("Previously searched PNR or Customers are displaying", "PASS", test);
		
	}
	
	
	
	
	
	public void selectDestinationType(String destinationType) throws InterruptedException
	{
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		searchHotelStep1Page.DESTINATION_TYPE.click();
		Thread.sleep(2000);
		if(destinationType.equals("HOTEL"))
		searchHotelStep1Page.DESTINATION_TYPE_HOTEL.click();	
		else if(destinationType.equals("AIRPORT"))
		searchHotelStep1Page.DESTINATION_TYPE_AIRPORT.click();	
		else if(destinationType.equals("GOOGLE"))
		searchHotelStep1Page.DESTINATION_TYPE_GOOGLE.click();
		else if(destinationType.equals("GOOGLE_AIRPORT"))
		{
			searchHotelStep1Page.DESTINATION_TYPE_GOOGLE.click();
			searchHotelStep1Page.DESTINATION_TYPE_AIRPORT.click();
		}
		else if(destinationType.equals("GOOGLE_HOTEL"))
		{
			searchHotelStep1Page.DESTINATION_TYPE_GOOGLE.click();
			searchHotelStep1Page.DESTINATION_TYPE_HOTEL.click();	
		}
		else if(destinationType.equals("AIRPORT_HOTEL"))
		{
			searchHotelStep1Page.DESTINATION_TYPE_AIRPORT.click();
			Thread.sleep(2000);
			searchHotelStep1Page.DESTINATION_TYPE_HOTEL.click();	
		}
	}
	
	public void searchLocation(String searchKey) throws InterruptedException
	{
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		this.Hotel=searchKey;
//		searchHotelStep1Page.DESTINATION_SEARCH_INPUT.click();
		searchHotelStep1Page.DESTINATION_SEARCH_INPUT.sendKeys(searchKey);
		reportingUtils.setInputData(searchKey);
		WebDriverWait wait=new WebDriverWait(driver(), 120);
		wait.until(ExpectedConditions.invisibilityOf(SearchHotelStep1Page.DESTINATION_SEARCH_SPINNER));
		
	}
	
	public void verifyDestinationType(String destinationType) throws InterruptedException
	{
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		if(destinationType.equals("HOTEL"))
		{
			
			Assert.assertTrue(isElementPresent(searchHotelStep1Page.FIRST_HOTEL_RESULT_BY()));
			Assert.assertTrue(!isElementPresent(searchHotelStep1Page.FIRST_GOOGLE_RESULT_BY()));
			Assert.assertTrue(!isElementPresent(searchHotelStep1Page.FIRST_IATA_RESULT_BY()));
			ScreenShot("Verification of Hotel Destination Type is sucessful", "PASS", test);
		}
		if(destinationType.equals("AIRPORT"))
		{
			Assert.assertTrue(isElementPresent(searchHotelStep1Page.FIRST_IATA_RESULT_BY()));
			Assert.assertTrue(!isElementPresent(searchHotelStep1Page.FIRST_GOOGLE_RESULT_BY()));
			Assert.assertTrue(!isElementPresent(searchHotelStep1Page.FIRST_HOTEL_RESULT_BY()));
			ScreenShot("Verification of Airport Destination Type is sucessful", "PASS", test);
		}
		if(destinationType.equals("GOOGLE"))
		{
			Assert.assertTrue(isElementPresent(searchHotelStep1Page.FIRST_GOOGLE_RESULT_BY()));
			Assert.assertTrue(!isElementPresent(searchHotelStep1Page.FIRST_IATA_RESULT_BY()));
			Assert.assertTrue(!isElementPresent(searchHotelStep1Page.FIRST_HOTEL_RESULT_BY()));
			ScreenShot("Verification of Google Destination Type is sucessful", "PASS", test);
		}
		if(destinationType.equals("ALL"))
		{
			Assert.assertTrue(isElementPresent(searchHotelStep1Page.FIRST_GOOGLE_RESULT_BY()));
			Assert.assertTrue(isElementPresent(searchHotelStep1Page.FIRST_IATA_RESULT_BY()));
			Assert.assertTrue(isElementPresent(searchHotelStep1Page.FIRST_HOTEL_RESULT_BY()));
			ScreenShot("Verification of All Destination Type is sucessful", "PASS", test);
		}
		
		if(destinationType.equals("GOOGLE_AIRPORT"))
		{
			Assert.assertTrue(isElementPresent(searchHotelStep1Page.FIRST_GOOGLE_RESULT_BY()));
			Assert.assertTrue(isElementPresent(searchHotelStep1Page.FIRST_IATA_RESULT_BY()));
			Assert.assertTrue(!isElementPresent(searchHotelStep1Page.FIRST_HOTEL_RESULT_BY()));
			ScreenShot("Verification of Google Destination Type is sucessful", "PASS", test);
		}
		
		if(destinationType.equals("GOOGLE_HOTEL"))
		{
			Assert.assertTrue(isElementPresent(searchHotelStep1Page.FIRST_GOOGLE_RESULT_BY()));
			Assert.assertTrue(!isElementPresent(searchHotelStep1Page.FIRST_IATA_RESULT_BY()));
			Assert.assertTrue(isElementPresent(searchHotelStep1Page.FIRST_HOTEL_RESULT_BY()));
			ScreenShot("Verification of Google Destination Type is sucessful", "PASS", test);
		}
		
		if(destinationType.equals("AIRPORT_HOTEL"))
		{
			Assert.assertTrue(!isElementPresent(searchHotelStep1Page.FIRST_GOOGLE_RESULT_BY()));
			Assert.assertTrue(isElementPresent(searchHotelStep1Page.FIRST_IATA_RESULT_BY()));
			Assert.assertTrue(isElementPresent(searchHotelStep1Page.FIRST_HOTEL_RESULT_BY()));
			ScreenShot("Verification of Google Destination Type is sucessful", "PASS", test);
		}
		
		
	}
	
	public boolean isElementPresent(By locatorKey) {
	    try {
	        driver().findElement(locatorKey);
	        return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}
	
	public void selectLocation(String LocationType) throws InterruptedException
	{
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		if(LocationType.isEmpty())
		{
			Thread.sleep(5000);
			searchHotelStep1Page.FIRST_RESULT.click();
		}
		else if(LocationType.contains("Google"))
		{	
			Thread.sleep(5000);
			searchHotelStep1Page.FIRST_GOOGLE_RESULT.click();
			ScreenShot("Selected GOOGLE result type from the search results ","INFO",test);
		}
		else if(LocationType.contains("IATA"))
		{
			Thread.sleep(5000);
			searchHotelStep1Page.FIRST_IATA_RESULT.click();
			ScreenShot("Selected IATA result type from the search results ","INFO",test);
		}
		else if(LocationType.contains("Hotel"))
		{	
			Thread.sleep(5000);
			searchHotelStep1Page.FIRST_HOTEL_RESULT.click();
			ScreenShot("Selected HOTEL result type from the search results ","INFO",test);
		}
	}
	

	
	public void selectCheckInCheckOutDate() throws InterruptedException
	{
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Select Check-In and Check-Out date" + "<b>");
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		int randomDay = (int)(Math.random()*(20-1))+1;
		System.out.println("randomDay "+randomDay);
		
		driver().manage().window().setSize(new Dimension(1920, 1080));

//		JavascriptExecutor js = (JavascriptExecutor) driver();
//		js.executeScript("window.scrollBy(0,-500)");
		Thread.sleep(2000);
		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_BUTTON.click();
		Thread.sleep(1000);
		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_STEP1.click();
//		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_STEP2.click();
//		JavascriptExecutor executor = (JavascriptExecutor)driver();
//		executor.executeScript("document.body.style.zoom = '0.95'");
//		Actions actions = new Actions(driver());
//		actions.moveToElement(searchHotelStep1Page.CHECK_IN_DATE_SELECTION_YEAR).perform();
		Thread.sleep(1000);
		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_MONTH.click();
//		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_DAY.click();
		String xpath="//tbody/*/td[(@class='day') and (text()="+randomDay+")]";
		driver().findElement(By.xpath(xpath)).click();
		int checkOutDate=randomDay+1;
		String xpath_checkout="//tbody/*/td[contains(@class,'day') and (text()="+checkOutDate+")]";
		driver().findElement(By.xpath(xpath_checkout)).click();
		ScreenShot("Check in - Check out date selection successful", "INFO", test);
		
		String CheckInCheckOut=searchHotelStep1Page.CHECKIN_CHECKOUT_DATE_HEADER.getText();
		
//		12-May-2019/13-May-2019
		
		System.out.println("Check In Check Out "+CheckInCheckOut);
		Thread.sleep(2000);
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
	
	public void selectCheckInCheckOutDateFixed() throws InterruptedException
	{
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Select Check-In and Check-Out date" + "<b>");
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_BUTTON.click();
		Thread.sleep(1000);
		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_STEP1.click();
		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_MONTH.click();
		searchHotelStep1Page.CHECK_IN_DATE_SELECTION_DAY.click();
		Thread.sleep(2000);
	}
	
	public void clickOnSearchHotelsButton() throws InterruptedException
	{
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		searchHotelStep1Page.SEARCH_HOTELS_BUTTON.click();
	}
	
	public void clickGetRates() throws InterruptedException
	{
		Thread.sleep(1000);
//		if(driver().findElementsByXPath("//button[contains(text(),'Get rates')]").size()>0)
		try
		{
				driver().findElement(By.xpath("//button[contains(text(),'Get rates')]")).click();	
		}catch(Exception e)
		{
			
		}
			
	}
	
	
	public void verifySearchResultsAreDisplayed(ExtentTest test) throws InterruptedException
	{
		
		ChooseHotelPage chooseHotelPage = new ChooseHotelPage();
		
		if(chooseHotelPage.hotelSearchResults.isDisplayed())
		{
			ScreenShot("Verification of Search Results is Successful ","PASS",test);
		}
		
		else
		{
			ScreenShot("Search Results are not displaying ","FAIL",test);
		}
		
		
	}
	
	
	public void verifyhotelInformation() throws InterruptedException
	{
		ChooseHotelPage chooseHotelPage = new ChooseHotelPage();
		Thread.sleep(2000);
		chooseHotelPage.VIEW_DETAILS.click();
		ScreenShot("Hotel Information ","INFO",test);
			String Address= (chooseHotelPage.HOTEL_INFORMATION_ADDRESS).getText();
			if (Address.isEmpty())
				ScreenShot("Verification of Hotel Address", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Address : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+Address+"</b>" );
		
			String Contact= chooseHotelPage.HOTEL_INFORMATION_CONTACT.getText();
			if (Contact.isEmpty())
				ScreenShot("Verification of Hotel Contact", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Contact : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+Contact+"</b>" );
			Thread.sleep(1000);
			String Description= chooseHotelPage.HOTEL_INFORMATION_DESCRIPTION.getText();
			if (Description.isEmpty())
				ScreenShot("Verification of Hotel Description", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Description : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+Description+"</b>" );
		
		js.executeScript("window.scrollBy(0,-350)");	
		chooseHotelPage.HOTEL_INFORMATION_DESCRIPTION_READ_MORE.click();
		Thread.sleep(2000);
		String GeneralDescription= chooseHotelPage.HOTEL_INFORMATION_DESCRIPTION_READ_MORE_GENERAL_DESCRIPTION.getText();
		ScreenShot("Hotel Description Read More", "INFO", test);
		chooseHotelPage.HOTEL_INFORMATION_DESCRIPTION_READ_MORE_CLOSE.click();
		
		Thread.sleep(2000);

		chooseHotelPage.HOTEL_INFORMATION_MAP_ZOOM_IN.click();
		Thread.sleep(2000);
		ScreenShot("Map Zoom In", "INFO", test);
		chooseHotelPage.HOTEL_INFORMATION_MAP_ZOOM_OUT.click();
		Thread.sleep(2000);
		ScreenShot("Map Zoom Out", "INFO", test);
		chooseHotelPage.HOTEL_INFORMATION_HOTEL_IMAGE_MORE_PHOTOS.click();
		Thread.sleep(2000);
		ScreenShot("More Photos", "INFO", test);
		chooseHotelPage.HOTEL_INFORMATION_HOTEL_IMAGE_MORE_PHOTOS_PREVIOUS.click();
		Thread.sleep(2000);

		chooseHotelPage.HOTEL_INFORMATION_HOTEL_IMAGE_PHOTOS_NEXT.click();
		Thread.sleep(2000);
		
		chooseHotelPage.HOTEL_INFORMATION_HOTEL_IMAGE_MORE_PHOTOS_CLOSE.click();
		JavascriptExecutor js = (JavascriptExecutor)(driver());
		js.executeScript("window.scrollBy(0,900)");
		ScreenShot("Hotel Information Room Details","INFO",test);
		
		if((chooseHotelPage.ROOM_DETAILS_CANCELLATION_POLICY).size()>0)
		{
			String CancellationPolicy= chooseHotelPage.ROOM_DETAILS_CANCELLATION_POLICY.get(0).getText();
			if (CancellationPolicy.isEmpty())
				ScreenShot("Verification of Hotel Cancellation Policy", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Cancellation Policy : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+CancellationPolicy+"</b>" );
		}
		

		if(chooseHotelPage.ROOM_DETAILS_AVERAGE_RATE_PER_NIGHT.size()>0)
		{
			String AverageRatePerNight= chooseHotelPage.ROOM_DETAILS_AVERAGE_RATE_PER_NIGHT.get(0).getText();
			if (AverageRatePerNight.isEmpty())
				ScreenShot("Verification of Hotel Information - Average Rate Per Night", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Average Rate Per Night : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+AverageRatePerNight+"</b>" );
		}
		
		if(chooseHotelPage.ROOM_DETAILS_TOTAL_AMOUNT.size()>0)
		{
			String TotalAmount= chooseHotelPage.ROOM_DETAILS_TOTAL_AMOUNT.get(0).getText();
			if (TotalAmount.isEmpty())
				ScreenShot("Verification of Hotel Information - Total Amount", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Total Amount : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+TotalAmount+"</b>" );
		}
		
		if(chooseHotelPage.ROOM_DETAILS_TAX.size()>0)
		{
			String Tax= chooseHotelPage.ROOM_DETAILS_TAX.get(0).getText();
			if (Tax.isEmpty())
				ScreenShot("Verification of Hotel Information - Tax", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Tax : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+Tax+"</b>" );
		}

		if(chooseHotelPage.ROOM_DETAILS_ROOM_DESCRIPTION.size()>0)
		{
			String RoomDescription= chooseHotelPage.ROOM_DETAILS_ROOM_DESCRIPTION.get(0).getText();
			if (RoomDescription.isEmpty())
				ScreenShot("Verification of Hotel Information - Room Description", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Room Description : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+RoomDescription+"</b>" );
		}
		
		if(chooseHotelPage.ROOM_DETAILS_RATE_DESCRIPTION.size()>0)
		{
			String RateDescription= chooseHotelPage.ROOM_DETAILS_RATE_DESCRIPTION.get(0).getText();
			if (RateDescription.isEmpty())
				ScreenShot("Verification of Hotel Information - Rate Description", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Rate Description : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+RateDescription+"</b>" );
		}
		
		if(chooseHotelPage.ROOM_DETAILS_MEAL_PLAN.size()>0)
		{
			String MealPlan= chooseHotelPage.ROOM_DETAILS_MEAL_PLAN.get(0).getText();
			if (MealPlan.isEmpty())
				ScreenShot("Verification of Hotel Information - Meal Plan", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Meal Plan : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+MealPlan+"</b>" );
		}
		
		if(chooseHotelPage.ROOM_DETAILS_TAX_DESCRIPTION.size()>0)
		{
			String TaxDescription= chooseHotelPage.ROOM_DETAILS_TAX_DESCRIPTION.get(0).getText();
			if (TaxDescription.isEmpty())
				ScreenShot("Verification of Hotel Information - Tax Description", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Tax Description : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+TaxDescription+"</b>" );
		}
		
		if(chooseHotelPage.ROOM_DETAILS_AGENCY_INFORMATION.size()>0)
		{
			String AgencyInformation= chooseHotelPage.ROOM_DETAILS_AGENCY_INFORMATION.get(0).getText();
			if (AgencyInformation.isEmpty())
				ScreenShot("Verification of Hotel Information - Tax Description", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Agency Information : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+AgencyInformation+"</b>" );
		}
		
		if(chooseHotelPage.ROOM_DETAILS_CHECK_IN_INSTRUCTIONS.size()>0)
		{
			String CheckInInstructions= chooseHotelPage.ROOM_DETAILS_CHECK_IN_INSTRUCTIONS.get(0).getText();
			if (CheckInInstructions.isEmpty())
				ScreenShot("Verification of Hotel Information - Tax Description", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Check In Instructions : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+CheckInInstructions+"</b>" );
		}

		if(chooseHotelPage.ROOM_DETAILS_GUARANTEE_POLICY.size()>0)
		{
			String GauranteePolicy= chooseHotelPage.ROOM_DETAILS_GUARANTEE_POLICY.get(0).getText();
			if (GauranteePolicy.isEmpty())
				ScreenShot("Verification of Hotel Information - Gaurantee Policy", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Gaurantee Policy : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+GauranteePolicy+"</b>" );
		}
		
		if(chooseHotelPage.ROOM_DETAILS_OTHER_INFORMATION.size()>0)
		{
			String OtherInformation= chooseHotelPage.ROOM_DETAILS_OTHER_INFORMATION.get(0).getText();
			if (OtherInformation.isEmpty())
				ScreenShot("Verification of Hotel Information - Other Information", "FAIL", test);
			else
				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Other Information : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+OtherInformation+"</b>" );
		}
		

	}
	
	
	
//	public void verifyhotelInformation1() throws InterruptedException
//	{
//		ChooseHotelPage chooseHotelPage = new ChooseHotelPage();
//		chooseHotelPage.VIEW_DETAILS.click();
//		ScreenShot("Hotel Information ","INFO",test);
//		if((chooseHotelPage.HOTEL_INFORMATION_ADDRESS).size()>0)
//		{
//			String Address= (chooseHotelPage.HOTEL_INFORMATION_ADDRESS).get(0).getText();
//			if (Address.isEmpty())
//				ScreenShot("Verification of Hotel Address", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Address : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+Address+"</b>" );
//		}
//		
//		if((chooseHotelPage.HOTEL_INFORMATION_CONTACT).size()>0)
//		{
//			String Contact= chooseHotelPage.HOTEL_INFORMATION_CONTACT.get(0).getText();
//			if (Contact.isEmpty())
//				ScreenShot("Verification of Hotel Contact", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Contact : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+Contact+"</b>" );
//		}
//		
//		if(chooseHotelPage.HOTEL_INFORMATION_DESCRIPTION.size()>0)
//		{
//			String Description= chooseHotelPage.HOTEL_INFORMATION_DESCRIPTION.get(0).getText();
//			if (Description.isEmpty())
//				ScreenShot("Verification of Hotel Description", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Description : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+Description+"</b>" );
//		}
//	
//		chooseHotelPage.HOTEL_INFORMATION_DESCRIPTION_READ_MORE.click();
//		Thread.sleep(2000);
//		String GeneralDescription= chooseHotelPage.HOTEL_INFORMATION_DESCRIPTION_READ_MORE_GENERAL_DESCRIPTION.getText();
//		ScreenShot("Hotel Description Read More", "INFO", test);
//		chooseHotelPage.HOTEL_INFORMATION_DESCRIPTION_READ_MORE_CLOSE.click();
//		
//		Thread.sleep(2000);
//
//		chooseHotelPage.HOTEL_INFORMATION_MAP_ZOOM_IN.click();
//		Thread.sleep(2000);
//		
//		chooseHotelPage.HOTEL_INFORMATION_MAP_ZOOM_OUT.click();
//		Thread.sleep(2000);
//		
//		chooseHotelPage.HOTEL_INFORMATION_HOTEL_IMAGE_MORE_PHOTOS.click();
//		
//		ScreenShot("More Photos", "INFO", test);
//		chooseHotelPage.HOTEL_INFORMATION_HOTEL_IMAGE_MORE_PHOTOS_PREVIOUS.click();
//		Thread.sleep(2000);
//
//		chooseHotelPage.HOTEL_INFORMATION_HOTEL_IMAGE_PHOTOS_NEXT.click();
//		Thread.sleep(2000);
//		
//		chooseHotelPage.HOTEL_INFORMATION_HOTEL_IMAGE_MORE_PHOTOS_CLOSE.click();
//		JavascriptExecutor js = (JavascriptExecutor)(driver());
//		js.executeScript("window.scrollBy(0,900)");
//		ScreenShot("Hotel Information Room Details","INFO",test);
//		
//		if((chooseHotelPage.ROOM_DETAILS_CANCELLATION_POLICY).size()>0)
//		{
//			String CancellationPolicy= chooseHotelPage.ROOM_DETAILS_CANCELLATION_POLICY.get(0).getText();
//			if (CancellationPolicy.isEmpty())
//				ScreenShot("Verification of Hotel Cancellation Policy", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Cancellation Policy : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+CancellationPolicy+"</b>" );
//		}
//		
//
//		if(chooseHotelPage.ROOM_DETAILS_AVERAGE_RATE_PER_NIGHT.size()>0)
//		{
//			String AverageRatePerNight= chooseHotelPage.ROOM_DETAILS_AVERAGE_RATE_PER_NIGHT.get(0).getText();
//			if (AverageRatePerNight.isEmpty())
//				ScreenShot("Verification of Hotel Information - Average Rate Per Night", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Average Rate Per Night : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+AverageRatePerNight+"</b>" );
//		}
//		
//		if(chooseHotelPage.ROOM_DETAILS_TOTAL_AMOUNT.size()>0)
//		{
//			String TotalAmount= chooseHotelPage.ROOM_DETAILS_TOTAL_AMOUNT.get(0).getText();
//			if (TotalAmount.isEmpty())
//				ScreenShot("Verification of Hotel Information - Total Amount", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Total Amount : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+TotalAmount+"</b>" );
//		}
//		
//		if(chooseHotelPage.ROOM_DETAILS_TAX.size()>0)
//		{
//			String Tax= chooseHotelPage.ROOM_DETAILS_TAX.get(0).getText();
//			if (Tax.isEmpty())
//				ScreenShot("Verification of Hotel Information - Tax", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Tax : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+Tax+"</b>" );
//		}
//
//		if(chooseHotelPage.ROOM_DETAILS_ROOM_DESCRIPTION.size()>0)
//		{
//			String RoomDescription= chooseHotelPage.ROOM_DETAILS_ROOM_DESCRIPTION.get(0).getText();
//			if (RoomDescription.isEmpty())
//				ScreenShot("Verification of Hotel Information - Room Description", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Room Description : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+RoomDescription+"</b>" );
//		}
//		
//		if(chooseHotelPage.ROOM_DETAILS_RATE_DESCRIPTION.size()>0)
//		{
//			String RateDescription= chooseHotelPage.ROOM_DETAILS_RATE_DESCRIPTION.get(0).getText();
//			if (RateDescription.isEmpty())
//				ScreenShot("Verification of Hotel Information - Rate Description", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Rate Description : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+RateDescription+"</b>" );
//		}
//		
//		if(chooseHotelPage.ROOM_DETAILS_MEAL_PLAN.size()>0)
//		{
//			String MealPlan= chooseHotelPage.ROOM_DETAILS_MEAL_PLAN.get(0).getText();
//			if (MealPlan.isEmpty())
//				ScreenShot("Verification of Hotel Information - Meal Plan", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Meal Plan : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+MealPlan+"</b>" );
//		}
//		
//		if(chooseHotelPage.ROOM_DETAILS_TAX_DESCRIPTION.size()>0)
//		{
//			String TaxDescription= chooseHotelPage.ROOM_DETAILS_TAX_DESCRIPTION.get(0).getText();
//			if (TaxDescription.isEmpty())
//				ScreenShot("Verification of Hotel Information - Tax Description", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Tax Description : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+TaxDescription+"</b>" );
//		}
//		
//		if(chooseHotelPage.ROOM_DETAILS_AGENCY_INFORMATION.size()>0)
//		{
//			String AgencyInformation= chooseHotelPage.ROOM_DETAILS_AGENCY_INFORMATION.get(0).getText();
//			if (AgencyInformation.isEmpty())
//				ScreenShot("Verification of Hotel Information - Tax Description", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Agency Information : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+AgencyInformation+"</b>" );
//		}
//		
//		if(chooseHotelPage.ROOM_DETAILS_CHECK_IN_INSTRUCTIONS.size()>0)
//		{
//			String CheckInInstructions= chooseHotelPage.ROOM_DETAILS_CHECK_IN_INSTRUCTIONS.get(0).getText();
//			if (CheckInInstructions.isEmpty())
//				ScreenShot("Verification of Hotel Information - Tax Description", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Check In Instructions : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+CheckInInstructions+"</b>" );
//		}
//
//		if(chooseHotelPage.ROOM_DETAILS_GUARANTEE_POLICY.size()>0)
//		{
//			String GauranteePolicy= chooseHotelPage.ROOM_DETAILS_GUARANTEE_POLICY.get(0).getText();
//			if (GauranteePolicy.isEmpty())
//				ScreenShot("Verification of Hotel Information - Gaurantee Policy", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Gaurantee Policy : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+GauranteePolicy+"</b>" );
//		}
//		
//		if(chooseHotelPage.ROOM_DETAILS_OTHER_INFORMATION.size()>0)
//		{
//			String OtherInformation= chooseHotelPage.ROOM_DETAILS_OTHER_INFORMATION.get(0).getText();
//			if (OtherInformation.isEmpty())
//				ScreenShot("Verification of Hotel Information - Other Information", "FAIL", test);
//			else
//				test.log(LogStatus.PASS, "<b style='color:#1976d2;Font-size:12px;font-family: verdana'>"+ "Hotel Information - Other Information : </b>"+"<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+OtherInformation+"</b>" );
//		}
//		
//
//	}
	
	public void verifyRates(String Param) throws InterruptedException
	{
		ChooseHotelPage chooseHotelPage = new ChooseHotelPage();
		
		if(Param.toLowerCase().equals("sabre"))
		{
			String xpath="//span[contains(text(),\"_hotelName\")]";
			String xpath_replace=xpath.replaceAll("_hotelName", Hotel);
			wait.until(ExpectedConditions.invisibilityOf(chooseHotelPage.FIRST_RESULT_SPIN));
//			driver().findElement(By.xpath(xpath_replace)).click();
			chooseHotelPage.ROOM_IT_RATES_LINK.click();
//			test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "RoomIt rates are displaying " + "<b>");
			js.executeScript("window.scrollBy(0,300)");
			ScreenShot("RoomIt rates are available", "INFO", test);
			chooseHotelPage.PUBLIC_RATES_LINK.click();
			ScreenShot("Public rates are available", "INFO", test);
		}
		if(Param.toLowerCase().equals("hhe"))
		{
			
			chooseHotelPage.PUBLIC_RATES_LINK.click();
			Thread.sleep(1000);
			js.executeScript("window.scrollBy(0,1200)");
			Thread.sleep(1000);
//			js.executeScript("window.scrollBy(0,200)");
			ScreenShot("HHE Public rates are available", "INFO", test);
			
		}
		if(Param.toLowerCase().equals("hheonrequest"))
		{
			
//			chooseHotelPage.ON_REQUEST_RATES_LINK.click();
			Thread.sleep(10000);
//			chooseHotelPage.VIEW_ALL_RATES_ONRQUEST.click();
//			chooseHotelPage.SELECT_RATE_FIRST_ONLINE_CANCELLABLE.click();
			js.executeScript("window.scrollBy(0,300)");
			js.executeScript("window.scrollBy(0,200)");
			ScreenShot("HHE On-request rates available", "INFO", test);
			
		}
		if(Param.toLowerCase().equals("amadeus"))
		{
			
			String xpath="//span[contains(text(),\"_hotelName\")]";
			String xpath_replace=xpath.replaceAll("_hotelName", Hotel);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath_replace)));
//			driver().findElement(By.xpath(xpath_replace)).click();
			Assert.assertTrue(chooseHotelPage.CLIENT_RATES_LINK.isDisplayed());
//			chooseHotelPage.CLIENT_RATES_LINK.click();
			js.executeScript("window.scrollBy(0,200)");
			ScreenShot("Client rates are available", "INFO", test);
			Assert.assertTrue(chooseHotelPage.ROOM_IT_RATES_LINK.isDisplayed());
			js.executeScript("window.scrollBy(0,200)");
//			test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "RoomIt rates are displaying " + "<b>");
			ScreenShot("RoomIt rates are available", "INFO", test);
			Assert.assertTrue(chooseHotelPage.PUBLIC_RATES_LINK.isDisplayed());
//			chooseHotelPage.PUBLIC_RATES_LINK.click();
			js.executeScript("window.scrollBy(0,200)");
			ScreenShot("Public rates are available", "INFO", test);
			
		}
		
		
		if(Param.toLowerCase().equals("bcom"))
		{
			Thread.sleep(2000);
			chooseHotelPage.AGGREGATOR_LINK.click();
			js.executeScript("window.scrollBy(0,300)");
			js.executeScript("window.scrollBy(0,200)");
//			Assert.assertTrue(chooseHotelPage.SELECT_RATE_FIRST_BCOM_CANCELLABLE.isDisplayed());
			ScreenShot("Public rates are available", "INFO", test);
		}
		
		
	}
	
	
	
	
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
			Thread.sleep(1000);
			chooseHotelPage.VIEW_ALL_RATES_AGGREGATOR.click();
			Thread.sleep(1000);
			if(BookingFlow.toLowerCase().equals("auto book"))
			{
				
				chooseHotelPage.AUTO_BOOK_FIRST_BCOM_CANCELLABLE.click();
			}
			else
			{
				Thread.sleep(1000);
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
//		chooseOtherRoom();
	}
	
	public void chooseOtherRoom() throws InterruptedException
	{
		SearchHotelStep1Page searchHotelStep1Page = new SearchHotelStep1Page();
		ChooseHotelPage chooseHotelPage = new ChooseHotelPage();
		if(driver().findElements(chooseHotelPage.NO_ROOM_RATES_BY()).size()>=1)
		{
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver();
			js.executeScript("window.scrollBy(0,-500)");
			searchHotelStep1Page.CHECKIN_CHECKOUT_DATE_HEADER.click();
			Thread.sleep(2000);
			searchHotelStep1Page.CHECKIN_DATE_HEADER_NEXT_DAY.click();
			searchHotelStep1Page.CHECKIN_CHECKOUT_DATE_HEADER_NEXT_DAY.click();
			Thread.sleep(2000);
			searchHotelStep1Page.CHECKIN_CHECKOUT_DATE_HEADER_PROCEED.click();
			chooseHotel(BookingType,BookingFlow);
		}
	}
	
	public void addNewCard() throws InterruptedException
	{
		BookHotelPage bookHotelPage = new BookHotelPage();
		bookHotelPage.ADD_NEW_CARD.click();
		Thread.sleep(1000);
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Enter the new card details" + "<b>");
		
		Select s=new Select(bookHotelPage.CARD_TYPE_DROPDOWN);
		s.selectByVisibleText("Visa");
		bookHotelPage.CARD_NUMBER.sendKeys("411111111111111");
		bookHotelPage.NAME_CARD.sendKeys("Test User");

		Select s1=new Select(bookHotelPage.EXPIRY_MONTH_DROPDOWN);
		s1.selectByVisibleText("JAN");
		
		Select s2=new Select(bookHotelPage.EXPIRY_YEAR_DROPDOWN);
		s2.selectByVisibleText("2022");
		ScreenShot("Card details entered successfully", "INFO", test);
	}
	
	public void fillOnlineBookingDetails() throws InterruptedException
	{
		
		BookHotelPage bookHotelPage = new BookHotelPage();
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
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Enter the SI Remarks" + "<b>");
		bookHotelPage.INTERNAL_REMARKS.sendKeys("This is test booking, please cancel");
		ScreenShot("Booking details input successful", "INFO", test);
	
	}
	
	public void extractTraveller(String PNR) throws InterruptedException
	{
		BookHotelPage bookHotelPage = new BookHotelPage();
		bookHotelPage.EXTRACT_TRAVELLER_INPUT.sendKeys(PNR);
		bookHotelPage.EXTRACT_TRAVELLER_DETAILS.click();
	}
	
	public void verifyExtractTravellerInformationInStep2(String PNR,String CardAssociation) throws InterruptedException
	{
		BookHotelPage bookHotelPage = new BookHotelPage();
		extractTraveller(PNR);
		ScreenShot("Extract traveller information", "PASS", test);
		String firstName=bookHotelPage.TRAVELLER_FIRST_NAME.getAttribute("value");
		String lastName=bookHotelPage.TRAVELLER_LAST_NAME.getAttribute("value");
		String pnr=bookHotelPage.PNR_INPUT.getAttribute("value");
		System.out.println(firstName +" "+lastName);
		Assert.assertTrue(!firstName.isEmpty());
		Assert.assertTrue(!lastName.isEmpty());
		Assert.assertTrue(!pnr.isEmpty());
		Thread.sleep(1000);
		if(CardAssociation.contains("Yes"))
		{
			Assert.assertTrue(bookHotelPage.PAYMENT_TYPE_DROPDOWN_SELECTED.isDisplayed());
		}
		else if(CardAssociation.contains("No"))
		{
			js.executeScript("window.scrollBy(0,250)");
			Assert.assertTrue(bookHotelPage.PAYMENT_TYPE_ADD_NEW_CARD.isDisplayed());
		}
		ScreenShot("Extract traveller information", "PASS", test);
	}
	
	
	
	public void clickOnBookButton() throws InterruptedException
	{
		BookHotelPage bookHotelPage = new BookHotelPage();
		bookHotelPage.PROCEED_BUTTON.click();
		Thread.sleep(5000);
	}
	
	public void fulFillNowDetailsAndClickBookButton(String PaymentType, String PaymentCard)
	{
		
	}
	
	public void verifyBookingConfirmation() throws InterruptedException
	{
		BookingConfirmationPage bookingConfirmationPage = new BookingConfirmationPage();
		
		wait.until(ExpectedConditions.visibilityOf(bookingConfirmationPage.BOOKING_CONFIRMATION_SUCCESS_TEST_BOOKINGS_ONLINE));
		ScreenShot("Booking Confirmation Page", "INFO", test);
		test.log(LogStatus.INFO, "<b style='color:#3b3f42;Font-size:12px;font-family: verdana'>"+ "Verify the booking cofirmation" + "<b>");
		Assert.assertTrue(bookingConfirmationPage.BOOKING_CONFIRMATION_SUCCESS_TEST_BOOKINGS_ONLINE.isDisplayed(),"Booking confirmation failed");
		String BookingReferenceNumber= bookingConfirmationPage.BOOKING_REFERENCE_NUMBER.getText();
		ScreenShot("Booking Confirmation successful", "PASS", test);
		System.out.println(BookingReferenceNumber);
		reportingUtils.setOutPutData(BookingReferenceNumber);
//		logOut(test);
		cancelBookingPH(BookingReferenceNumber,test);
		
	}
	

	
	public void goToPreviousBookings() throws InterruptedException
	{
	
		HomePage homePage = new HomePage();
		Thread.sleep(2000);
		homePage.MORE.click();
		homePage.PREVIOUS_BOOKINGS.click();
		
		Thread.sleep(2000);

		
	}
	
	
	public void applySearch(String searchBy,String input)
	{
		PreviousBookingsPage previousBookingsPage = new PreviousBookingsPage();
		this.searchBy=searchBy;
		this.input=input;
		if(searchBy.contains("Market"))
		{
			Select select = new Select(previousBookingsPage.MARKET);
			select.selectByVisibleText(input);
		}
		if(searchBy.contains("Guest Name"))
		{
			previousBookingsPage.GUEST_NAME_SEARCH.sendKeys(input);
		}
		if(searchBy.contains("PNR"))
		{
			previousBookingsPage.PNR_SEARCH.clear();
			previousBookingsPage.PNR_SEARCH.sendKeys(input);
		}
		previousBookingsPage.LIST_BOOKINGS.click();
		
	}
	
	public void applyFilter(String filterBy,String filterCriteria)
	{
		PreviousBookingsPage previousBookingsPage = new PreviousBookingsPage();
		
		this.filterBy=filterBy;
		this.filterCriteria=filterCriteria;
		
			Select select = new Select(previousBookingsPage.FILTER_BY_DROP_DOWN);
			select.selectByVisibleText(filterBy);
			Select select1 = new Select(previousBookingsPage.FILTER_NAME_DROP_DOWN);
			select1.selectByVisibleText(filterCriteria);
		
	}

	public void sortTheResults(String sortBy, String order) throws InterruptedException
	{
		PreviousBookingsPage previousBookingsPage = new PreviousBookingsPage();
		
		Select select = new Select(previousBookingsPage.SORT_BY_DROP_DOWN);
		select.selectByVisibleText(sortBy);
		Thread.sleep(2000);
		if(order.equals("desc"))
		{
			try
			{
				previousBookingsPage.SORT_BY_DESCENDING.click();
			}
			catch(Exception e)
			{
				
			}
		}
		
		if(order.equals("asce"))
		{
			try
			{
				previousBookingsPage.SORT_BY_ASCENDING.click();
			}
			catch(Exception e)
			{
				
			}
		}
		
		
		driver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver(),60);
		
		
	}

	
	public void verifyPreviousBookingsFilter(String filterBy, String input) throws InterruptedException
	{
		
		PreviousBookingsPage previousBookingsPage = new PreviousBookingsPage();
	
		List<WebElement> web =new ArrayList<WebElement>();
		if(filterBy.contains("Reference"))
		{
			web= previousBookingsPage.CUSTOMER_LIST;
		}
		if(filterBy.equals("PNR"))
		{
			web= previousBookingsPage.PNR_LIST;
		}
//		if(filterBy.equals("PNR"))
//		{
//			web= previousBookingsPage.PNR_LIST;
//		}
//		
//		if(filterBy.equals("PNR"))
//		{
//			web= previousBookingsPage.PNR_LIST;
//		}
		String[] value= new String[web.size()];
			
		
				
				for(int i=0;i<web.size();i++)
				{
					value[i]=web.get(i).getAttribute("textContent");
				}
			
			for(String a:value)
			{
				if(!a.contains(input))
					ScreenShot("Filter By "+filterBy +"Failed ", "FAIL", test);
			}
			
			
		}
		
	public void verifyPNR()
	{
		PreviousBookingsPage previousBookingsPage = new PreviousBookingsPage();
//		previousBookingsPage.BOOKING_REFERENCE_LIST.click();
//		String script = return document.getElementByXpath('//span[@class='pl-5']/a').innerHTML;
//		System.out.println(script);
		String pnr = previousBookingsPage.PNR.getAttribute("textContent");
		
		
	}
	
	
	
	public void verifyPreviousBookingsFilterBy(String filterBy,String input) throws InterruptedException
	{
		PreviousBookingsPage previousBookingsPage = new PreviousBookingsPage();
		Thread.sleep(2000);
		List<WebElement> web =new ArrayList<WebElement>();
		if(filterBy.equals("Agent"))
		{
			web= previousBookingsPage.BOOKED_DATE_LIST;
		}
		String[] value= new String[web.size()];

		for(int i=0;i<web.size();i++)
				{
					value[i]=web.get(i).getAttribute("data-original-title");
				}
			
			
		for(String a:value)
			{
				
				if(!a.contains(input))
				ScreenShot("Filter By "+filterBy +"Failed ", "FAIL", test);
			}
		
	}
	
	
	public void cancelMultipleBookings() throws InterruptedException
	{
		
		
		while(isElementPresent(PreviousBookingsPage.CANCEL_ICON_BY()))
		{
			PreviousBookingsPage previousBookingsPage = new PreviousBookingsPage();
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
			applySearch(searchBy,input);
			applyFilter(filterBy,filterCriteria);
			sortTheResults("Status","asce");
			
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
	
	
	public void cancelBooking(String BookingReferenceNumber, ExtentTest test) throws InterruptedException
	{
		HomePage homePage = new HomePage();
		BookingConfirmationPage bookingConfirmationPage = new BookingConfirmationPage();
		PreviousBookingsPage previousBookingsPage = new PreviousBookingsPage();
		driver().get("https://ngbeta.hotelhub.net/v1");
		homePage.MORE.click();
		homePage.PREVIOUS_BOOKINGS.click();
		wait.until(ExpectedConditions.visibilityOf(previousBookingsPage.BOOKING_REFERENCE));
		previousBookingsPage.BOOKING_REFERENCE.sendKeys(BookingReferenceNumber);
		previousBookingsPage.LIST_BOOKINGS.click();
		previousBookingsPage.CANCEL_ICON.click();
		wait.until(ExpectedConditions.visibilityOf(previousBookingsPage.CANCEL_YES));
		previousBookingsPage.CANCEL_YES.click();
//		wait.until(ExpectedConditions.visibilityOf(previousBookingsPage.CANCELLATION_REFRENCE_NUMBER));
//		String CancellationRefrenceNumber=previousBookingsPage.CANCELLATION_REFRENCE_NUMBER.getText();
//		System.out.println(CancellationRefrenceNumber);
		
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
