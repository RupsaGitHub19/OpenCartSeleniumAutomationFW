package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.google.common.io.Files;
import com.qa.opencart.exceptions.FrameworkException;



public class DriverFactory {
 WebDriver driver;
 Properties prop;
 OptionsManager om;
 public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
 //threadlocal creates a copy of the driver which every thread can use during execution
 
 
 public WebDriver initDriver(Properties prop) {
	 //Passing browser and url as parameters in initDriver is messy. So we use call by reference
	 //we pass Properties prop reference, and access all the key values present in properties file
	 
	 
	 om=new OptionsManager(prop);
	String browserName=prop.getProperty("browser");
	//String browserName=System.getProperty("browser");
	 System.out.println("The browser name is:"+browserName);
	 switch (browserName.toLowerCase().trim()) {
	case "chrome":
		//driver=new ChromeDriver(om.getChromeOptions());
		if(Boolean.parseBoolean(prop.getProperty("remote"))){
			//to run testcases in remote
			initRemoteDriver(browserName);
		}
		else {
			//to run testcases in local
			tlDriver.set(new ChromeDriver(om.getChromeOptions()));
		}
		break;
	case "firefox":
		//driver=new FirefoxDriver(om.getFirefoxOptions());
		if(Boolean.parseBoolean(prop.getProperty("remote"))){
			//to run testcases in remote
			initRemoteDriver(browserName);
		}
		else {
			//to run testcases in local
			tlDriver.set(new FirefoxDriver(om.getFirefoxOptions()));
		}
		break;
	case "edge":
		//driver=new EdgeDriver(om.getEdgeOptions());
		if(Boolean.parseBoolean(prop.getProperty("remote"))){
			//to run testcases in remote
			initRemoteDriver(browserName);
		}
		else {
			//to run testcases in local
			tlDriver.set(new EdgeDriver(om.getEdgeOptions()));
		}
		break;
	case "safari":
		driver=new SafariDriver();
		break;

	default:
		throw new FrameworkException("This is invalid browser name. Please pass the right browser");
	}
//	 driver.manage().window().maximize();
//	 driver.get(prop.getProperty("url"));
//	 System.out.println("User is logged in");
//	 return driver;

	 getTLDriver().manage().window().maximize();
	 getTLDriver().get(prop.getProperty("url"));
	 System.out.println("User is logged in");
	 return getTLDriver();
}
 
 public static WebDriver getTLDriver() {
	 return tlDriver.get();
 }
 
 private void initRemoteDriver(String browserName) {
	 System.out.println("Executing in Selenium GRID using browsername:"+browserName);
	try {
	 switch(browserName.toLowerCase().trim()) {
	case "chrome":
		tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),om.getChromeOptions() ));
		break;
 	case "firefox":
		tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),om.getFirefoxOptions()));
		break;
 	case "edge":
		tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),om.getEdgeOptions()));
		break;
	default:
		System.out.println("Invalid browser"+browserName+"Cannot launch selenium grid");
	 }
	}
	 catch(MalformedURLException e) {
		 
	 }
 }
 	public Properties initProp() {//this method is used to read the properties file
 		
 		 //mvn clean install -Denv="qa"
 		FileInputStream io=null;
 		prop=new Properties();
 		String envName=System.getProperty("env");
 		System.out.println("The Env Name is:"+envName);
 		
 			try {
 				if(envName==null) {
				io=new FileInputStream("./src/test/resources/config/config.qa.properties");
			}	
 		else {
 			switch (envName.toLowerCase().trim()) {
			case "qa":
				io=new FileInputStream("./src/test/resources/config/config.qa.properties");	
				break;
			case "uat":
				io=new FileInputStream("./src/test/resources/config/config.uat.properties");	
				break;
			default:
				System.out.println("Please pass the right env"+envName);
				throw new FrameworkException("Wrong Env Name");
			}
 		}
 	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
 		
 	}
 			try {
 				prop.load(io);
 			} catch (IOException e1) {
 				e1.printStackTrace();
 			}
			return prop;
 		
 	}

	public static String getScreenshot(String methodName) {//screenshot method
		//TakesScreenshot
		File srcFile=((TakesScreenshot)getTLDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshot/"+methodName+"_"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		try {
			Files.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
