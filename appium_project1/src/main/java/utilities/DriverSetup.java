package utilities;

import java.net.URL;
import java.util.Map;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;


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
		
		return driver;
	}
	
}
