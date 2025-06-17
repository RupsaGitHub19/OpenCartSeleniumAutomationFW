package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

import io.qameta.allure.Step;
//Page classes are classic examples of encapsulation
public class LoginPage {
	private WebDriver driver;
	private ElementUtils ele;

	private By userName=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By logout=By.linkText("Logout");
	private By registerLnk=By.linkText("Register");

	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		ele=new ElementUtils(this.driver);

	}

	@Step("This is method for Getting Login Title")
	public String getLoginTitle() {
		//String title=driver.getTitle();.
		String title=ele.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login Page title is:"+ title);
		return title;
	}

	@Step("This is method for Getting Login URL")
	public String getLoginURL() {
		//String url=driver.getCurrentUrl();
		String url=ele.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login Page url is:"+ url);
		return url;
	}

	@Step("This is method for page login: {0} and {1}")
	public AccountPage doLogin(String userNm,String Pwd) {
		ele.doSendKeysWithWait(userName, userNm, AppConstants.MEDIUM_DEFAULT_WAIT);
		ele.doSendKeysWithWait(password, Pwd, AppConstants.SHORT_DEFAULT_WAIT);
		ele.doClick(loginBtn);
		//	ele.doClickWithWait(loginBtn, AppConstants.MEDIUM_DEFAULT_WAIT);
		System.out.println("User has been logged in");
		return new AccountPage(driver);
	}

	public RegistrationPage navigateToRegister() {
		//if(ele.getPageTitle().equalsIgnoreCase(AppConstants.ACCOUNT_PAGE_TITLE)) {
		ele.waitVisibilityOfElement(logout, AppConstants.SHORT_DEFAULT_WAIT).click();
	//	}
		ele.waitVisibilityOfElement(registerLnk, AppConstants.SHORT_DEFAULT_WAIT).click();
		return new RegistrationPage(driver);

	}



}
