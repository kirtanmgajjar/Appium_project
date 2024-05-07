package tests;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumTest2 {
	
	static AndroidDriver driver;
	static WebDriverWait wait;
	static AppiumDriverLocalService service;
	@BeforeTest
	public void setup() throws Exception
	{
		service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\2234444\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).withArgument(()->"--use-plugins=gestures").build();

//AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\2234444\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//.withIPAddress("127.0.0.1").usingAnyFreePort().build();
service.start();
UiAutomator2Options options = new UiAutomator2Options();

options.setCapability("appium:deviceName","Pixel 7");
//options.setCapability("app", System.getProperty("user.dir")+"/src/test/java/resources/POP SnF.apk");
//options.setCapability("appium:app", System.getProperty("user.dir")+"/src/test/java/resources/ApiDemos-debug.apk");
options.setCapability("appium:platformName", "Android");
options.setCapability("appium:platformVersion", "12.0");

options.setCapability("app", System.getProperty("user.dir")+"/src/main/resources/Wdio_app.apk");
driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	@AfterTest(alwaysRun = true)
	public void tearDown()
	{
		service.stop();
	}
	
	@Test(enabled=true)
	public void test1()
	{
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.accessibilityId("Swipe")))).click();
		tap(wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.accessibilityId("Swipe")))));
		WebElement el = wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.accessibilityId("Carousel"))));
//		scrollWithPlugin(el,"left");
//		scrollWithPlugin(wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.accessibilityId("Swipe-screen")))),"up");
		swipe(el,"left");
		scroll("up");
//		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.accessibilityId("Drag")))).click();
//		String[] loc = {"l","c","r"};
//		for(int i=0;i<3;i++) {
//			for(int j=1;j<=3;j++) {
//				WebElement source = wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.accessibilityId("drag-"+loc[i]+j))));
//				WebElement destination = wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.accessibilityId("drop-"+loc[i]+j))));
//				dragAndDropWithPlugin(source, destination);
//			}
//		}
		
