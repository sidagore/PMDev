package com.hotelhub.powermode.tests;

import java.text.SimpleDateFormat;

import org.testng.annotations.Test;

import com.hotelhub.powermode.helpers.LoginHelper;
import com.hotelhub.powermode.helpers.RegressionHelper;
import com.relevantcodes.extentreports.ExtentTest;

import base.Base;
import base.PowerModeConstants;

import utils.ReportingUtils;

public class HotelSearchTest extends Base {
	
//	@Test(priority=1)
	public void verifyUserIsAbleToSearchHotelByIATA() throws Throwable
	{
		LoginHelper loginHelper = new LoginHelper();
		RegressionHelper regressionHelper = new RegressionHelper();
		String methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		reportingUtils =new ReportingUtils();
		reportingUtils.setTestCaseNumber("TC01");
		reportingUtils.setSummary("Verify user is able to search hotel by IATA");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName(methodName);
//		ExtentTest test = ExtentTestManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		loginHelper.login(test);
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"");
		regressionHelper.searchLocation(PowerModeConstants.SEARCH_LOCATION_IATA);
		regressionHelper.selectLocation("IATA");
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.verifySearchResultsAreDisplayed(test);
		reportingUtils.setTestResult("PASS");
	}
	
//	@Test(priority=2)
	public void verifyUserIsAbleToSearchHotelByAddress() throws Throwable
	{
//		ExtentTest test = ExtentTestManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		LoginHelper loginHelper = new LoginHelper();
		RegressionHelper regressionHelper = new RegressionHelper();
		String methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		reportingUtils =new ReportingUtils();
		reportingUtils.setTestCaseNumber("TC02");
		reportingUtils.setSummary("Verify user is able to search hotel by Google Address");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName(methodName);
		loginHelper.login(test);
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"");
		regressionHelper.searchLocation(PowerModeConstants.SEARCH_LOCATION_GOOGLE);
		regressionHelper.selectLocation("Google");
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.verifySearchResultsAreDisplayed(test);
		reportingUtils.setTestResult("PASS");
	}
	
//	@Test(priority=3)
	public void verifyUserIsAbleToSearchHotelByHotel() throws Throwable
	{
//		ExtentTest test = ExtentTestManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		LoginHelper loginHelper = new LoginHelper();
		RegressionHelper regressionHelper = new RegressionHelper();
		String methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		reportingUtils =new ReportingUtils();
		reportingUtils.setTestCaseNumber("TC03");
		reportingUtils.setSummary("Verify user is able to search hotel by Hotel");
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setTestCaseName(methodName);
		
		loginHelper.login(test);
		regressionHelper.selectCustomerOrPNROrReferenceNumber(PowerModeConstants.TEST_PNR_SABRE,"");
		regressionHelper.searchLocation(PowerModeConstants.SEARCH_LOCATION_HOTEL);
		regressionHelper.selectLocation("Hotel");
		regressionHelper.clickOnSearchHotelsButton();
		regressionHelper.verifySearchResultsAreDisplayed(test);
		reportingUtils.setTestResult("PASS");
	}
	
}
