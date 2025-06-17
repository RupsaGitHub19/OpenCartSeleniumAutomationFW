package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class SearchResultsPage {
	private WebDriver driver;
	private ElementUtils ele;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		ele=new ElementUtils(this.driver);
	}
	
	public ProductDetailsPage selectProduct(String productName) {
		ele.waitVisibilityOfElement(By.linkText(productName), AppConstants.LONG_DEFAULT_WAIT).click();
		return new ProductDetailsPage(driver);
	}
	
	
}
