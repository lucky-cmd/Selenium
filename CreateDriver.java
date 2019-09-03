package com.selenium.config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.selenium.utils.DataHandler;


public class CreateDriver 
{
	public static WebDriver getBrowserInstance() throws Exception
	{
		WebDriver driver=null;
		
		String browserinfo=DataHandler.readFromProperties("./config-data/configuration.properties", "browser");
		System.out.println(browserinfo);
		
		String url=DataHandler.readFromProperties("./config-data/configuration.properties", "url");
		System.out.println(url);
		
		if(browserinfo.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browserinfo.equalsIgnoreCase("gecko"))
		{
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		if(url.equalsIgnoreCase("prod"))
		{
			driver.get(DataHandler.readFromProperties("./config-data/configuration.properties", "prod"));
			
		}
		
		else
		{
			driver.get(DataHandler.readFromProperties("./config-data/configuration.properties", "test"));
			
		}
		
		return driver;
		
		
	}

}
