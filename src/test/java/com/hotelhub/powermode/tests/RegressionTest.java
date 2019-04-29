package com.hotelhub.powermode.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hotelhub.powermode.helpers.HotelBookingHelper;
import com.hotelhub.powermode.helpers.LoginHelper;
import com.hotelhub.powermode.helpers.RegressionHelper;
import base.Base;
import base.PowerModeConstants;
import base.Property;
import utils.ReportingUtils;


@Listeners(utils.MyListener.class)

public class RegressionTest extends Base{

	public static int totalNoOfRows;
	
	@BeforeMethod(alwaysRun=true)
	void loginToTheApplication() throws Throwable
	{
		LoginHelper loginHelper = new LoginHelper();
		reportingUtils =new ReportingUtils();
//		ExtentTest test = ExtentTestManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		loginHelper.login(test);
		
		
	}
	
	
	public static String[][] readinput() throws IOException
	{
		Property props = new Property();
		FileInputStream fi = new FileInputStream(props.getProperty("InputFileLocation"));
		XSSFWorkbook workbook = new XSSFWorkbook(fi);
		XSSFSheet sheet = workbook.getSheetAt(1);
		 totalNoOfRows = sheet.getLastRowNum();
		
		String[][] arrayExcelData = new String[totalNoOfRows][3];
		
		for (int i= 0 ; i < totalNoOfRows; i++) {
			
				for (int j=0; j <=2; j++) {
					arrayExcelData[i][j] = sheet.getRow(i+1).getCell(j).toString();
					
				}
		}
		return arrayExcelData;
	}
	
	
	
	@DataProvider(name="data")
//	@Test
	public String[][] dataProvider(Method m) throws IOException
	{
		String[][] data=readinput();
		
		
		String testName=m.getName();
		
		
		int count=0;
		int rows=0;
//		
		for(int k=0;k<totalNoOfRows;k++)
		{
		
			if(data[k][0].contains(testName))
				rows++;
			
		}
		String[][] testData= new String[rows][2];
		for(int i=0;i<totalNoOfRows-1;i++)
		{
			if(data[i][0].contains(testName))
			{
				
				for(int j=0; j<2;j++)
				{
					testData[count][j]=data[i][j+1];
					
					
				}
				count++;
				
			}
		}
	
		return testData;
	}

	
	
//	--done
	@Test(groups="RegressionDailyRun1")
	public void displayPreviouslySearchedPNRandCustomer() throws InterruptedException
	{
		
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC07");
		reportingUtils.setSummary("Display of Previously Searched PNR and Customers");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Display of Previously Searched PNR and Customers");
		reportingUtils.setInputData(PowerModeConstants.TEST_PNR_SABRE);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.verifyPreviouslySelectedPNRorCustomer();
		
	}
	
	@Test(groups="RegressionDailyRun")
	public void verificationOfSelectRateFunctionality() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Select Rate Functionality");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification for Select Rate Functionality");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"");
		regressionHelper.searchLocation(PowerModeConstants.BCOM_BOOKING_TEST_HOTEL);
		Thread.sleep(2000);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.clickGetRates();
		regressionHelper.chooseHotel("BCOM", "");
		ScreenShot("Verification of Select Rate Functionality is successful", "PASS", test);
		reportingUtils.setTestResult("PASS");
	}
	
	@Test(priority=1,groups={"Rates","RegressionDailyRun2"})
	public void verificationOfAmadeusRatesDisplay() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of Rates display");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Amadeus Rates");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER_CLIENT_RATE_AMADEUS);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_CLIENT_RATE_AMADEUS,"");
		regressionHelper.selectDestinationType("HOTEL");
		regressionHelper.searchLocation(PowerModeConstants.AMADEUS_RATES_TEST_HOTEL_ONLINE);
		
		regressionHelper.selectLocation("");
		regressionHelper.clickOnSearchHotelsButton();
		Thread.sleep(10000);
		regressionHelper.verifyRates("AMADEUS");
