package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtils;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void registrationSetup() {
		accPg=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		registerPage=lp.navigateToRegister();
	}
	
//	@DataProvider
//	public Object[][] getUserRegData() {
//		return new Object[][]{
//				{"Tom","automation","9283756102","test@123"},
//		{"Rahul","auto","9234756102","test@123"},
//		{"Ajay","yadav","928346102","test@123"}
//	};
//	}
	
	@DataProvider
	public Object[][] getUserRegTestExcelData() {
	Object regData[][]=	ExcelUtils.readDataFromExcel(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	public String getRandomEmailID(String firstNm) {
		return firstNm+"testautomation" + System.currentTimeMillis() + "@opencart.com";
		// testautomation1212121@opencart.com
		// return "testautomation" + UUID.randomUUID()+"@gmail.com";

	}
	
	@Test(dataProvider="getUserRegTestExcelData")
	public void doRegisterTest(String firstNm,String lastNm,String phone,String pass) {
	boolean isRegdone=registerPage.register(firstNm, lastNm, getRandomEmailID(firstNm), phone, pass);
	Assert.assertTrue(isRegdone);
	System.out.println("doRegisterTest");

	}
	
	
}
	
