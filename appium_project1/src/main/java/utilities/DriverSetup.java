package utilities;

import java.io.File;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class DriverSetup {
	
	private static AppiumDriver driver;
	
	public static AppiumDriver setupDriver(String choice, Map<String,Object> deviceInfo,String app) throws Exception
	{
		
		
		
		if (choice.equalsIgnoreCase("Android"))
		{
			UiAutomator2Options options = new UiAutomator2Options();
			for(Map.Entry<String, Object> option: deviceInfo.entrySet())
			{
				options.setCapability(option.getKey(), option.getValue());
			}
			options.setCapability("appium:app", System.getProperty("user.dir")+"/src/main/resources/"+app);
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		}
		
		
		else if (choice.equalsIgnoreCase("IOS"))
		{
			XCUITestOptions options = new XCUITestOptions();
			for(Map.Entry<String, Object> option: deviceInfo.entrySet())
			{
				options.setCapability(option.getKey(), option.getValue());
			}
			options.setCapability("appium:app", System.getProperty("user.dir")+"/src/main/resources/"+app);
			driver = new IOSDriver(new URL("http://127.0.0.1:4723"),options);
		}
		
		else
		{
			throw new Exception();
		}
		
		return driver;
	}
	
}