//		regressionHelper.chooseHotel("BCOM", "");
		ScreenShot("Verification of Amadeus Rates Display is successful", "PASS", test);
		reportingUtils.setTestResult("PASS");
	}
	
	@Test(priority=2,groups={"Rates","RegressionDailyRun"})
	public void verificationOfSabreRatesDisplay() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of Rates display");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Sabre Rates");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"SABRE");
		regressionHelper.selectDestinationType("HOTEL");
		regressionHelper.searchLocation(PowerModeConstants.SABRE_RATES_TEST_HOTEL_ONLINE);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.clickGetRates();
		regressionHelper.verifyRates("SABRE");
//		regressionHelper.chooseHotel("BCOM", "");
		ScreenShot("Verification of Sabre Rates Display is successful", "PASS", test);
		reportingUtils.setTestResult("PASS");
	}
	
	@Test(priority=3,groups={"Rates","RegressionDailyRun"})
	public void verificationOfBcomRatesDisplay() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of Rates display");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Bcom Rates");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"");
		regressionHelper.searchLocation(PowerModeConstants.BCOM_BOOKING_TEST_HOTEL);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.verifyRates("BCOM");
//		regressionHelper.chooseHotel("BCOM", "");
		ScreenShot("Verification of Bcom Rates Display is successful", "PASS", test);
		reportingUtils.setTestResult("PASS");
	}

	@Test(priority=4,groups={"Rates","RegressionDailyRun"})
	public void verificationOfHHEOnlineRatesDisplay() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of Rates display");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of HHE Online Rates");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"SABRE");
		regressionHelper.searchLocation(PowerModeConstants.HHE_BOOKING_TEST_HOTEL);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
//		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.verifyRates("HHE");
//		regressionHelper.chooseHotel("BCOM", "");
		ScreenShot("Verification of HHE Online Rates Display is successful", "PASS", test);
		reportingUtils.setTestResult("PASS");
	}
	
	
	
	@Test(priority=5,groups={"Rates","RegressionDailyRun"})
	public void verificationOfHHEOnrequestRatesDisplay() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of Rates display");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of HHE On-Request Rates");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER_CLIENT_RATE_HHE_ONREQUEST);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_CLIENT_RATE_HHE_ONREQUEST,"");
		regressionHelper.searchLocation(PowerModeConstants.HHE_ON_REQUEST_TEST_HOTEL_ONLINE);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
