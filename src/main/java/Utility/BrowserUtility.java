package Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserUtility
{
	public static WebDriver OpenBrowser(WebDriver driver,String browserName,String url) throws InterruptedException
	{
		if(browserName.equals("Chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(url);
			Thread.sleep(5000);
			return driver;	
		}else 
		if(browserName.equals("edge"))
		{
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(url);
			return driver;
		} else if(browserName.equals("Firefox"))
		{
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(url);
			return driver;	
		}
			return null;			
	}

}
