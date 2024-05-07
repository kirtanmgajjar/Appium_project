package pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;


public class BasePage {
	
	public AppiumDriver driver;
	public WebDriverWait wait;
	
	public BasePage(AppiumDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
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
}
