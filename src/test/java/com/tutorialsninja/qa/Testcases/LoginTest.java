package com.tutorialsninja.qa.Testcases;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Baseclass;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.LoginPage;
import com.tutorialsninja.qa.pageobjects.MyAccountPage;
import com.tutorialsninja.qa.utils.Utilities;


public class LoginTest extends Baseclass {
	
	public WebDriver driver;
	Random rand = new Random();
	LoginPage loginpage;
	MyAccountPage myaccountpage;
	
	public LoginTest() {
		super();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenAppUrl(prop.getProperty("browser"));
		HomePage homepage= new HomePage(driver);
		homepage.clickMyAccount();
		loginpage= homepage.selectLoginOption();
	}
	
	@DataProvider(name = "validCredsSupplier")
	public Object[][] supplyTestData() throws Exception
	{
		Object data[][] = Utilities.getTestDataFromExcel("Login");
		return data;
		
	}
	
	@Test(priority=1, dataProvider="validCredsSupplier")
	public void verifyLoginWithValidCredsUsingDataProvider(String Validemail, String Validpassword) throws InterruptedException {

		loginpage.enterUsername(Validemail);
		loginpage.enterPassword(Validpassword);
		myaccountpage= loginpage.clickOnLoginButton();
		
		Assert.assertTrue(myaccountpage.getStatusOfEditYourAccountInfo(),"Please check your credentials");
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCreds() {
		
		loginpage.enterUsername(Utilities.generateEmailIDWithTimestamp());
		loginpage.enterPassword(dataprop.getProperty("invalidpasswordforlogin"));
		loginpage.clickOnLoginButton();
		
		String warningmessage = loginpage.retrieveEmailPwdNotMatchWarnMessage();
		String expectedwarningmessage=dataprop.getProperty("emailPasswordNoMatchWarningMessage");
		Assert.assertTrue(warningmessage.trim().contains(expectedwarningmessage.trim()), "Expected Warning message is displayed");
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailValidpassword() {
		
		loginpage.enterUsername(Utilities.generateEmailIDWithTimestamp());
		loginpage.enterPassword(prop.getProperty("mainvalidpassword"));
		loginpage.clickOnLoginButton();
		
		String warningmessage = loginpage.retrieveEmailPwdNotMatchWarnMessage();
		String expectedwarningmessage = dataprop.getProperty("emailPasswordNoMatchWarningMessage");
		Assert.assertTrue(warningmessage.trim().contains(expectedwarningmessage.trim()), "Expected Warning message is displayed");
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailInvalidpassword() {
		
		loginpage.enterUsername(prop.getProperty("mainvalidusername"));
		loginpage.enterPassword("12345" + rand.nextInt(99));
		loginpage.clickOnLoginButton();
		
		String warningmessage = loginpage.retrieveEmailPwdNotMatchWarnMessage();
		String expectedwarningmessage = dataprop.getProperty("emailPasswordNoMatchWarningMessage");
		Assert.assertTrue(warningmessage.trim().contains(expectedwarningmessage.trim()), "Expected Warning message is displayed");
	}
	
	@Test(priority=5)
	public void verifyLoginWithNoCreds() {
		
		loginpage.clickOnLoginButton();
		
		String warningmessage = loginpage.retrieveEmailPwdNotMatchWarnMessage();
		String expectedwarningmessage = dataprop.getProperty("emailPasswordNoMatchWarningMessage");
		Assert.assertTrue(warningmessage.trim().contains(expectedwarningmessage.trim()), "Expected Warning message is displayed");
	}
	
	
	
	
}