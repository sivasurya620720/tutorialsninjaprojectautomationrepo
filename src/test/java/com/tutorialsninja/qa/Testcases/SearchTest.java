package com.tutorialsninja.qa.Testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Baseclass;
import com.tutorialsninja.qa.pageobjects.SearchAndResultsPage;

public class SearchTest extends Baseclass{
	//Added a comment 15/06
	public WebDriver driver;
	
	public SearchTest() {
		super();
	}
		
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenAppUrl(prop.getProperty("browser"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void searchWithExistingProduct() {
		SearchAndResultsPage searchres= new SearchAndResultsPage(driver);
		searchres.enterProductNameIntoSearchField(dataprop.getProperty("validProduct"));
		searchres.clickSearchButtonForresults();
		
		Assert.assertTrue(searchres.displayStatusOfHPValidProduct());
	}
	@Test(priority=2)
	public void searchWithNonExistingProduct() {
		SearchAndResultsPage searchres= new SearchAndResultsPage(driver);
		searchres.enterProductNameIntoSearchField(dataprop.getProperty("invalidProduct"));
		searchres.clickSearchButtonForresults();
		
		Assert.assertEquals(searchres.getInvalidProductSearchMessage(), dataprop.getProperty("DProductMessage"));		
	}
	@Test(priority=3, dependsOnMethods = {"searchWithNonExistingProduct"})
	public void searchWithoutProvidingProductName() {

		SearchAndResultsPage searchres= new SearchAndResultsPage(driver);
		searchres.clickSearchButtonForresults();
		
		Assert.assertEquals(searchres.getInvalidProductSearchMessage(), dataprop.getProperty("noProductMessage"));		
	}

}
