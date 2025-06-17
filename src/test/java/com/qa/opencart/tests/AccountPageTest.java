package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetup() {
		accPg=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		//doLogin method returns the AccountPage object
	}
	
	@Test
	public void accPageTitleTest() {
	Assert.assertEquals(accPg.getAccPageTitle(), "My Account");
		System.out.println("accPageTitleTest");
	}
	
	@Test
	public void accPageURLTest() {
	Assert.assertTrue(accPg.getAccPageURL().contains("route=account/account"));
	System.out.println("accPageURLTest");
	}
	
	@Test
	public void accPageLogoutLinkTest() {
	Assert.assertTrue(accPg.isLogoutLinkExist());
	System.out.println("accPageLogoutLinkTest");
	}
	
	@Test
	public void getHeaderList() {
	Assert.assertEquals(accPg.getHeaderList(), AppConstants.EXPECTED_ACCOUNT_PAGE_HEADERS);
	System.out.println("getHeaderList");

	}
	
	@DataProvider
	public Object[][] getSearchData() {
	return	new Object[][] {
			{"Macbook","MacBook Pro"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"imac","iMac"}
			
		};
	}
	@Test(dataProvider="getSearchData")
	public void searchTest(String searchKey,String productNm) {
		searchPage=	accPg.doSearch(searchKey);
		productDetailsPage=	searchPage.selectProduct(productNm);
		Assert.assertEquals(productDetailsPage.getProductName(), productNm);;
		System.out.println("searchTest");

	}
	
	
}