//		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.verifyRates("HHEOnrequest");
//		regressionHelper.chooseHotel("BCOM", "");
		ScreenShot("Verification of HHE Online Rates Display is successful", "PASS", test);
		reportingUtils.setTestResult("PASS");
	}
	
	
	@Test(priority=4,groups={"RegressionDailyRun"})
	public void verificationOfPNRGDSMismatchError() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of PNR GDS mismatch error");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Error message when PNR and GDS mismatch");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.verifyPNRGDSMismatchError(PowerModeConstants.TEST_PNR_AMADEUS);
		reportingUtils.setTestResult("PASS");
	}
	
	//In-Progress
	@Test(priority=3,groups={"RegressionDailyRun","DestinationSearch"})
	public void verificationOfDestinationSearchHotel() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of Destination Search Type");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Destination Search Type as Hotel");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"");
		regressionHelper.selectDestinationType("HOTEL");
		regressionHelper.searchLocation("Bangalore, Karnataka, India");
		regressionHelper.verifyDestinationType("HOTEL");
		reportingUtils.setTestResult("PASS");
	}
	

	@Test(priority=4,groups={"RegressionDailyRun","DestinationSearch"})
	public void verificationOfDestinationSearchAirport() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of Destination Search Type");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Destination Search Type as Airport");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"");
		regressionHelper.selectDestinationType("AIRPORT");
		regressionHelper.searchLocation("JFK");
		regressionHelper.verifyDestinationType("AIRPORT");
		reportingUtils.setTestResult("PASS");
	}
	
	@Test(priority=2,groups={"RegressionDailyRun","DestinationSearch"})
	public void verificationOfDestinationSearchGoogle() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of Destination Search Type");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Destination Search Type as Google");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"");
		regressionHelper.selectDestinationType("GOOGLE");
		regressionHelper.searchLocation("Bangalore, Karnataka, India");
		regressionHelper.verifyDestinationType("GOOGLE");
		reportingUtils.setTestResult("PASS");
	}
	
	@Test(priority=1,groups={"RegressionDailyRun","DestinationSearch"})
	public void verificationOfDestinationSearchAll() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of PNR GDS mismatch error");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Destination Search Type as All");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"");
		regressionHelper.searchLocation("JFK");
		regressionHelper.verifyDestinationType("ALL");
		reportingUtils.setTestResult("PASS");
	}
	
	@Test(priority=1,groups={"",""})
	public void verificationOfDestinationSearchGoogleAndAirport() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of PNR GDS mismatch error");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Destination Search Type as All");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"");
		regressionHelper.selectDestinationType("GOOGLE_AIRPORT");
		regressionHelper.searchLocation("JFK");
		regressionHelper.verifyDestinationType("GOOGLE_AIRPORT");
		reportingUtils.setTestResult("PASS");
	}
	
	@Test(priority=1,groups={"",""})
	public void verificationOfDestinationSearchGoogleAndHotel() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of PNR GDS mismatch error");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Destination Search Type as All");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"");
		regressionHelper.selectDestinationType("GOOGLE_HOTEL");
		regressionHelper.searchLocation("Bangalore, Karnataka, India");
		regressionHelper.verifyDestinationType("GOOGLE_HOTEL");
		reportingUtils.setTestResult("PASS");
	}
	
	@Test(priority=1,groups={"",""})
	public void verificationOfDestinationSearchAirportAndHotel() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of PNR GDS mismatch error");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Destination Search Type as All");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"");
		regressionHelper.selectDestinationType("AIRPORT_HOTEL");
		regressionHelper.searchLocation("JFK");
		regressionHelper.verifyDestinationType("AIRPORT_HOTEL");
		reportingUtils.setTestResult("PASS");
	}
	
	@Test(priority=4,groups={"RegressionDailyRun"})
	public void verifyUserIsAbleToSelectCheckInCheckOutDates() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of PNR GDS mismatch error");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Destination Search Type as All");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_CLIENT_RATE_HHE_ONREQUEST,"");
		
		regressionHelper.searchLocation(PowerModeConstants.HHE_ON_REQUEST_TEST_HOTEL_ONLINE);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
		reportingUtils.setTestResult("PASS");
		
	}
	
	@Test(priority=4,groups={""})
	public void verificationOfCheckInCheckOutDate() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of PNR GDS mismatch error");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Destination Search Type as All");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_CLIENT_RATE_HHE_ONREQUEST,"");
		
		regressionHelper.searchLocation(PowerModeConstants.HHE_ON_REQUEST_TEST_HOTEL_ONLINE);
		regressionHelper.selectLocation("");
//		regressionHelper.selectCheckInCheckOutDateDelete();
		reportingUtils.setTestResult("PASS");
		
	}
	
	
	
	
	@Test(priority=4,groups={""})
	public void verificationOfPreviousBookingFilter() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC09");
		reportingUtils.setSummary("Verification of PNR GDS mismatch error");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of Destination Search Type as All");
		reportingUtils.setInputData(PowerModeConstants.TEST_CUSTOMER);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.goToPreviousBookings();
		regressionHelper.applySearch("PNR","O3OEWG");
		regressionHelper.verifyPreviousBookingsFilter("PNR","O3OEWG");
		regressionHelper.applyFilter("Agent","Shiva Prasad");
		regressionHelper.verifyPreviousBookingsFilterBy("Agent","Shiva Prasad");

		reportingUtils.setTestResult("PASS");
		
	}
	
	@Test
	public void cancelMyBookings() throws InterruptedException
	{
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.goToPreviousBookings();
		regressionHelper.applySearch("PNR","OPLWZW");
		regressionHelper.applyFilter("Agent","Shiva Prasad");
		regressionHelper.sortTheResults("Status","asce");
		regressionHelper.cancelMultipleBookings();
	}
	

	/*
	 * @TestCaseID= NG-001
	 * @Owner ="Shivaprasad"
	 * @Date Jan-19
	 */
	@Test(dataProvider="data")
	
	public void verificationOfPNRReadForApolloGDS(String TestName,String PNR) throws Throwable
	{
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC06");
		reportingUtils.setSummary("PNR Read functionality");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName(TestName);
		reportingUtils.setInputData(PNR);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PNR,"APOLLO");
		reportingUtils.setTestResult("PASS");
		
	}

	@Test(dataProvider="data")
	public void verificationOfCustomerSearchBox(String TestName,String Customer) throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC08");
		reportingUtils.setSummary("Customer Search functionality");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName(TestName);
		reportingUtils.setInputData(Customer);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(Customer,"");
		reportingUtils.setTestResult("PASS");
	}
	
