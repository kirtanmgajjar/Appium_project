package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

import static utilities.AndroidLocators.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

public class CartPage extends BasePage{
	
	private List<Map<String,Object>> productsInCart = new ArrayList<>();
	
	public CartPage(AppiumDriver driver)
	{
		super(driver);
		scrollTillLast();
	}
	
	public List<Map<String,Object>> getProductDetails()
	{
		List<WebElement> productsName = findElements(productLabel);
		List<WebElement> productsPrice = findElements(productPrice);
		List<WebElement> productsQuantity = findElements(counterAmount);
		for(int i=0; i<productsName.size();i++)
		{
			Map<String,Object> productDetails = new HashMap<>();
			productDetails.put("WebElement", productsName.get(i));
			productDetails.put("name", productsName.get(i).getText());
			productDetails.put("price", productsPrice.get(i).getText());
			productDetails.put("quantity", productsQuantity.get(i).getText());
			productsInCart.add(productDetails);
		}
		return productsInCart;
	}
	
	public void addQuantity(String name,int quantity)
	{
		int i = findInList(name)+1;
		
		while(Integer.parseInt(findElement(String.format(productAmountInCart,i)).getText())!=quantity)
		{
			if(Integer.parseInt(findElement(String.format(productAmountInCart,i)).getText())<quantity)
				findElement(String.format(incProductInCart,i)).click();
			else if(Integer.parseInt(findElement(String.format(productAmountInCart,i)).getText())>quantity)
				findElement(String.format(decProductInCart,i)).click();
		}
		
		productsInCart.get(i-1).put("quantity", quantity);
	}
	
	
	public void removeItemFromCart(String name)
	{
		int i = findInList(name)+1;
		findElement(String.format(removeProductFromCart, i)).click();
		productsInCart.remove(i-1);
	}
	
	
	public String getTotalItems()
	{
		
		return findElement(totalItemNum).getText();
	}
	
	public String getTotalPrice()
	{
		
		return findElement(totalPrice).getText();
	}
	
	public void proceedToCheckout()
	{
		findElement(checkoutButton).click();
	}
	
	private int findInList(String name)
	{
		for(int i=0;i<productsInCart.size();i++)
		{
			if(productsInCart.get(i).get("name").equals(name)) {
				return i;
			}
		}
		return -1;
		
	}
}
