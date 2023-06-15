package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reports {
	public static ExtentReports generateExtentReport() throws IOException
	{
		ExtentReports extentreport = new ExtentReports();
		File repfile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentreport.html");
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(repfile);
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setReportName("TutorialsNinja Test Automation results");
		sparkreporter.config().setDocumentTitle("Test Automation Report");
		sparkreporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentreport.attachReporter(sparkreporter);
		
		Properties configprop = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fis = new FileInputStream(configPropFile);
		configprop.load(fis);
		
		extentreport.setSystemInfo("Application URL", configprop.getProperty("url"));
		extentreport.setSystemInfo("Browser name", configprop.getProperty("browser"));
		extentreport.setSystemInfo("Email", configprop.getProperty("mainvalidusername"));
		
		extentreport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentreport.setSystemInfo("Java version", System.getProperty("java.version"));
		
		return extentreport;
	
	}

}
