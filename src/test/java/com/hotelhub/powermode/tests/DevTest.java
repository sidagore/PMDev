package com.hotelhub.powermode.tests;

import java.text.SimpleDateFormat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hotelhub.powermode.helpers.HotelBookingHelper;
import com.hotelhub.powermode.helpers.LoginHelper;
import com.hotelhub.powermode.pages.HomePage;
import com.hotelhub.powermode.pages.LoginPage;

import base.Base;
import base.PowerModeConstants;
import base.Property;
import utils.ReportingUtils;

@Listeners(utils.MyListener.class)
public class DevTest extends Base{

	
	@BeforeMethod(alwaysRun=true)
	void loginToTheApplication() throws Throwable
	{
		LoginHelper loginHelper = new LoginHelper();
		Property props = new Property();
		String Url=props.getProperty("PowerModeDev");
		String UserName=props.getProperty("UserName");
		String PassWord=props.getProperty("PassWord");
		driver().get(Url);
		LoginPage loginPage = new LoginPage();
		HomePage homePage = new HomePage();
		loginPage.userName.sendKeys(UserName);
		loginPage.passWord.sendKeys(PassWord);
		loginPage.signInButton.click();
	}
	
	
	@Test(groups="DevTest",priority=1)
	void BCOMbookingWithVISACard() throws Throwable
	{
		String methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		reportingUtils =new ReportingUtils();
		reportingUtils.setTestCaseNumber("TC02");
		reportingUtils.setSummary("Verify user is able to create BCOM booking");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName(methodName);
//		SessionId sessionId = driver().getSessionId();
//		System.out.println("Session ID : "+sessionId.toString());
		HotelBookingHelper hotelBookingHelper = new HotelBookingHelper();
//		hotelBookingHelper.initializeInputData();
		hotelBookingHelper.enterCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"SABRE");
		hotelBookingHelper.searcHotel(PowerModeConstants.BCOM_BOOKING_TEST_HOTEL);
		hotelBookingHelper.selectCheckInCheckOutDate();
//		hotelBookingHelper.clickOnSearchHotels();
		hotelBookingHelper.chooseHotel("BCOM","");
		hotelBookingHelper.fillOnlineBookingDetails();
		hotelBookingHelper.clickOnBookButton();
		hotelBookingHelper.verifyBookingConfirmation();
		reportingUtils.setTestResult("PASS");
		
	}
}