//	@Test
	public void verifyUserIsAbleToLoginWithValidUserIdAndPassword() throws Throwable
	{
		LoginHelper loginHelper = new LoginHelper();
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC02");
		reportingUtils.setSummary("Login Functionality");
		reportingUtils.setTestCaseName("Verify user is able to login with valid userid and password");
		reportingUtils.setExecutionTime(timeStamp);
		loginHelper.login(test);
		reportingUtils.setTestResult("PASS");
		
	}
	
//	@Test
	public void verificationOfLoginFunctionality() throws Throwable
	{
		LoginHelper loginHelper = new LoginHelper();
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC03");
		reportingUtils.setSummary("Login Functionality");
		reportingUtils.setTestCaseName("Verification of login functionality with difference negative scenarios ");
		reportingUtils.setExecutionTime(timeStamp);
		
		loginHelper.verifyLoginFunctionality(test);
		reportingUtils.setTestResult("PASS");
		
	}
	
	@Test(dataProvider="data")
	public void verificationOfPNRReadForSABREGDS(String TestName,String PNR) throws Throwable
	{
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	
		reportingUtils.setTestCaseNumber("TC04");
		reportingUtils.setSummary("PNR Read functionality");
		reportingUtils.setTestCaseName(TestName);
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setInputData(PNR);
		
		RegressionHelper regressionHelper = new RegressionHelper();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PNR,"SABRE");
		reportingUtils.setTestResult("PASS");
	}
	
	@Test(dataProvider="data")
	public void verificationOfPNRReadForAmadeusGDS(String TestName,String PNR) throws Throwable
	{
		
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC05");
		reportingUtils.setSummary("PNR Read functionality");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName(TestName);
		reportingUtils.setInputData(PNR);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PNR,"AMADEUS");
		reportingUtils.setTestResult("PASS");
	}
	
	
	/*
	 * @Owner = Shiva Prasad
	 */
	
	@Test(dataProvider="data")
	public void verificationOfPNRReadForGalileoGDS() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC07");
		reportingUtils.setSummary("PNR Read functionality");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verify user is able to Read GALILEO PNR");
		reportingUtils.setInputData(PowerModeConstants.TEST_PNR_GALILEO);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_GALILEO,"GALILEO");
		reportingUtils.setTestResult("PASS");
	}
	
	@Test(groups="demo")
	public void verificationOfUseTravellerDetailsFromPNROnStep3OnlineRatesAmadeusPNR() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC07");
		reportingUtils.setSummary("Verification of use traveller details from PNR on Step3");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of use traveller details from PNR on Step3 Online rates Amadeus PNR");
		reportingUtils.setInputData(PowerModeConstants.TEST_PNR_GALILEO);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_AMADEUS_UK,"");
		regressionHelper.searchLocation(PowerModeConstants.BCOM_BOOKING_TEST_HOTEL);
		regressionHelper.selectLocation("");
