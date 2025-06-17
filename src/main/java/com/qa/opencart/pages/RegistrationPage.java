package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class RegistrationPage {
private WebDriver driver;
private ElementUtils ele;

public RegistrationPage(WebDriver driver) {
	this.driver=driver;
	ele=new ElementUtils(this.driver);
}
 private By firstName=By.name("firstname");
 private By lastName=By.name("lastname");
 private By email=By.name("email");
 private By telephone=By.name("telephone");
 private By password=By.name("password");
 private By confirmPassword=By.name("confirm");
 private By policyChkBox=By.name("agree");
 private By continueBtn=By.xpath("//input[@type='submit']");
 private By successMssg=By.xpath("//div[@id='content']/h1"); 
 private By logoutLnk=By.linkText("Logout");
 private By registerLnk=By.linkText("Register");
 
 
 public boolean register(String firstNm,String lastNm,String mail,String telephn,String passWd) {
	 ele.waitVisibilityOfElement(firstName, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(firstNm);
	 ele.doSendKeys(lastName, lastNm);
	 ele.doSendKeys(email,mail);
	 ele.doSendKeys(telephone,telephn);
	 ele.doSendKeys(password,passWd);
	 ele.doSendKeys(confirmPassword,passWd);
	 ele.doClick(policyChkBox);
	 ele.waitVisibilityOfElement(continueBtn, AppConstants.SHORT_DEFAULT_WAIT).click();
	 String successMessage= ele.waitVisibilityOfElement(successMssg, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
	 
	 if(successMessage.contains(AppConstants.USER_REGISTER_SUCCESS_MESSAGE)) {
		 ele.doClick(logoutLnk);
		 ele.doClick(registerLnk);
		 return true;
	 }
	 else
		 return false;
	
 }
 
 public String getRegisterTitle() {
	return ele.waitForTitleIs("Register Account",AppConstants.MEDIUM_DEFAULT_WAIT);
 }
}
