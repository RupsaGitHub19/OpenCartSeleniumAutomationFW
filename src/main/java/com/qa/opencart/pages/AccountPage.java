package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class AccountPage {
	private WebDriver driver;
	private ElementUtils ele;
	
	private By logout=By.linkText("Logout");
	private By headers=By.xpath("//div[@id='content']/h2");
	private By search=By.name("search");
	private By searchBtn=By.xpath("//div[@id='search']/span/button");
	private By registerLnk=By.linkText("Register");
	
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		ele=new ElementUtils(this.driver);
	}
	
	public String getAccPageTitle() {
	String title=	ele.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
	return title;
	}

	public String getAccPageURL() {
		String url=ele.waitForURLContains(AppConstants.ACCOUNT_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		return url;
		}
	
	public boolean isLogoutLinkExist() {
	return ele.waitVisibilityOfElement(logout, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}
	
	public void logOut() {
		if(isLogoutLinkExist()) {
			ele.doClick(logout);
		}
	}
	
	public boolean isSearchExist() {
		return ele.waitVisibilityOfElement(search, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public SearchResultsPage doSearch(String productName) {
		if(isSearchExist()) {
			ele.waitForPresenceOfElement(search, AppConstants.SHORT_DEFAULT_WAIT).clear();
			ele.doSendKeys(search, productName);
			ele.doClick(searchBtn);
		}
		return new SearchResultsPage(driver);
	}
	
	public List<String> getHeaderList() {
	List<WebElement> headersList=ele.waitForVisibilityOfElements(headers, AppConstants.MEDIUM_DEFAULT_WAIT);
	List<String> headersStringList=new ArrayList<String>();
	for(WebElement e: headersList) {
		headersStringList.add(e.getText());
		
	}
	System.out.println(headersStringList);
	return headersStringList;
	}
	
	
//	public RegistrationPage navigateToRegister() {
//		
//		ele.waitVisibilityOfElement(logout, AppConstants.SHORT_DEFAULT_WAIT).click();{
//		}
//		ele.waitVisibilityOfElement(registerLnk, AppConstants.SHORT_DEFAULT_WAIT).click();
//		return new RegistrationPage(driver);
//
//		}

}
