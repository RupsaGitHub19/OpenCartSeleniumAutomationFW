package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class ProductDetailsPage {
	private WebDriver driver;
	private ElementUtils ele;
	
	private By prodName=By.xpath("//div[@id='content']//h1");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By footerlinks=By.xpath("//footer/div[@class='container']//ul/li");
	private Map<String,String> productData=new HashMap<String,String>();
	//hashmap does not maintain insertion order
	//to maintain the insertion order, we can use LinkedHashMap
	//to maintain the data in Sorted alphabetical order we can use TreeMap
	
	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		ele=new ElementUtils(this.driver);
	}
	
	public String getProductName() {
	String productName =ele.waitVisibilityOfElement(prodName, AppConstants.SHORT_DEFAULT_WAIT).getText();
	return productName;
	}
	
	private Map<String, String> getProductMetaData() {
	List<WebElement> metaData=ele.waitForVisibilityOfElements(productMetaData, AppConstants.SHORT_DEFAULT_WAIT);
	for(WebElement e:metaData) {
		String data=e.getText();
		String metaKey=data.split(":")[0].trim();
		String metaValue=data.split(":")[1].trim();
		productData.put(metaKey, metaValue);
	}

	return productData;		
	}
	
	private Map<String, String> getProductPriceData() {
		List<WebElement> priceData=ele.waitForVisibilityOfElements(productPriceData, AppConstants.SHORT_DEFAULT_WAIT);
		productData.put("Product Price", priceData.get(0).getText());
		productData.put("Product Ex Tax", priceData.get(1).getText().split(":")[1].trim());
		
		return productData;
		
	}
	
	public Map<String, String> getProductDetails() { 
		productData.put("Product Name", getProductName());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productData);
		return productData;
	}
	
	public int getFooterLinksCount() {
		List<WebElement> linkEle=ele.waitForVisibilityOfElements(footerlinks, AppConstants.SHORT_DEFAULT_WAIT);
	return	linkEle.size();
	}
	
	public List<String> getFooterLinks() {
	List<WebElement> linkEle=ele.waitForVisibilityOfElements(footerlinks, AppConstants.SHORT_DEFAULT_WAIT);
	List<String> linkText=new ArrayList<String>();
	for(WebElement e:linkEle) {
	linkText.add(e.getText());
	}
	System.out.println(linkText);
	return linkText;
	}
}
