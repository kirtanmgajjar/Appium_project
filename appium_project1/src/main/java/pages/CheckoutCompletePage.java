package pages;


import io.appium.java_client.AppiumDriver;
import static utilities.AndroidLocators.*;

import org.openqa.selenium.WebElement;

public class CheckoutCompletePage extends BasePage {

	public CheckoutCompletePage(AppiumDriver driver) {
		super(driver);
	}
	
	public String getCheckoutCompleteMessage()
	{
		WebElement message = findElement(checoutCompleteMessage);
		waitForVisible(message);
		return message.getText();
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
