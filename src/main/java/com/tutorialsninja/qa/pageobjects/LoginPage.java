package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(id="input-email") 
	private WebElement username;

	@FindBy(id="input-password") 
	private WebElement password;

	@FindBy(xpath="//input[@type='submit' and @value='Login']") 
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]") 
	private WebElement emailPasswordNoMatchWarningMessage;

	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String uname) 
	{
		username.sendKeys(uname);
	}
	
	public void enterPassword(String pwd) 
	{
		password.sendKeys(pwd);
	}
	public MyAccountPage clickOnLoginButton()
	{
		loginButton.click();
		return new MyAccountPage(driver);
	}
	public String retrieveEmailPwdNotMatchWarnMessage()
	{
		return emailPasswordNoMatchWarningMessage.getText();
	}
	
}
