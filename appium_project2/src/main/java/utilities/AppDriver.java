package utilities;

import io.appium.java_client.AppiumDriver;

public class AppDriver {
	private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
	private static AppDriver instance = null;
	
	public static AppDriver getInstace()
	{
		if(instance==null)
			instance = new AppDriver();
		
		return instance;
	}
	
	public AppiumDriver getDriver()
	{
		return driver.get();
	}
	
	public static AppiumDriver getCurrentDriver()
	{
		return getInstace().getDriver();
	}
	
	public static void setDriver(AppiumDriver Driver)
	{
		driver.set(Driver);
	}
}
