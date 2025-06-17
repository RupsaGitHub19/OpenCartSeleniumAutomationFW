package com.qa.opencart.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterMethod;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductDetailsPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage lp;
	protected AccountPage accPg;
	protected SearchResultsPage searchPage;
	protected ProductDetailsPage productDetailsPage;
	protected RegistrationPage registerPage;
	protected SoftAssert softAssert;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df=new DriverFactory();
		prop=df.initProp();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		driver=df.initDriver(prop);
		lp=new LoginPage(driver);
		softAssert=new SoftAssert();
	}
	
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
	

}
