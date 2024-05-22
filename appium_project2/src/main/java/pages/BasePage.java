package pages;

import static utilities.AndroidLocators.menuIcon;
import static utilities.AndroidLocators.webviewMenu;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;


public class BasePage {
	
	public AppiumDriver driver;
	public WebDriverWait wait;
	
	public BasePage(AppiumDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	
	public WebElement findElement(String locator)
	{
		String[] loc = locator.split(";");
		if (loc[0].equals("accessibility id"))
			return driver.findElement(AppiumBy.accessibilityId(loc[1]));
		else if(loc[0].equals("class"))
			return driver.findElement(AppiumBy.className(loc[1]));
		else if(loc[0].equals("id"))
			return driver.findElement(AppiumBy.id(loc[1]));
		else if(loc[0].equals("name"))
			return driver.findElement(AppiumBy.name(loc[1]));
		else if(loc[0].equals("xpath"))
			return driver.findElement(AppiumBy.xpath(loc[1]));
		else if(loc[0].equals("UiSelector"))
			return driver.findElement(AppiumBy.androidUIAutomator(loc[1]));
		else
			throw new NoSuchElementException();
		
	}
	
	public List<WebElement> findElements(String locator)
	{
		
		String[] loc = locator.split(";");
		if (loc[0].equals("accessibility id"))
			return driver.findElements(AppiumBy.accessibilityId(loc[1]));
		else if(loc[0].equals("class"))
			return driver.findElements(AppiumBy.className(loc[1]));
		else if(loc[0].equals("id"))
			return driver.findElements(AppiumBy.id(loc[1]));
		else if(loc[0].equals("name"))
			return driver.findElements(AppiumBy.name(loc[1]));
		else if(loc[0].equals("xpath"))
			return driver.findElements(AppiumBy.xpath(loc[1]));
		else if(loc[0].equals("UiSelector"))
			return driver.findElements(AppiumBy.androidUIAutomator(loc[1]));
		else
			return null;
		
	}
	
	
	public void waitForVisible(WebElement... el)
	{
		if(el.length==1)
		wait.until(ExpectedConditions.visibilityOf(el[0]));
		else
			wait.until(ExpectedConditions.visibilityOf(el[0]));
	}
	
	public void waitForPresence(String locator)
	{
		By location=null;
		String[] loc = locator.split(";");
		if (loc[0].equals("accessibility id"))
			location = AppiumBy.accessibilityId(loc[1]);
		else if(loc[0].equals("class"))
			location = AppiumBy.className(loc[1]);
		else if(loc[0].equals("id"))
			location = AppiumBy.id(loc[1]);
		else if(loc[0].equals("name"))
			location = AppiumBy.name(loc[1]);
		else if(loc[0].equals("xpath"))
			location = AppiumBy.xpath(loc[1]);
		else if(loc[0].equals("UiSelector"))
			location = AppiumBy.androidUIAutomator(loc[1]);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(location));
	}
	
	public By getByObject(String locator)
	{
		By location=null;
		String[] loc = locator.split(";");
		if (loc[0].equals("accessibility id"))
			location = AppiumBy.accessibilityId(loc[1]);
		else if(loc[0].equals("class"))
			location = AppiumBy.className(loc[1]);
		else if(loc[0].equals("id"))
			location = AppiumBy.id(loc[1]);
		else if(loc[0].equals("name"))
			location = AppiumBy.name(loc[1]);
		else if(loc[0].equals("xpath"))
			location = AppiumBy.xpath(loc[1]);
		else if(loc[0].equals("UiSelector"))
			location = AppiumBy.androidUIAutomator(loc[1]);
		
		return location;
	}
	
	public void waitForClickable(WebElement el)
	{
		wait.until(ExpectedConditions.elementToBeClickable(el));
	}
	
	
	
	public void clickMenu()
	{
		waitForClickable(findElement(menuIcon));
		findElement(menuIcon).click();
		waitForVisible(findElement(webviewMenu));
	}
	
	
}
