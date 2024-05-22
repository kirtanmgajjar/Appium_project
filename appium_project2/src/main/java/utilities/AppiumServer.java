package utilities;

import java.io.File;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServer {
	
	static AppiumDriverLocalService server;
	
	private static boolean useGesturePlugin=false;
	public static void setInstance()
	{
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		
		builder.withAppiumJS(new File("C:\\Users\\2234444\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723);
		
		if(useGesturePlugin)
			builder.withArgument(()->"--use-plugins=gestures");
		
		server = AppiumDriverLocalService.buildService(builder);
	}
	
	public static void useGesturePlugin()
	{
		useGesturePlugin=true;
	}
	
	public static AppiumDriverLocalService getInstance()
	{
		if(server==null)
			setInstance();
			
		return server;
	}
	
	public static void start()
	{
		getInstance().start();
	}
	
	public static void stop()
	{
		if(server!=null)
			getInstance().stop();
	}
	
}
