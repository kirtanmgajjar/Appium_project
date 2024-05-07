package pages;


import static utilities.AndroidLocators.*;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;

public class Catalog extends BasePage{
	
	public Catalog(AppiumDriver driver)
	{
		super(driver);
	}
	
	public void verifyPage()
	{
		findElement(productsLabel);
	}
	
	public void clickMenu()
	{
		findElement(menuIcon).click();
		wait.until(ExpectedConditions.visibilityOf(findElement(webviewMenu)));
	}
	
	public void verifyMenuItems() throws Exception
	{
		List<String> menuItems = findElements(allMenuItems).stream().map(WebElement::getText).toList();
		Reporter.log(menuItems.toString());
		
	}
	
	public void navigateToDrawingMenu()
	{
		clickMenu();
		findElement(drawingMenu).click();
	}
	
	
	
}
