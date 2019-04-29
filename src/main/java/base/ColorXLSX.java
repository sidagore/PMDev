package base;

//
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
//public class ColorXLSX {
//	public static void main(String[] args) throws IOException {
//	    Workbook workbook = new XSSFWorkbook();
//	    Sheet sheet = workbook.createSheet("Color Test");
//	    Row row = sheet.createRow(0);
//
//	    CellStyle style = workbook.createCellStyle();
//	    style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
//	    style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
//	    Font font = workbook.createFont();
//            font.setColor(IndexedColors.RED.getIndex());
//            style.setFont(font);
//        
//	    Cell cell1 = row.createCell(0);
//	    cell1.setCellValue("ID");
//	    cell1.setCellStyle(style);
//	    
//	    Cell cell2 = row.createCell(1);
//	    cell2.setCellValue("NAME");
//	    cell2.setCellStyle(style);
//
//	    FileOutputStream fos =new FileOutputStream(new File("D:/Book1.xlsx"));
//	    workbook.write(fos);
//	    fos.close();
//	    System.out.println("Done");
//	}
//}  
import org.testng.annotations.Test;

//import java.io.FileNotFoundException;  
//import java.io.FileOutputStream;  
//import java.io.IOException;  
//import java.io.OutputStream;  
//import org.apache.poi.ss.usermodel.Cell;  
//import org.apache.poi.ss.usermodel.CellStyle;  
//import org.apache.poi.ss.usermodel.FillPatternType;  
//import org.apache.poi.ss.usermodel.IndexedColors;  
//import org.apache.poi.ss.usermodel.Row;  
//import org.apache.poi.ss.usermodel.Sheet;  
//import org.apache.poi.ss.usermodel.Workbook;  
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
//public class ColorXLSX {  
//   public static void main(String[] args) throws FileNotFoundException, IOException {  
//       try (OutputStream fileOut = new FileOutputStream("D:\\Book1.xlsx")) {  
//           Workbook wb = new XSSFWorkbook();  
//           Sheet sheet = wb.createSheet("Sheet");  
//           Row row = sheet.createRow(1);  
//           CellStyle style = wb.createCellStyle();  
//           // Setting Background color  
//           style.setFillBackgroundColor(IndexedColors.RED.getIndex());  
//           style.setFillPattern(FillPatternType.BIG_SPOTS);  
//           Cell cell = row.createCell(1);  
//           cell.setCellValue("Javatpoint");  
//           cell.setCellStyle(style);  
//           // Setting Foreground Color  
//           style = wb.createCellStyle();  
//           style.setFillForegroundColor(IndexedColors.BLUE.getIndex());  
//           style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
//           cell = row.createCell(2);  
//           cell.setCellValue("A Technical Portal");  
//           cell.setCellStyle(style);  
//           wb.write(fileOut);  
//           }catch(Exception e) {  
//               System.out.println(e.getMessage());  
//           }  
//   }  
//}  






