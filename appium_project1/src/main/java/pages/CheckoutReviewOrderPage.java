package pages;


import io.appium.java_client.AppiumDriver;
import static utilities.AndroidLocators.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

public class CheckoutReviewOrderPage extends BasePage {
	
	private List<Map<String,Object>> productsInCart = new ArrayList<>();
	
	public CheckoutReviewOrderPage(AppiumDriver driver) {
		super(driver);
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
	
	public String getDelevieryAddress()
	{
		List<WebElement> els = findElements(deliveryAddress);
		String address = "";
		for(WebElement i:els)
		{
			address = i.getText()+"\n";
		}
		return address;
	}
	
	public String getBillingAddress()
	{
		List<WebElement> els = findElements(billingAddress);
		String address = "";
		for(WebElement i:els)
		{
			address = i.getText()+"\n";
		}
		return address;
	}
	
	public String getPaymentMethodDetails()
	{
		List<WebElement> els = findElements(paymentMethodDetails);
		String details = "";
		for(WebElement i:els)
		{
			details = i.getText()+"\n";
		}
		return details;
	}
	
	public String getDeliveryDetails()
	{
		List<WebElement> els = findElements(deliveryDetails);
		String details = "";
		for(WebElement i:els)
		{
			details = i.getText()+"\n";
		}
		return details;
	}
	
	
	public void placeOrderButton()
	{
		findElement(placeOrderButton).click();
	}
	
	public int getTotalItems()
	{
		
		return Integer.parseInt(findElement(totalItemNum).getText());
	}
	
	public int getTotalPrice()
	{
		
		return Integer.parseInt(findElement(totalPrice).getText());
	}
	
	
}
