package com.tutorialsninja.qa.Testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Baseclass;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterAccountTest extends Baseclass{
	
	public WebDriver driver;
	RegisterPage regpage;
	public RegisterAccountTest() {
		super();
	}
		
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenAppUrl(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickMyAccount();
		regpage= homepage.selectRegisterOption();
	}
	
	@Test(priority=1)
	public void verifyRegisterWithMandatoryFields() {
		
		regpage.enterFirstName(dataprop.getProperty("firstname"));
		regpage.enterLastName(dataprop.getProperty("lastname"));
		regpage.enterEmail(Utilities.generateEmailIDWithTimestamp());
		regpage.enterTelephone(dataprop.getProperty("telephone"));
		regpage.enterPwd(dataprop.getProperty("password"));
		regpage.confirmPwd(dataprop.getProperty("password"));
		regpage.checkAgree();
		regpage.clickContinueButton();
		
		String actualSuccessHeading=regpage.retrieveSuccessHeadingMessage();
		Assert.assertEquals(actualSuccessHeading, dataprop.getProperty("expectedSuccessHeading"),"Error creating the account");
	}
	
	@Test(priority=2)
	public void verifyRegisterWithAllFields() {
		
		regpage.enterFirstName(dataprop.getProperty("firstname"));
		regpage.enterLastName(dataprop.getProperty("lastname"));
		regpage.enterEmail(Utilities.generateEmailIDWithTimestamp());
		regpage.enterTelephone(dataprop.getProperty("telephone"));
		regpage.enterPwd(dataprop.getProperty("password"));
		regpage.confirmPwd(dataprop.getProperty("password"));
		
		regpage.selectSubscribeOption();
		regpage.checkAgree();
		regpage.clickContinueButton();
		
		String actualSuccessHeading=regpage.retrieveSuccessHeadingMessage();
		Assert.assertEquals(actualSuccessHeading, dataprop.getProperty("expectedSuccessHeading"),"Error creating the account");
			
	}

	@Test(priority=3)
	public void verifyRegisterWithExistingEmail() {
		
		regpage.enterFirstName(dataprop.getProperty("firstname"));
		regpage.enterLastName(dataprop.getProperty("lastname"));
		regpage.enterEmail(prop.getProperty("mainvalidusername"));
		regpage.enterTelephone(dataprop.getProperty("telephone"));
		regpage.enterPwd(dataprop.getProperty("password"));
		regpage.confirmPwd(dataprop.getProperty("password"));
		
		regpage.selectSubscribeOption();
		regpage.checkAgree();
		regpage.clickContinueButton();
		
		String actualWarningMessage=regpage.duplicateEmailWarningMessage();
		Assert.assertTrue(actualWarningMessage.trim().equals(dataprop.getProperty("duplicateEmailWarning")),"Expected Warning message is not displayed");
			
	}
	
	@Test(priority=4)
	public void verifyRegisterWithoutFillingAnyDetails() {
		regpage.clickContinueButton();
		
		String actualPrivacyPolicyWarning = regpage.actualPrivacyPolicyWarning();
		String actualFirstNameWarning = regpage.actualFirstNameWarningMessage(); 
		String actualLastNameWarning = regpage.actualLastNameWarningMessage();
		String actualEmailWarning = regpage.actualEmailWarningMessage();
		String actualTelephoneWarning = regpage.actualTelepohoneWarningMessage();
		String actualPasswordWarning = regpage.actualPasswordWarningMessage();
		
		Assert.assertTrue(actualPrivacyPolicyWarning.trim().contains(dataprop.getProperty("privacyPolicyWarning")), "Privacy Warning message is not displayed");
		Assert.assertEquals(actualFirstNameWarning.trim(), dataprop.getProperty("noFirstNameWarning"), "Firstname warning message is not displayed");
		Assert.assertEquals(actualLastNameWarning.trim(), dataprop.getProperty("noLastNameWarning"), "Lastname warning message is not displayed");
		Assert.assertEquals(actualEmailWarning.trim(), dataprop.getProperty("noOrInvalidEmailWarning"), "Email warning message is not displayed");
		Assert.assertEquals(actualTelephoneWarning .trim(), dataprop.getProperty("noOrOInvalidTelephone"), "Telephone warning message is not displayed");
		Assert.assertEquals(actualPasswordWarning.trim(), dataprop.getProperty("noOrInvalidpassword"), "Password warning message is not displayed");
	}

}
