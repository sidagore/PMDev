package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import jcifs.smb.NtlmPasswordAuthentication;
import utils.ReportingUtils;


public class Base {
//	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports reports;
	public static ExtentTest test;
//	public ExtentHtmlReporter html;
	static double count=0;
	
//	String newDir = "C://Screenshots//"+new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date());
	
	public Property props = new Property();
	
	public static ReportingUtils reportingUtils;
	public String imagePath=props.getProperty("imagePath");
	public String reportPath=props.getProperty("reportPath");
	    public static final ThreadLocal<RemoteWebDriver> drivers = new ThreadLocal();

//	    @BeforeSuite
//	    public void beforeSuite() {
//	        reports = ExtentManager.getReporter();
//	    }
	   
	    @BeforeMethod(alwaysRun=true)
	    public void instantiateBrowser(ITestResult testResult) throws IOException {
	    	
//	    	System.setProperty("webdriver.chrome.driver","C://Users//sprasad//Downloads//chromedriver.exe");
	    	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	    	RemoteWebDriver driver = new ChromeDriver();
	    	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
//	    	driver.manage().window().setSize(new Dimension(1044,784));
	    	driver.manage().window().maximize();
	    	drivers.set(driver);
	    	
	    }
	    
	    
	    

//	    @Test(dataProvider = "dp")
//	    public void testMethod(String url) {
//	        Reporter.log("Launching the URL [" + url + "] on Thread [" + Thread.currentThread().getId() + "]", true);
//	        driver().get(url);
//	        Reporter.log("Page Title :" + driver().getTitle(), true);
//	    }
//
//	    @DataProvider(name = "dp", parallel = true)
//	    public Object[][] getData() {
//	        return new Object[][]{
//	                {"http://www.google.com"}, {"http://www.stackoverflow.com"}, {"http://facebook.com"}
//	        };
//	    }
	    
	   
	    @AfterMethod(alwaysRun=true)
		public static void writeToExcel() throws IOException
		{
	    	Property props = new Property();
	    	ZipSecureFile.setMinInflateRatio(0);
	    	FileInputStream fi=new FileInputStream(props.getProperty("OutputFileLocation"));
	    	
			Workbook workbook = WorkbookFactory.create(fi);
			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
			CellStyle style1= workbook.createCellStyle();
			CellStyle style2= workbook.createCellStyle();
//	        style.setFillBackgroundColor(IndexedColors.BROWN.getIndex()); 
			style1.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style2.setFillForegroundColor(IndexedColors.RED.getIndex());
			style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
			style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
			Row newRow = sheet.createRow(rowCount+1);
//			style.setFillBackgroundColor((IndexedColors.BRIGHT_GREEN.getIndex()));
			
			
			Cell cell0 = newRow.createCell(0);
			cell0.setCellValue(reportingUtils.getTestCaseNumber());
			Cell cell1 = newRow.createCell(1);
			cell1.setCellValue(reportingUtils.getSummary());
			Cell cell2 = newRow.createCell(2);
			cell2.setCellValue(reportingUtils.getTestCaseName());
			Cell cell3 = newRow.createCell(3);
			cell3.setCellValue(reportingUtils.getInputData());
			Cell cell4 = newRow.createCell(4);
			cell4.setCellValue(reportingUtils.getOutPutData());
			Cell cell5 = newRow.createCell(5);
			cell5.setCellValue(reportingUtils.getEchoToken());
			Cell cell6 = newRow.createCell(6);
			cell6.setCellValue(reportingUtils.getExecutionTime());
			Cell cell7 = newRow.createCell(7);
			cell7.setCellValue(reportingUtils.getTestResult());
			
			String cell7Value= cell7.getStringCellValue();
			
			cell7.setCellStyle(style1);
			
			if(cell7Value.contains("FAIL"))
				cell7.setCellStyle(style2);	
			fi.close();
			FileOutputStream fo = new FileOutputStream(props.getProperty("OutputFileLocation"));
			workbook.write(fo);
			workbook.close();
			fo.close();
//			Row row = sheet.getRow(0);
//			Cell cell = row.getCell(0);
//			cell.setCellValue(data);
		}
	    
