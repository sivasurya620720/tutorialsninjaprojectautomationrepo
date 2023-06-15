package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.Reports;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener{

	ExtentReports reports;
	ExtentTest extenttest;
	String testname;
	@Override
	public void onStart(ITestContext context) {
		
		try {
			reports= Reports.generateExtentReport();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		testname = result.getName();
		extenttest = reports.createTest(testname);
		extenttest.log(Status.INFO, testname + "test has been started");	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extenttest.log(Status.PASS, testname + "executed successfully");
		System.out.println(testname + " got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver=null;
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		extenttest.addScreenCaptureFromPath(Utilities.takescreenshot(driver,testname));
		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.FAIL, testname + " got failed");		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.SKIP, testname + " test has been skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentreport.html";  
		File extentrep= new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentrep.toURI());
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