//		regressionHelper.selectCheckInCheckOutDate();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.chooseHotel("BCOM","");
		regressionHelper.verifyExtractTravellerInformationInStep2(PowerModeConstants.TEST_PNR_AMADEUS_UK,"");
		
	}
	@Test(groups="demo")
	public void verificationOfUseTravellerDetailsFromPNROnStep3OnrequestRatesAmadeusPNR() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC07");
		reportingUtils.setSummary("Verification of use traveller details from PNR on Step3");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of use traveller details from PNR on Step3 On-request rates Amadeus PNR");
		reportingUtils.setInputData(PowerModeConstants.TEST_PNR_GALILEO);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_AMADEUS_UK,"");
		regressionHelper.searchLocation(PowerModeConstants.BCOM_BOOKING_TEST_HOTEL);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.chooseHotel("ONREQUEST","");
		regressionHelper.verifyExtractTravellerInformationInStep2(PowerModeConstants.TEST_PNR_AMADEUS_UK,"");
		
	}
	
	
	
	@Test
	public void verificationOfUseTravellerDetailsFromPNROnStep3OnlineRatesGalileoPNR() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC07");
		reportingUtils.setSummary("Verification of use traveller details from PNR on Step3");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of use traveller details from PNR on Step3 Online rates Galileo PNR");
		reportingUtils.setInputData(PowerModeConstants.TEST_PNR_GALILEO);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_GALILEO_IN,"");
		regressionHelper.searchLocation(PowerModeConstants.BCOM_BOOKING_TEST_HOTEL);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.chooseHotel("BCOM","");
		regressionHelper.verifyExtractTravellerInformationInStep2(PowerModeConstants.TEST_PNR_GALILEO_IN,"");
	}
	
	@Test
	public void verificationOfUseTravellerDetailsFromPNROnStep3OnrequestRatesGalileoPNR() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC07");
		reportingUtils.setSummary("Verification of use traveller details from PNR on Step3");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of use traveller details from PNR on Step3 On-request rates Galileo PNR");
		reportingUtils.setInputData(PowerModeConstants.TEST_PNR_GALILEO);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_SABRE_UK,"");
		regressionHelper.searchLocation(PowerModeConstants.BCOM_BOOKING_TEST_HOTEL);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.chooseHotel("ONREQUEST","");
		regressionHelper.verifyExtractTravellerInformationInStep2(PowerModeConstants.TEST_PNR_SABRE_UK,"");
	
	}
	@Test
	public void verificationOfUseTravellerDetailsFromPNROnStep3OnlineRatesWhenPNRisAssociatedWithPaymentCard() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC07");
		reportingUtils.setSummary("Verification of use traveller details from PNR on Step3");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of use traveller details from PNR on Step3 Online rates when PNR is associated with payment card");
		reportingUtils.setInputData(PowerModeConstants.TEST_PNR_GALILEO);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_AMADEUS_UK,"");
		regressionHelper.searchLocation(PowerModeConstants.BCOM_BOOKING_TEST_HOTEL);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.chooseHotel("BCOM","");
		regressionHelper.verifyExtractTravellerInformationInStep2(PowerModeConstants.TEST_PNR_AMADEUS_UK_PAYMENT_CARD_ASSOCIATED,"Yes");
		Thread.sleep(5000);
	}
	
	@Test
	public void verificationOfUseTravellerDetailsFromPNROnStep3OnrequestRatesWhenPNRisAssociatedWithPaymentCard() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC07");
		reportingUtils.setSummary("Verification of use traveller details from PNR on Step3");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of use traveller details from PNR on Step3 on-request rated when PNR is associated with payment card");
		reportingUtils.setInputData(PowerModeConstants.TEST_PNR_GALILEO);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_AMADEUS_UK,"");
		regressionHelper.searchLocation(PowerModeConstants.BCOM_BOOKING_TEST_HOTEL);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.chooseHotel("ONREQUEST","");
		regressionHelper.verifyExtractTravellerInformationInStep2(PowerModeConstants.TEST_PNR_AMADEUS_UK,"Yes");
		
	}
	@Test(priority=9,groups={"demo"})
	public void verificationOfHotelInformation() throws Throwable
	{
		String methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC05");
		reportingUtils.setSummary("Verification of Hotel information");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName(methodName);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_CLIENT_RATE_HHE_ONREQUEST,"");
		Thread.sleep(1000);
		
		regressionHelper.searchLocation(PowerModeConstants.HOTEL_INFORMATION_TEST_HOTEL_ONLINE);
		regressionHelper.selectLocation("");