	    @AfterMethod(alwaysRun=true)
	    public void cleanupBrowser() throws IOException {
//	    	reports.endTest(test);
	    	reports.flush();
//	         driver().get(System.getProperty("user.dir")+"/test-output/ExtentReportResults.html");
//	         driver().findElement(By.xpath("//*[@id='enableDashboard']/i")).click();
//	         driver().findElement(By.xpath("html/body/nav/ul[2]/li[1]")).click();
//	         TakesScreenshot ts1 = (TakesScreenshot) driver();
//				File src = ts1.getScreenshotAs(OutputType.FILE);
//			File file = new File("P:\\AutomationResults\\PowerMode\\ExtentReports 2019.01.21")	
					
//	    	ExtentReports reports = reports. 
//				FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/test-output/ExtentReportResultsImage"+".png"));
//	    	File scr1 = new File("\\\\10.201.30.101\\CommonShare\\AutomationResults\\PowerMode\\ExtentReports "+new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date())+"\\ExtentReportResults"+new SimpleDateFormat("yyyy.MM.dd hh-mm-ss").format(new java.util.Date())+".html");
//	    	File scr2 = new File( System.getProperty("user.dir")+"\\ExtentReports\\ExtentReportResults.html");
//	    	File scr3 = new File("\\\\10.201.30.101\\CommonShare\\AutomationResults\\PowerMode\\ExtentReports "+new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date())+"\\Images\\");
//	    	File scr4 = new File(System.getProperty("user.dir")+"\\ExtentReports\\Images\\");
//	    	FileUtils.copyDirectory(scr4,scr3);
	        RemoteWebDriver driver = driver();
	        
	        driver.quit();
	    }
	    
	    @AfterTest(alwaysRun=true)
	    public void copyExtent() throws IOException
	    {
	    	File scr1 = new File("D:\\TCT\\PowerMode_ToChekIn\\PowerMode\\ExtentReports\\ExtentReportResults.html");
	      	File scr2 = new File("\\\\10.201.30.101\\CommonShare\\AutomationResults\\PowerMode\\ExtentReports\\ExtentReports "+new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date())+"\\ExtentReportResults"+new SimpleDateFormat("yyyy.MM.dd hh-mm-ss").format(new java.util.Date())+".html");
	      	FileUtils.copyFile(scr1,scr2);
	      	System.out.println("File Copied");
	      	
//	      	driver().get("file:///D:/TCT/PowerMode_ToChekIn/PowerMode/ExtentReports/ExtentReportResults.html");
	    }

	    public RemoteWebDriver driver() {
	        RemoteWebDriver driver = drivers.get();
	        if (driver == null) {
	            throw new IllegalStateException("Driver should have not been null.");
	        }
	        return driver;
	    }

	    public void PrintInfo(String info)
	    {
	    	test.log(LogStatus.INFO, "<b style='color:#1976d2;Font-size:14px;font-family: verdana'>"+info + "<b>");
	    }
	    
	    
	    public void ScreenShot(String stepName,String logStatus, ExtentTest test ) throws InterruptedException
		{
//<<<<<<< .mine
//			count=count+1;
			count=Math.random()*100;
			String ImagePath="\\\\10.201.30.101\\CommonShare\\AutomationResults\\PowerMode\\ExtentReports\\ExtentReports "+new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date())+"\\Images\\";
//			String ImagePath=System.getProperty("user.dir")+"\\ExtentReports\\Images\\"+ stepName+count + ".png";
			

			

//	    	count=Math.random()*100;
//			String ImagePath="\\\\10.201.30.101\\CommonShare\\AutomationResults"+"\\ExtentReports\\Images\\"+ stepName+count + ".png";
//>>>>>>> .r14318
			TakesScreenshot ts = (TakesScreenshot) driver();
			NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication("domain", "username", "password");
			File src = ts.getScreenshotAs(OutputType.FILE);
			  try {
//<<<<<<< .mine
			FileUtils.copyFile(src, new File(ImagePath+ stepName+count + ".png"));
			String file = test.addScreenCapture(ImagePath+ stepName+count + ".png");
//||||||| .r13974
//			FileUtils.copyFile(src, new File("C://images//" + stepName+count + ".png"));
//			String file = test.addScreenCapture("C://images//" + stepName+count + ".png");
			
			if(logStatus.toLowerCase().equals("info"))
			{
				test.log(LogStatus.INFO, file);
				test.log(LogStatus.INFO, "<b style='color:#1976d2;Font-size:14px;font-family: verdana'>"+stepName + "<b>");
			  }
			  
			else if(logStatus.toLowerCase().equals("pass"))
			  {
				test.log(LogStatus.PASS, file);
				test.log(LogStatus.PASS, "<b style='color:#32cd32;Font-size:14px;font-family: verdana'>"+stepName + "<b>");
					  }
			else if(logStatus.toLowerCase().equals("fail"))
			  {
				test.log(LogStatus.FAIL, file);
				test.log(LogStatus.FAIL, "<b style='color:#1976d2;Font-size:14px;font-family: verdana'>"+stepName + "<b>");
					  }
			  
			  }
			  catch (IOException e) {
			   e.printStackTrace();
			  }
		}
	    
	    
	  
	    
	}

	

