package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstname;
	
	@FindBy(id="input-lastname")
	private WebElement lastname;
	
	@FindBy(id="input-email")
	private WebElement email;

	@FindBy(id="input-telephone")
	private WebElement telephone;

	@FindBy(id="input-password")
	private WebElement password;

	@FindBy(id="input-confirm")
	private WebElement confirmpassword;

	@FindBy(xpath="//input[@name='agree']")
	private WebElement agreeOption;

	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueOption;
	
	@FindBy(xpath="//input[@name='newsletter' and @value=1]")
	private WebElement subscribeOption;
	
	@FindBy(xpath="//div[@id='content']//h1")
	private WebElement actualSuccessHeadingMessage;
	
	@FindBy(xpath="//div[contains(@class, 'alert alert-danger alert-dismissible')]")
	private WebElement duplicateEmailWarningMessage;
	
	@FindBy(xpath="//div[contains(@class, 'alert-danger')]")
	WebElement privacywarningmsg;

	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	WebElement firstnamewarningmessage;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	WebElement lastnamewarningmessage;

	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	WebElement emailwarningmessage;

	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	WebElement telephonewarningmessage;

	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	WebElement passwordwarningmessage;

	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String fname)
	{
		firstname.sendKeys(fname);
	}
	public void enterLastName(String lname)
	{
		lastname.sendKeys(lname);
	}
	public void enterEmail(String emailid)
	{
		email.sendKeys(emailid);
	}
	public void enterTelephone(String phone)
	{
		telephone.sendKeys(phone);
	}
	public void enterPwd(String pwd)
	{
		password.sendKeys(pwd);
	}
	public void confirmPwd(String confpwd)
	{
		confirmpassword.sendKeys(confpwd);
	}
	public void checkAgree()
	{
		agreeOption.click();
	}
	public void clickContinueButton()
	{
		continueOption.click();
	}
	public String retrieveSuccessHeadingMessage()
	{
		return actualSuccessHeadingMessage.getText();
		
	}
	public String duplicateEmailWarningMessage()
	{
		return duplicateEmailWarningMessage.getText();
	}
	public void selectSubscribeOption()
	{
		subscribeOption.click();
	}
	
	public String actualPrivacyPolicyWarning()
	{
		return privacywarningmsg.getText();
	}
	public String actualFirstNameWarningMessage()
	{
		return firstnamewarningmessage.getText();
	}
	public String actualLastNameWarningMessage()
	{
		return lastnamewarningmessage.getText();
	}
	public String actualEmailWarningMessage()
	{
		return emailwarningmessage.getText();
	}
	public String actualTelepohoneWarningMessage()
	{
		return telephonewarningmessage.getText();
	}
	public String actualPasswordWarningMessage()
	{
		return passwordwarningmessage.getText();
	}
	
	
}