//		regressionHelper.selectCheckInCheckOutDateFixed();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.clickGetRates();
		regressionHelper.verifyhotelInformation();
//		regressionHelper.logOut(test);
		reportingUtils.setTestResult("PASS");
		
	}
	
	
	@Test(groups="demo")
	public void verificationOfUseTravellerDetailsFromPNROnStep3OnlineRatesSabrePNR() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC07");
		reportingUtils.setSummary("Verification of use traveller details from PNR on Step3");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of use traveller details from PNR on Step3 Online Rates Sabre PNR");
		reportingUtils.setInputData(PowerModeConstants.TEST_PNR_GALILEO);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_SABRE_UK,"");
		regressionHelper.searchLocation(PowerModeConstants.BCOM_BOOKING_TEST_HOTEL);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.chooseHotel("BCOM","");
		regressionHelper.verifyExtractTravellerInformationInStep2(PowerModeConstants.TEST_PNR_SABRE_UK,"");
		
	}
	@Test(groups="demo")
	public void verificationOfUseTravellerDetailsFromPNROnStep3OnrequestRatesSabrePNR() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC07");
		reportingUtils.setSummary("Verification of use traveller details from PNR on Step3");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of use traveller details from PNR on Step3 On-Request rates Sabre PNR");
		reportingUtils.setInputData(PowerModeConstants.TEST_PNR_GALILEO);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_SABRE_UK,"");
		regressionHelper.searchLocation(PowerModeConstants.BCOM_BOOKING_TEST_HOTEL);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.chooseHotel("ONREQUEST","");
		regressionHelper.verifyExtractTravellerInformationInStep2(PowerModeConstants.TEST_PNR_SABRE_UK,"");
		
	}
	@Test(groups="demo")
	public void verificationOfUseTravellerDetailsFromPNROnStep3OnlineRatesWhenPNRisNotAssociatedWithPaymentCard() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC07");
		reportingUtils.setSummary("Verification of use traveller details from PNR on Step3");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of use traveller details from PNR on Step3 Online rates when PNR is not associated with payment card");
		reportingUtils.setInputData(PowerModeConstants.TEST_PNR_GALILEO);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_SABRE_UK,"");
		regressionHelper.searchLocation(PowerModeConstants.BCOM_BOOKING_TEST_HOTEL);
		regressionHelper.selectLocation("");
//		regressionHelper.selectCheckInCheckOutDate();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.chooseHotel("BCOM","");
		regressionHelper.verifyExtractTravellerInformationInStep2(PowerModeConstants.TEST_PNR_SABRE_UK_PAYMENT_CARD_NOT_ASSOCIATED,"No");
		
	}
	
	@Test
	public void verificationOfUseTravellerDetailsFromPNROnStep3OnrequestRatesWhenPNRisNotAssociatedWithPaymentCard() throws Throwable
	{
		new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		reportingUtils.setTestCaseNumber("TC07");
		reportingUtils.setSummary("Verification of use traveller details from PNR on Step3");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName("Verification of use traveller details from PNR on Step3 on-request rates when PNR is not associated with payment card");
		reportingUtils.setInputData(PowerModeConstants.TEST_PNR_GALILEO);
		
		RegressionHelper regressionHelper = new RegressionHelper();
//		hotelBookingHelper.initializeInputData();
		regressionHelper.clickOnBookAHotel();
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_CUSTOMER_AMADEUS_UK,"");
		regressionHelper.searchLocation(PowerModeConstants.BCOM_BOOKING_TEST_HOTEL);
		regressionHelper.selectLocation("");
		regressionHelper.selectCheckInCheckOutDate();
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.chooseHotel("ONREQUEST","");
		regressionHelper.verifyExtractTravellerInformationInStep2(PowerModeConstants.TEST_PNR_AMADEUS_UK,"");
		
	}
	
}
