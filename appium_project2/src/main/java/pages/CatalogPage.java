package pages;


import static utilities.AndroidLocators.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;

public class CatalogPage extends BasePage{
	
	private List<Map<String,String>> productsDetails = new ArrayList<>();
	
	public CatalogPage(AppiumDriver driver)
	{
		super(driver);
		scrollTillLast();
	}
	
	public void verifyPage()
	{
		findElement(productsLabel);
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
	
	
	
	public List<Map<String,String>> getAllProductDetails()
	{
		List<WebElement> products = findElements(productsNames);
		List<WebElement> productsPrice = findElements(productsPrices);
		for(int i=0;i<products.size();i++)
		{
			Map<String,String> productDetails = new HashMap<>();
			productDetails.put("name", products.get(i).getText());
			productDetails.put("Price", productsPrice.get(i).getText());
			productsDetails.add(productDetails);
		}
		return productsDetails;
	}
	
	public void navigateToProduct(String name)
	{
		findElements(productsNames).get(findInList(name)).click();
	}
	
	public void navigateToProduct(int num)
	{
		findElements(productsNames).get(num-1).click();
	}
	
	public int getItemsInCart()
	{
		waitForVisible(findElement(itemsInCart));
		return Integer.parseInt(findElement(itemsInCart).getText());
	}
	
	
	public void navigateToCart()
	{
		waitForPresence(cartBadge);
		tap(findElement(cartBadge));
	}
	
	public void sortItems(String choice, String order)
	{
		findElement(sortButton).click();
		if(choice.equalsIgnoreCase("name"))
		{
			if(order.equalsIgnoreCase("descending"))
				findElement(sortNameDesc).click();
			else
				findElement(sortNameAsc).click();
		}
		else if(choice.equalsIgnoreCase("price"))
		{
			if(order.equalsIgnoreCase("descending"))
				findElement(sortPriceDesc).click();
			else
				findElement(sortPriceAsc).click();
		}
	}
	
	public int findInList(String name)
	{
		for(int i=0;i<productsDetails.size();i++)
		{
			if(productsDetails.get(i).get("name")==name)
			{
				return i;
			}
		}
		return -1;
	}
	
}
