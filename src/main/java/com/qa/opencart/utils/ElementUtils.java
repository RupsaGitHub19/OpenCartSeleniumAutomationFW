package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ElementUtils {
private  WebDriver driver;//in EleUtils create a private driver

public ElementUtils(WebDriver driver) {// create a const.. for ElementUtil will driver as parameter, 
										//in Page Class, create obj of EleUtils and access the methods in it
	this.driver = driver;
}

public  WebElement getElement(By locator) {
	return driver.findElement(locator);
}

public  void doSendKeysOnElement(WebElement ele,String text) {
	ele.sendKeys(text);
}
public  void doSendKeys(By locator, String text) {
	getElement(locator).sendKeys(text);
}

public  void doClick(By locator) {
	getElement(locator).click();;
}

public List<WebElement> getElementList(By locator) {
	List<WebElement> list=new ArrayList<WebElement>();
	list=driver.findElements(locator);
	return list;
}

public String getPageTitle() {
return	driver.getTitle();
}
//-------------------* MultiSelect Dropdowns using Select Class*--------------------------
public  boolean isDropdownMultiple(By locator) {
	Select sel=new Select(getElement(locator));
	return sel.isMultiple() ? true:false;
}

public  void multiSelectOption(By locator, String... values) {
	Select sel=new Select(getElement(locator));
	if(isDropdownMultiple(locator)) {
		for(String value: values)
		sel.selectByValue(value);
	}
}
	
	//-------------------* Single Select Dropdowns using Select Class*--------------------------
	public  void singleSelectDropdown(By locator,String value) {
		Select select=new Select(getElement(locator));
		select.selectByContainsVisibleText(value);
		
	}
	//-----------*multimenu handling using Actions class*--------------
	public  void multimenuHandling(By parentmenu,By firstChild,By secondChild,By thirdChild) throws InterruptedException {
		doClick(parentmenu);
		Thread.sleep(3000);
		Actions action=new Actions(driver);
		action.moveToElement(getElement(firstChild)).build().perform();
		Thread.sleep(2000);
		action.moveToElement(getElement(secondChild)).build().perform();
		doClick(thirdChild);
		
	}

////////////////////WAITS//////////////////////////////////
	public WebElement waitForPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement ele=wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return ele;
	}
	
	//with polling time
	public WebElement waitForPresenceOfElement(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		WebElement ele=wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return ele;
	}
	public WebElement waitVisibilityOfElement(By locator, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return ele;
	}
	
	//with polling time
	public WebElement waitVisibilityOfElement(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return ele;
	}
	
	public void doClickWithWait(By locator, int timeOut) {
		waitVisibilityOfElement(locator, timeOut).click();
	}

	public void doSendKeysWithWait(By locator, String value, int timeOut) {
		waitVisibilityOfElement(locator, timeOut).sendKeys(value);
	}
	
	public List<WebElement> waitForVisibilityOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
	
	public List<WebElement> waitForPresenceOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public String waitForTitleContains(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println(titleFraction + " title value is not present....");
			e.printStackTrace();
		}
		return null;

	}

	public String waitForTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.titleIs(title))) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println(title + " title value is not present....");
			e.printStackTrace();
		}
		return driver.getTitle();

	}

	public String waitForURLContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			System.out.println(urlFraction + " url value is not present....");
			e.printStackTrace();
		}
		return null;

	}

	public String waitForURLToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlToBe(url))) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			System.out.println(url + " url value is not present....");
			e.printStackTrace();
		}
		return null;

	}
	public Alert waitForJSAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptJSAlert(int timeOut) {
		waitForJSAlert(timeOut).accept();
	}

	public void dismissJSAlert(int timeOut) {
		waitForJSAlert(timeOut).dismiss();
	}

	public String getJsAlertText(int timeOut) {
		return waitForJSAlert(timeOut).getText();
	}

	public void enterValueOnJsAlert(int timeOut, String value) {
		waitForJSAlert(timeOut).sendKeys(value);
	}

	public void waitForFrameByLocator(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameByIndex(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameByIDOrName(String IDOrName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));
	}

	public void waitForFrameByElement(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public boolean checkNewWindowExist(int timeOut, int expectedNumberOfWindows) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows))) {
				return true;
			}
		} catch (TimeoutException e) {
			System.out.println("number of windows are not same....");
		}
		return false;
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		} catch (TimeoutException e) {
			System.out.println("element is not clickable or enabled...");
		}
	}

}
