package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Baseclass {
	
	public Properties prop;
	public Properties dataprop;
	WebDriver driver;
	
	public Baseclass() {
		
		prop = new Properties();
		dataprop = new Properties();

		File testdatafile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		try {
		FileInputStream fisdata = new FileInputStream(testdatafile);
		dataprop.load(fisdata);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		File propfile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(propfile);
			prop.load(fis);	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public WebDriver initializeBrowserAndOpenAppUrl(String browserName) {
		
		switch(browserName.toLowerCase()) {
		
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}