import java.io.FileInputStream;
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.CellStyle;  
import org.apache.poi.ss.usermodel.FillPatternType;  
import org.apache.poi.ss.usermodel.IndexedColors;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
public class ColorXLSX {  
	
	
	
	
   public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {  
      
	   //find sum of all digits
	   
	   //find duplicate elements in an array

	   //find duplicate characters in string.
	   
	   //Reverse sentence word by word
	   
	

	//find largest number less than given number and without given number
	   
	   //frequency of all digits in a number
	  
//	   keep – peek

	   
//	   String s1="Keep";
//	   String s2="Peek";
//	   
//	   s1=s1+s2;//KeepPeek
//	   s2= s1.substring(0,s1.length()-s2.length());
//	   
//	   
	   
//	   int[] array = {10,70,20,30,40,50};
//	   
//	   int firstLargest=0;
//	   int secondLargest=0;
//	   
//	   if(array[0]>array[1])
//	   {
//		   firstLargest=array[0];
//		   secondLargest=array[1];
//	   }else 
//	   {
//		   firstLargest=array[1];
//		   secondLargest=array[0];
//	   }
//	   
//	   for(int i=2;i<array.length;i++)
//	   {
//		   if(array[i]>firstLargest)
//		   {
//			   secondLargest = firstLargest;
//			   firstLargest=array[i];
//			   
//		   }
//		   else if(array[i]<firstLargest&&array[i]>secondLargest)
//		   {
//			   secondLargest=array[i];
//		   }
//	   }
//	   
//	   System.out.println("first Largest "+firstLargest);
//	   System.out.println("Second Largest "+secondLargest);
	   
	   
	   
//	   String s="Shiva Prasad";
//	   
//	   char[] array=s.toCharArray();
//	   HashMap<Character,Integer> map = new HashMap<Character,Integer>();
//	   
//	   for(Character var:array)
//	   {
//		   if(map.containsKey(var))
//		   {
//			   map.put(var, map.get(var)+1);
//		   }
//		   else
//			   map.put(var, 1);
//	   }
//	   
//	   Set<Character> set = map.keySet();
//	   
//	   for(Character var:set)
//	   {
//		   if(map.get(var)>1)
//		   {
//			   System.out.println(var +" "+map.get(var));
//		   }
//	   }
	   
	   
	   
	   
	   
	   
//	     1
//	    2 2
//	   3 3 3
//	  4 4 4 4
//	   3 3 3
//	    2 2
//	     1
//
//	   
//	   
//	   int n=4;
//	   for(int i=1;i<=n;i++)
//	   {
//		   for(int j=n;j>i;j--)
//		   {
//			   System.out.print(" ");
//		   }
//		   for(int k=1;k<=i;k++)
//		   {
//			   System.out.print(" "+i);
//		   }
//		   System.out.println();
//	   }
//	   for(int i=n-1;i>=1;i--)
//	   {
//		   for(int j=i;j<n;j++)
//		   {
//			   System.out.print(" ");
//		   }
//		   for(int k=1;k<=i;k++)
//		   {
//			   System.out.print(" "+i);
//		   }
//		   System.out.println();
//	   }
	   
	  
	   
	   
//	   int n=12345;
//	   int sum=0;
//	   
//	   while(n!=0)
//	   {
//		   int lastDigit=n%10;
//		   sum=sum+lastDigit;
//		   n=n/10;
//		   
//	   }
//	   System.out.println("Sum of all digits "+sum);
	   
	   
//	   ArmStrong Program
//	 int n=153;
//	 
//	 int numberOfDigits=String.valueOf(n).length();
//	 int sum=0;
//	 while(n!=0)
//	 {
//		 int lastDigit=n%10;
//		
//		 int sumofPower=1;
//		 for(int i=1;i<=numberOfDigits;i++)
//		 {
//			 sumofPower=sumofPower*lastDigit;
//			 
//		 }
//		 sum=sum+sumofPower;
//		 n=n/10;
//	 }
//	 if(sum==n)
//	 {
//		 System.out.println("Number is Armstrong ");
//	 }
//	 else
//	 {
//		 System.out.println("Number is not ArmStrong ");
//	 }
	
	   
//	   FIZZBUZZ Program
//	  
//	   for(int i=1;i<=100;i++)
//	   {
//		   if(i%3==0&&i%5!=0)
//		   {
//			   System.out.println("FIZZ");
//		   }
//		   else if(i%5==0&&i%3!=0)
//		   {
//			   System.out.println("BUZZ");
//		   }
//		   else if(i%5==0&&i%3==0)
//		   {
//			   System.out.println("FIZZBUZZ");  
//		   }
//		   else
//		   {
//			   System.out.println(i);
//		   }
//
//		   
//	   }
//	   
//	   
//	   
//	   
//	   String s1="shiva";
//	   String s2="prasad";
//	   
//	   s1=s1+s2;
//	   s2=s1.substring(0,s1.length()-s2.length());
//	   s1=s1.substring(s2.length());
//	   System.out.println("s1 "+s1);
//	   System.out.println("s2 "+s2);
//	   
//	   int i=10;
//	   int j=20;
//	   
//	   i=i+j;
//	   j=i-j;
//	   i=i-j;
//	   
//	   System.out.println("Value of i "+i);
//	   System.out.println("Value of j "+j);
	   
	   
	   
	   
//	   int x=70;
//	   int y=20;
//			   
//	   int diff1=100-x;
//	   int diff2=100-y;
//	   
//	   if(diff1>diff2)
//		   System.out.println("Nearest to 100 is "+y);
//	   else if(diff1<diff2)
//		   System.out.println("Nearest to 100 is "+x);
//	   else
//		   System.out.println("both the numbers are equal");
//			   
//	   
//	   https://mail.google.com/mail/u/0/#inbox
//	   System.out.println("2");
	   
	   
	   
	   
	   
	   
//	   String s="12-July-2019";
//	   SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
//	   Date date = sdf.parse(s);
//	   SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
//	   String newDate=sdf1.format(date);
//	   System.out.println(newDate);
	   
	   
//	   --find given number is binary
	   /*
	   int i = 1010101;
	   boolean isBinary=true;
	   while(i!=0)
	   {
		   int lastDigit = i%10;
		   if(lastDigit>1)
			   isBinary=false;
		   i=1/10;
	   }
	   if(isBinary)
		   System.out.println("Given number is binary");
	   else
		   System.out.println("Givem number is not binary");
		   */
	   
	   // find the largest 

	  
	  
	   
	   
//	   int firstTerm=0;
//	   int secondTerm=1;
//	   int thirdTerm=0;
//	   
//	   int i=0;
//	   while(i<20)
//	   {
//		   thirdTerm=firstTerm+secondTerm;
//		   firstTerm=secondTerm;
//		   secondTerm=thirdTerm;
//		   i++;
//		   System.out.println(thirdTerm);
//	   }
	   
//	   int a=15;
//	   int b=5;
//	   if((a-b)>=2)
//	   {
//		   System.out.println("a");
//	   }
//	   else if((b-a)>=2)
//	   {
//		   System.out.println("b");
//	   }
//	   else
//	   {
//		   System.out.println("Inconclusive");
//	   }
	   
	   
	   //first 50 prime numbers
	   
	   int input=1 ;
	   
	   for(int i=2;i<input;i++)
	   {
		   
		   if(input%i==0)
		   {
			   System.out.println("Given number is not prime number");
		   }
		   
		   
	   }
	   
	   
	   
	   
	   
	   
	   //implicit wait
	   
	   
	   
//	   int n=153;
//	   int sum=0;
//	   int sumOfLastDigit=1;
//	   int numberOfDigits=String.valueOf(n).length();
//	   
//	   while(n!=0)
//	   {
//		   int lastDigit = n%10;
//		   for(int i=0;i<numberOfDigits;i++)
//		   {
//			   
//			   sumOfLastDigit=sumOfLastDigit*lastDigit;
//		   }
//		   sum=sum+sumOfLastDigit;
//		   sumOfLastDigit=1;
//		   n=n/10;
//	   }
//	   System.out.println("Sum of 153 "+sum);
//	   
	   
	   
	   
	   
	   int randomDay = (int)(Math.random()*(20-10))+10;
	   System.out.println(randomDay);
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	    
	   
	   
	   
	   
	  
	   
	
	   
   }
   
//   @DataProvider
//   public String[][] datap()
//   {
//	 String[][] array={{"1"},{"2"}};
//	 return array;
//   }
//   
//   
//   @Test(dataProvider="datap")
//   public void data(String data)
//   {
//	   System.out.println(data);
//	   
//   }
   
   
  

   
   
   
}  


