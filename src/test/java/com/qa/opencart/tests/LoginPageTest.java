package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("EPIC 100: THIS IS LOGIN PAGE TESTING")
@Story("USR20010:   THis is my USR for login TCs")
@Feature("Feature 50: THis is team feature for login")
public class LoginPageTest extends BaseTest {
	
	@Description("This Test case is used to test login page title")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void LoginPageTitleTest() {
		Assert.assertEquals(lp.getLoginTitle(), "Account Login");
		System.out.println("LoginPageTitleTest");

	}
	
	@Description("This Test case is used to test login page URL")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	public void LoginPageURLTest() {
		Assert.assertTrue(lp.getLoginURL().contains("account/login"));
		System.out.println("LoginPageURLTest");

	}
	@Description("This Test case is used to test login page feature of OpenCart application")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void DoLoginTest() {
		accPg=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPg.getAccPageTitle(), "My Account");
		System.out.println("DoLoginTest");

	}
	

	@Description("This Test case is used to test Register Link in login page")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=4)
	public void RegisterLinkTest() {
		accPg.isLogoutLinkExist();
		registerPage=lp.navigateToRegister();
		Assert.assertEquals(registerPage.getRegisterTitle(), "Register Account");
		System.out.println("RegisterLinkTest");

	}
	
	
}
