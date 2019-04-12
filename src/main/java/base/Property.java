package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {
	
	static Properties ConfigProps = new Properties();
	String strValue;
	
	public String getProperty(String strKey) 
	{
		try
		{
			File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
			FileInputStream fi = new FileInputStream(file);
			ConfigProps.load(fi);
			strValue=ConfigProps.getProperty(strKey);
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return strValue;
		
	}
	
	

}
