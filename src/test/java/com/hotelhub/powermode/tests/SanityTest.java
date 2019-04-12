package com.hotelhub.powermode.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hotelhub.powermode.helpers.LoginHelper;
import com.relevantcodes.extentreports.ExtentTest;

import base.Base;
import base.Property;

import utils.ReportingUtils;


public class SanityTest extends Base {


	
	public static String[][] readinput() throws IOException
	{
		
		FileInputStream fi = new FileInputStream("D:\\TCT\\input.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fi);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int totalNoOfRows = sheet.getLastRowNum();
		int count=0;
		for(int k=0;k<totalNoOfRows+1;k++)
		{
			if(sheet.getRow(k).getCell(3).toString().toLowerCase().equals("yes"))
			{
				count=count+1;
			}
			
		}
	
		String[][] arrayExcel = new String[count][2];
		String[][] arrayExcelData = new String[totalNoOfRows][2];
		for (int i= 0 ; i < totalNoOfRows; i++) {
			
			if(sheet.getRow(i+1).getCell(3).toString().toLowerCase().equals("yes"))
			{
				for (int j=0; j < 3; j++) {
					arrayExcel[i][j] = sheet.getRow(i+1).getCell(j).toString();
					
				}
			}
		
		}
		return arrayExcel;
	}
//	,parallel=true
	@DataProvider(name="testData")
	
	public String[][] input() throws IOException
	{
		return SanityTest.readinput();
	}
	

	
	
	
	
//	@Test(dataProvider="testData")
	public void CreateBookingWithDifferentGDS(String PNR, String GDS) throws Throwable
	{
//		ExtentTest test = ExtentTestManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		reportingUtils =new ReportingUtils();
		Property props = new Property();
		String Url=props.getProperty("Url");
		String UserName=props.getProperty("UserName");
		String PassWord=props.getProperty("PassWord");
		reportingUtils.setTestCaseNumber("TC01");
		reportingUtils.setSummary("Creating booking");
		String methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		reportingUtils.setExecutionTime(timeStamp);
		reportingUtils.setSummary("Creating booking");
		reportingUtils.setTestCaseName(methodName);
		LoginHelper loginHelper = new LoginHelper();
		reportingUtils =new ReportingUtils();
//		ExtentTestManager.endTest();
	}
	
//	@Test(dataProvider="testData")
	void CreateBooking(String PNR, String GDS, String Hotel) throws Throwable
	{
//		LoginHelper loginHelper= new LoginHelper();
//		HotelBookingHelper hotelBookingHelper = new HotelBookingHelper();
//		loginHelper.login();
//		hotelBookingHelper.enterCustomerOrPNROrReferenceNumber();
		
		System.out.println("PNR "+PNR);
		System.out.println("GDS "+GDS);
		System.out.println("Hotel "+Hotel);
	}
	
	
}
