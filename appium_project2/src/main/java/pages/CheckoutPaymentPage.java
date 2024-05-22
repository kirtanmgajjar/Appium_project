package pages;


import io.appium.java_client.AppiumDriver;
import static utilities.AndroidLocators.*;

public class CheckoutPaymentPage extends BasePage {

	public CheckoutPaymentPage(AppiumDriver driver) {
		super(driver);
	}
	
	public void enterAllDetails(String name1, String cardNumber, String expDate, String securityCode, boolean sameBillingAddress,String[] args)
	{
		findElement(nameCheckoutTextbox).sendKeys(name1);
		findElement(cardNumTextbox).sendKeys(cardNumber);
		findElement(expDateTextbox).sendKeys(expDate);
		findElement(securityCodeTextbox).sendKeys(securityCode);
		
		if(sameBillingAddress)
			findElement(sameAddressCheckbox).click();
		else
		{
			if(args.length>0)
			{
				if(args.length==6)
					enterAllBillingAddressDetails(args[0],args[1],args[2],args[3],args[4],args[5]);
				else if(args.length==7)
					enterAllBillingAddressDetails(args[0],args[1],args[2],args[3],args[4],args[5],args[6]);
			}
		}
	}
	
	public void enterAllBillingAddressDetails(String name, String addressLine1, String addressLine2, String city, String state, String zipCode, String country)
	{
		findElement(nameCheckoutTextbox).sendKeys(name);
		findElement(addressLine1Textbox).sendKeys(addressLine1);
		findElement(addressLine2Textbox).sendKeys(addressLine2);
		findElement(cityTextbox).sendKeys(city);
		findElement(stateTextbox).sendKeys(state);
		findElement(zipCodeTextbox).sendKeys(zipCode);
		findElement(countryTextbox).sendKeys(country);
	}
	
	public void enterAllBillingAddressDetails(String name, String addressLine1, String city, String state, String zipCode, String country)
	{
		findElement(nameCheckoutTextbox).sendKeys(name);
		findElement(addressLine1Textbox).sendKeys(addressLine1);
		findElement(cityTextbox).sendKeys(city);
		findElement(stateTextbox).sendKeys(state);
		findElement(zipCodeTextbox).sendKeys(zipCode);
		findElement(countryTextbox).sendKeys(country);
	}
	
	public void clickReviewOrderButton()
	{
		findElement(reviewOrderButton).click();
	}
	
}
