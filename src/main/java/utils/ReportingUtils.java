package utils;

import java.util.ArrayList;

import base.Base;

public class ReportingUtils extends Base {
	
	
	private String testCaseNumber="";
	private String summary="";
	private String testCaseName= "";
	private String inputData= "";
	public String outPutData="";
	private String executionTime= "";
	private String testResult= "FAIL";
	private String echoToken= "";
	private String confirmationNumber="";
	private String bookingReferenceNumber="";
	private String hhbLine="";
	private String checkInDate="";
	private String travellerName="";
	
	
	
	public String getTravellerName() {
		return travellerName;
	}

	public void setTravellerName(String travellerName) {
		this.travellerName = travellerName;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	private String checkOutDate="";
	
	
	public String getHhbLine() {
		return hhbLine;
	}

	public void setHhbLine(String hhbLine) {
		this.hhbLine = hhbLine;
	}

	public String getBookingReferenceNumber() {
		return bookingReferenceNumber;
	}

	public void setBookingReferenceNumber(String bookingReferenceNumber) {
		this.bookingReferenceNumber = bookingReferenceNumber;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public String getEchoToken() {
		return echoToken;
	}

	public void setEchoToken(String echoToken) {
		this.echoToken = echoToken;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public String getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(String executionTime) {
		this.executionTime = executionTime;
	}

	public String getTestCaseNumber() {
		return testCaseNumber;
	}

	public void setTestCaseNumber(String testCaseNumber) {
		this.testCaseNumber = testCaseNumber;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getOutPutData() {
		return outPutData;
	}

	public void setOutPutData(String outPutData) {
		this.outPutData = this.outPutData+"\n"+outPutData;
	}

	public String getInputData() {
		return inputData;
	}

	public void setInputData(String inputData) {
		this.inputData = this.inputData+" "+inputData;
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}
	
	

}
