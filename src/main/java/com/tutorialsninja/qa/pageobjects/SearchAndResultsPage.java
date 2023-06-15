package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchAndResultsPage {

	WebDriver driver;
	
	@FindBy(name="search")
	private WebElement searchbox;

	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchbutton;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement validHPproduct;
	
	@FindBy(xpath = "//input[@id='button-search']/following-sibling::p")
	private WebElement invalidProductSearchMessage;
	
	public SearchAndResultsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	public void enterProductNameIntoSearchField(String searchoption)
	{
		searchbox.sendKeys(searchoption);
	}
	public void clickSearchButtonForresults()
	{
		searchbutton.click();
	}
	public boolean displayStatusOfHPValidProduct()
	{
		return validHPproduct.isDisplayed();
	}
	public String getInvalidProductSearchMessage()
	{
		return invalidProductSearchMessage.getText();
	}

}
