package pages;

import io.appium.java_client.AppiumDriver;
import static utilities.AndroidLocators.*;

public class LoginPage extends BasePage {

	public LoginPage(AppiumDriver driver) {
		super(driver);
	}
	
	public void login(String username,String password)
	{
		findElement(usernameTextbox).sendKeys(username);
		findElement(passwordTextbox).sendKeys(password);
		findElement(loginButton).click();
	}
	
	
	public String getErrorMessage()
	{
		return findElement(usernameErrorMessage).getText()+findElement(passwordErrorMessage).getText()+findElement(genericErrorMessage).getText();
	}
}
