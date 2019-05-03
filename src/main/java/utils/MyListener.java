package utils;



	import java.io.File;
	import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;
	import com.relevantcodes.extentreports.ExtentReports;
	import com.relevantcodes.extentreports.LogStatus;
	import base.Base;

	public class MyListener extends Base implements ITestListener {
//	 protected static WebDriver driver;
//	 protected static ExtentReports reports;
//	 protected static ExtentTest test;
		

	
	 public void onTestStart(ITestResult result) {
	  System.out.println("on test start");
	  test = reports.startTest(result.getMethod().getMethodName());
	  test.log(LogStatus.INFO, result.getMethod().getMethodName() + "test is started");
	 }
	 public void onTestSuccess(ITestResult result) {
	  System.out.println("on test success");
	  test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");
	 }
	 public void onTestFailure(ITestResult result) {
	  System.out.println("on test failure");
	  test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed");
	  TakesScreenshot ts = (TakesScreenshot) driver();
	  File src = ts.getScreenshotAs(OutputType.FILE);
	  try {
		  String workingDir = System.getProperty("user.dir");
//		  String ImagePath=System.getProperty("user.dir")+"\\ExtentReports"+"\\Images\\" + result.getMethod().getMethodName() +Math.random()*100+ ".png";
	  String ImagePath="\\\\10.201.30.101\\CommonShare\\AutomationResults\\PowerMode\\ExtentReports\\ExtentReports "+new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date())+"\\Images\\" + result.getMethod().getMethodName() +Math.random()*100+ ".png"; 
//	   FileUtils.copyFile(src, new File(ImagePath));
//	   String file = test.addScreenCapture(ImagePath);
//	   FileUtils.copyFile(src, new File("C:\\images\\" + result.getMethod().getMethodName() + ".png"));
//	   String file = test.addScreenCapture("C:\\images\\" + result.getMethod().getMethodName() + ".png");
		  double randomNumber=Math.random()*10;
	   FileUtils.copyFile(src, new File("\\\\10.201.30.101\\CommonShare\\AutomationResults"+"\\ExtentReports\\Images\\" + result.getMethod().getMethodName() +randomNumber+ ".png"));
	   String file = test.addScreenCapture("\\\\10.201.30.101\\CommonShare\\AutomationResults"+"\\ExtentReports\\Images\\" + result.getMethod().getMethodName()+randomNumber + ".png");
	   test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", file);
	   test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed"+"EchoToken " +reportingUtils.getEchoToken(), result.getThrowable().getMessage());
	   test.log(LogStatus.FAIL, "EchoToken " +reportingUtils.getEchoToken());
	   System.out.println("EchoToken " +reportingUtils.getEchoToken());
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 }
	 public void onTestSkipped(ITestResult result) {
	  System.out.println("on test skipped");
	  test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");
	 }
	 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	  System.out.println("on test sucess within percentage");
	 }
	 public void onStart(ITestContext context) {
	  System.out.println("on start");
//	  driver = new ChromeDriver(); // Set the drivers path in environment variables to avoid code(System.setProperty())
	  String workingDir = System.getProperty("user.dir");
	  reports = new ExtentReports(workingDir+"\\ExtentReports\\ExtentReportResults.html");
	  
//	  reports = new ExtentReports("\\\\10.201.30.101\\CommonShare\\AutomationResults\\PowerMode\\ExtentReports\\ExtentReportResults.html");
//	  reports = new ExtentReports("\\\\10.201.30.101\\CommonShare\\AutomationResults\\PowerMode\\ExtentReports\\ExtentReports "+new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date())+"\\ExtentReportResults"+new SimpleDateFormat("yyyy.MM.dd hh-mm-ss").format(new java.util.Date())+".html");

	 }
	 public void onFinish(ITestContext context) {
	System.out.println("on finish");
  	
  	
//	  driver.close();
//	  reports.endTest(test);
//	  reports.flush();
	 }
	}


