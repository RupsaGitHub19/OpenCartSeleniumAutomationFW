package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountPage;

public class ProductDetailsTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accPg=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));


	}
	@DataProvider
	public  Object[][] getProductInfoData() {
		return new Object[][] {
			{"Macbook","MacBook Pro","Apple","Product 18","800","Out Of Stock","$2,000.00","$2,000.00"},
			{"samsung","Samsung SyncMaster 941BW",null,"Product 6",null,"2-3 Days","$242.00","$200.00"},
			
		};
	}

	@Test(dataProvider="getProductInfoData", priority=1)
	public void productDetailsTest(String searchKey,String productNm,String brand,String productCd,String rewdPts,String availability,String prodPrice,String ProdExTax) {
		searchPage=	accPg.doSearch(searchKey);
		productDetailsPage= searchPage.selectProduct(productNm);
		Map<String, String> productDetails=productDetailsPage.getProductDetails();
		softAssert.assertEquals(productDetails.get("Product Name"), productNm);
		softAssert.assertEquals(productDetails.get("Brand"), brand);
		softAssert.assertEquals(productDetails.get("Product Code"), productCd);
		softAssert.assertEquals(productDetails.get("Reward Points"),rewdPts);
		softAssert.assertEquals(productDetails.get("Availability"), availability);
		softAssert.assertEquals(productDetails.get("Product Price"), prodPrice);
		softAssert.assertEquals(productDetails.get("Product Ex Tax"), ProdExTax);
		softAssert.assertAll();
		/*Assert class is used for hard assertion. If one Assert fails the remaining wont run
	so we use one hard assertion for one test class. All methods insider Assert class are static
	However to handle this multiple assertion case, we can use soft assert.
	the methods inside soft assert are non static, so we have to create object in basetest.
	at the end we should mention softAssert.AssertAll() method, to determine,
	which assertions have failed*/	
		System.out.println("productDetailsTest");

	}

	@Test(priority=2)
	public void footerLinkCountTest() {
		Assert.assertEquals(productDetailsPage.getFooterLinksCount(), 15);	
		System.out.println("footerLinkCountTest");

	}

	@Test(priority=3)
	public void footerLinkTest() {
		softAssert.assertEquals(productDetailsPage.getFooterLinks().get(0), "About Us");
		softAssert.assertEquals(productDetailsPage.getFooterLinks().get(9), "Affiliate");
		softAssert.assertTrue(productDetailsPage.getFooterLinks().containsAll(AppConstants.EXPECTED_FOOTER_LINKS));
		/*contains will fail coz it checks for single element, it cannot check list of elements
	so we have to use containsAll()*/
		softAssert.assertAll();
		System.out.println("footerLinkTest");

	}
}