//		goToRecentApp();
		switchApps("right");
	}
	
	@Ignore
	@Test
	public void test2()
	{
		driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
		goToRecentApp();
	}
	
	public static void scrollWithPlugin(WebElement el,String direction)
	{
		driver.executeScript("gesture: swipe", Map.of("elementId", ((RemoteWebElement) el).getId(), "percentage", 50, "direction", direction));
	}
	
	public static void dragAndDropWithPlugin(WebElement source, WebElement destination)
	{
		driver.executeScript("gesture: dragAndDrop", Map.of("sourceId", ((RemoteWebElement) source).getId(), "destinationId", ((RemoteWebElement) destination).getId()));
	}
	
	public static void tap(WebElement el)
	{
		Point loc = el.getLocation();
		Dimension size = el.getSize();
		int cx = loc.getX()+size.getWidth()/2;
		int cy = loc.getY()+size.getHeight()/2;
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), cx, cy));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(List.of(tap));	
	}
	
	public static void doubleTap(WebElement el)
	{
		Point loc = el.getLocation();
		Dimension size = el.getSize();
		int cx = loc.getX()+size.getWidth()/2;
		int cy = loc.getY()+size.getHeight()/2;
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence doubleTap = new Sequence(finger, 1);
		doubleTap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), cx, cy));
		doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		doubleTap.addAction(new Pause(finger, Duration.ofMillis(100)));
		doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		doubleTap.addAction(new Pause(finger, Duration.ofMillis(100)));
		doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		doubleTap.addAction(new Pause(finger, Duration.ofMillis(100)));
		doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(List.of(doubleTap));	
	}
	
	public static void scroll(String direction)
	{
		int sx = driver.manage().window().getSize().getWidth()/2;
		int sy = driver.manage().window().getSize().getHeight()/2;
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence scroll = new Sequence(finger, 0);
		
		scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),sx,sy));
		scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		
		int cy=0;
		
		if(direction.equalsIgnoreCase("up"))
			cy = (int) (driver.manage().window().getSize().getHeight()*0.2);
		else if(direction.equalsIgnoreCase("down"))
			cy = (int) (driver.manage().window().getSize().getHeight()*0.8);
		
		scroll.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),sx,cy));
		scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(List.of(scroll));	
	}
	
	
	public static void swipe(WebElement el,String direction)
	{
		Point loc = el.getLocation();
		Dimension size = el.getSize();
		
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 0);
		int sx=0,sy=0,cx=0;
		if(direction.equalsIgnoreCase("left"))
		{
			sx = (int) (loc.getX()+size.getWidth()*0.8);
			sy = (int) (loc.getY()+size.getHeight()*0.8);
			cx = (int) (loc.getX()+size.getWidth()*0.2);
		}
		else if(direction.equalsIgnoreCase("right"))
		{
			sx = (int) (loc.getX()+size.getWidth()*0.2);
			sy = (int) (loc.getY()+size.getHeight()*0.2);
			cx = (int) (loc.getX()+size.getWidth()*0.8);
		}
		
		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),sx,sy));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),cx,sy));
		driver.perform(List.of(swipe));
	}
	
	
	public static void dragAndDrop(WebElement sourceElement, WebElement destinationElement)
	{
		Point source = sourceElement.getLocation();
		Dimension sSize = sourceElement.getSize();
		int sx = source.getX()+sSize.getWidth()/2;
		int sy = source.getY()+sSize.getHeight()/2;
		
		Point destination = destinationElement.getLocation();
		Dimension dSize = destinationElement.getSize();
		int dx = destination.getX()+dSize.getWidth()/2;
		int dy = destination.getY()+dSize.getHeight()/2;
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence dragAndDrop = new Sequence(finger, 1);
		dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), sx, sy));
		dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),dx,dy));
		dragAndDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(List.of(dragAndDrop));
	}
	
	public static void goToRecentApp()
	{
		WebElement navigationBar = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("android:id/navigationBarBackground"))));
		Point loc = navigationBar.getLocation();
		Dimension size = navigationBar.getSize();
		int cx = loc.getX()+size.getWidth()/2;
		int cy = loc.getY()+size.getHeight()/2;
		int dy = (int) (driver.manage().window().getSize().getHeight()*0.2);
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence recentApps = new Sequence(finger, 1);
		recentApps.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), cx, cy));
		recentApps.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		recentApps.addAction(new Pause(finger, Duration.ofMillis(100)));
		recentApps.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		recentApps.addAction(new Pause(finger, Duration.ofMillis(100)));
		recentApps.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		recentApps.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),cx,dy));
		recentApps.addAction(new Pause(finger, Duration.ofMillis(500)));
		recentApps.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(List.of(recentApps));
	}
	
	public static void goToHomeScreen()
	{
		WebElement navigationBar = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("android:id/navigationBarBackground"))));
		Point loc = navigationBar.getLocation();
		Dimension size = navigationBar.getSize();
		int cx = loc.getX()+size.getWidth()/2;
		int cy = loc.getY()+size.getHeight()/2;
		int dy = (int) (driver.manage().window().getSize().getHeight()*0.2);
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence homeScreen = new Sequence(finger, 1);
		homeScreen.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), cx, cy));
		homeScreen.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		homeScreen.addAction(new Pause(finger, Duration.ofMillis(100)));
		homeScreen.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		homeScreen.addAction(new Pause(finger, Duration.ofMillis(100)));
		homeScreen.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		homeScreen.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),cx,dy));
		homeScreen.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(List.of(homeScreen));
	}
	
	public static void switchApps(String direction)
	{
		WebElement navigationBar = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("android:id/navigationBarBackground"))));
		Point loc = navigationBar.getLocation();
		Dimension size = navigationBar.getSize();
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence switchApps = new Sequence(finger, 0);
		int cx = loc.getX()+size.getWidth()/2;
		int cy = loc.getY()+size.getHeight()/2;
		int x=0;
		if(direction.equalsIgnoreCase("left"))
		{
			x = (int) (loc.getX()+size.getWidth()*0.05);
		}
		else if(direction.equalsIgnoreCase("right"))
		{
			x = (int) (loc.getX()+size.getWidth()*0.95);
		}
		
		switchApps.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),cx,cy));
		switchApps.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		switchApps.addAction(finger.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(),x,cy));
		switchApps.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(List.of(switchApps));
	}
}
