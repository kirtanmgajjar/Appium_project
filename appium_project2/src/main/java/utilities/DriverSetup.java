package utilities;

import java.io.File;
import java.net.URL;
import java.util.Map;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;


public class DriverSetup {
	
	private static AppiumDriver driver;
	
	public static void startDriver(String OS, String device, String app) throws Exception
	{
		
		Map<String,Object> deviceInfo = readDeviceInfo(device);
		Map<String,Object> appInfo = readAppInfo(app);
		
		if (OS.equalsIgnoreCase("Android"))
		{
			UiAutomator2Options options = new UiAutomator2Options();
			
			for(Map.Entry<String, Object> option: deviceInfo.entrySet())
			{
				options.setCapability("appium:"+option.getKey(), option.getValue());
			}
//			options.setCapability("appium:app", System.getProperty("user.dir")+"/src/main/resources/"+app);
			options.setCapability("appium:appPackage", appInfo.get("appPackage"));
			options.setCapability("appium:appActivity", appInfo.get("appActivity"));
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		}
		
		
		else if (OS.equalsIgnoreCase("IOS"))
		{
			XCUITestOptions options = new XCUITestOptions();
			for(Map.Entry<String, Object> option: deviceInfo.entrySet())
			{
				options.setCapability(option.getKey(), option.getValue());
			}
			options.setCapability("appium:bundleId", appInfo.get("bundleId"));
			driver = new IOSDriver(new URL("http://127.0.0.1:4723"),options);
		}
		
		AppDriver.setDriver(driver);
		
	}
	
	private static Map<String,Object> readDeviceInfo(String device) throws Exception
	{
		JSONReader reader = new JSONReader(new File(System.getProperty("user.dir")+"/src/main/resources/devices.json"));
		return reader.getObjectData(device);
	}
	private static Map<String,Object> readAppInfo(String app) throws Exception
	{
		JSONReader reader = new JSONReader(new File(System.getProperty("user.dir")+"/src/main/resources/appInfo.json"));
		return reader.getObjectData(app);
	}
	
}
