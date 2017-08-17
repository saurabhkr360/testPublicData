package test.java.com.MusicGroup.util;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Select;
//import com.thoughtworks.selenium.Selenium;

import org.testng.Assert;

public abstract class DriverHelper
{
	//Define objects
	protected WebDriver driver;
	
	//Declare objects
	public DriverHelper(WebDriver webdriver) 
	{
		driver = webdriver;
	}
	
/*	// Return web driver object
	public WebDriver getWebDriver() 
	{
		return driver;
	}*/

	// Handle locator type
	public By ByLocator(String locator) {
		By result = null;

		if (locator.startsWith("//")) {
			result = By.xpath(locator);
		} else if (locator.startsWith("css=")) {
			result = By.cssSelector(locator.replace("css=", ""));
			  
		} else if (locator.startsWith("#")) {
			result = By.name(locator.replace("#", ""));
		} else if (locator.startsWith("link=")) {
			result = By.linkText(locator.replace("link=", ""));
		} else {
			result = By.id(locator);
		}
		return result;
	}
	


	// Assert element present
	public Boolean isElementPresent(String locator) 
	{
		Boolean result = false;
		try {
			
			driver.findElement(ByLocator(locator));
			result = true;
		} catch (Exception ex) {
		}
		return result;
	}

	// Wait for element present
	public void WaitForElementPresent(String locator, int timeout) 
	{
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element not present
	public void WaitForElementNotPresent(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (!isElementPresent(locator)) {
				break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element enabled
	public void WaitForElementEnabled(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (driver.findElement(ByLocator(locator)).isEnabled()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element not enabled
	public void WaitForElementNotEnabled(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (!driver.findElement(ByLocator(locator)).isEnabled()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element visible
	public void WaitForElementVisible(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (driver.findElement(ByLocator(locator))
						.isDisplayed()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element not visible
	public void WaitForElementNotVisible(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (!driver.findElement(ByLocator(locator))
						.isDisplayed()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for text present
	public void WaitForTextPresent(String locator, String text, int timeout) {
		WaitForElementPresent(locator, timeout);
		for (int i = 0; i < timeout; i++) {
			if (isTextPresent(locator, text)) {
				break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Handle mouse over action
	public void mouseOver(String locator) {
		this.WaitForElementPresent(locator, 50);
		WebElement el = driver.findElement(ByLocator(locator));

		// build and perform the mouseOver with Advanced User Interactions API
		Actions builder = new Actions(driver);
		builder.moveToElement(el).build().perform();		
	}	

	// Handle mouse double click action
	public void mouseDoubleClick(String locator) {
		this.WaitForElementPresent(locator, 50);
		WebElement el = driver.findElement(ByLocator(locator));

		// build and perform the mouse click with Advanced User Interactions API
		Actions builder = new Actions(driver);		
		builder.doubleClick(el).perform();
	}	

	// Handle drag and drop action
	public void dragAndDrop(String draggable, String to) {
		this.WaitForElementPresent(draggable, 50);
		this.WaitForElementPresent(to, 50);
		WebElement elDraggable = driver.findElement(ByLocator(draggable));
		WebElement todrag = driver.findElement(ByLocator(to));

		// build and perform drag and drop with Advanced User Interactions API
		Actions builder = new Actions(driver);
		builder.dragAndDrop(elDraggable, todrag).perform();
	}

	// Handle click action
	public void clickOn(String locator) {
		this.WaitForElementEnabled(locator, 10);
		Assert.assertTrue(isElementPresent(locator));
		WebElement el = driver.findElement(ByLocator(locator));
		el.click();
	}	
	
	

	// Handle send keys action
	public void sendKeys(String locator, String text) {
		this.WaitForElementPresent(locator, 30);
		//Assert.assertTrue(isElementPresent(locator));
		WebElement el = driver.findElement(ByLocator(locator));
		el.clear();
		el.sendKeys(text);
	}
	
	// Press Enter keys action
	public void presEnterKey(String locator) {
		this.WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator));
		WebElement el = driver.findElement(ByLocator(locator));
		el.sendKeys(Keys.ENTER);
	}
	
	// Handle send keys action
	public void sendKeysAction(String locator, String text) {
		this.WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator));
		WebElement el = driver.findElement(ByLocator(locator));
		Actions builder = new Actions(driver);
			//builder.sendKeys(el, text).perform();
		builder.click(el).perform();
		builder.sendKeys(text).perform();
			//builder.sendKeys(Keys.ENTER).perform();		
			}

	// Select value from drop down
	public void selectDropDown(String locator, String targetValue) {
		Assert.assertTrue(isElementPresent(locator));
		this.WaitForElementPresent(locator, 20);
		new Select(driver.findElement(ByLocator(locator)))
				.selectByVisibleText(targetValue);

	}

	// Assert text present
	public boolean isTextPresent(String locator, String str) {
		Assert.assertTrue(isElementPresent(locator));
		String message = driver.findElement(ByLocator(locator)).getText();
		if (message.contains(str)) {
			return true;
		} else {
			return false;
		}
	}

	// Store text from a locator
	public String getText(String locator) {
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator));
		String text = driver.findElement(ByLocator(locator)).getText();
		return text;
	}

	// Assert check box selected
	public boolean isChecked(String locator) {
		boolean checkStatus = false;
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator));
		WebElement el = driver.findElement(ByLocator(locator));
		checkStatus = el.isSelected();
		return checkStatus;
	}

	// Get attribute value
	public String getAttribute(String locator, String attribute) {
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator));
		String text = driver.findElement(ByLocator(locator)).getAttribute(attribute);
		return text;
	}
	
	// Get css value
	public String getCSS(String locator, String csstype) {
		WaitForElementPresent(locator, 50);
		Assert.assertTrue(isElementPresent(locator));
		String text = driver.findElement(ByLocator(locator)).getCssValue(csstype);
		return text;
	}

	public void acceptAlert() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alert = driver.switchTo().alert();
		alert.accept();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void acceptAlertdismiss() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void verifyTextPresent(String text)
	 {
	  
	  try {
	   Thread.sleep(5000);
	  } catch (InterruptedException e) {
	   e.printStackTrace();
	  }
	  boolean result = driver.findElement(By.cssSelector("body")).getText().contains(text);
	  Assert.assertTrue(result);
	  //return result;
	 }
	
	public void waitForElementLoad(int x)
	{
		int num = x*1000;
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void switchWindow(String title)
	{
		driver.switchTo().window(title);
		driver.getTitle();
	}
	public void switchToFrameById (String id) {
		driver.switchTo().frame(id);
	}

	
/*	public  getHeight() {
		 height = null;
		try{
	        driver.findElement(By.xpath("")).getSize().getHeight();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}*/
	
	
}
