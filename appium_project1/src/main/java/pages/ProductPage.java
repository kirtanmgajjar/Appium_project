package pages;

import io.appium.java_client.AppiumDriver;
import static utilities.AndroidLocators.*;

import java.util.HashMap;
import java.util.Map;



public class ProductPage extends BasePage{
	
	public ProductPage(AppiumDriver driver)
	{
		super(driver);
	}
	
	public Map<String,String> getProductPageDetails()
	{
		Map<String,String> productDetails = new HashMap<>();
		productDetails.put("name",findElement(productName).getText());
		productDetails.put("price",findElement(productPrice).getText());
		return productDetails;
	}
	
	public void addProducts(int num)
	{
		waitForVisible(findElement(counterAmount));
		while(Integer.parseInt(findElement(counterAmount).getText())!=num)
		{
			if(Integer.parseInt(findElement(counterAmount).getText())<num)
				findElement(counterPlusButton).click();
			else if(Integer.parseInt(findElement(counterAmount).getText())>num)
				findElement(counterMinusButton).click();
				
		}
		findElement(addToCartButton).click();
		
	}
	
	public int getItemsInCart()
	{
		return Integer.parseInt(findElement(itemsInCart).getText());
	}
	
	public void navigateToCatalog()
	{
		clickMenu();
		findElement(catalogMenu).click();
	}
}
