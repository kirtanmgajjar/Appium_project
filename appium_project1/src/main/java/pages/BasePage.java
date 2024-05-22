package pages;

import static utilities.AndroidLocators.menuIcon;
import static utilities.AndroidLocators.webviewMenu;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;


public class BasePage {
	
	public AppiumDriver driver;
	public WebDriverWait wait;
	
	public BasePage(AppiumDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	
	public WebElement findElement(String locator)
	{
		String[] loc = locator.split(";");
		if (loc[0].equals("accessibility id"))
			return driver.findElement(AppiumBy.accessibilityId(loc[1]));
		else if(loc[0].equals("class"))
			return driver.findElement(AppiumBy.className(loc[1]));
		else if(loc[0].equals("id"))
			return driver.findElement(AppiumBy.id(loc[1]));
		else if(loc[0].equals("name"))
			return driver.findElement(AppiumBy.name(loc[1]));
		else if(loc[0].equals("xpath"))
			return driver.findElement(AppiumBy.xpath(loc[1]));
		else if(loc[0].equals("UiSelector"))
			return driver.findElement(AppiumBy.androidUIAutomator(loc[1]));
		else
			throw new NoSuchElementException();
		
	}
	
	public List<WebElement> findElements(String locator)
	{
		
		String[] loc = locator.split(";");
		if (loc[0].equals("accessibility id"))
			return driver.findElements(AppiumBy.accessibilityId(loc[1]));
		else if(loc[0].equals("class"))
			return driver.findElements(AppiumBy.className(loc[1]));
		else if(loc[0].equals("id"))
			return driver.findElements(AppiumBy.id(loc[1]));
		else if(loc[0].equals("name"))
			return driver.findElements(AppiumBy.name(loc[1]));
		else if(loc[0].equals("xpath"))
			return driver.findElements(AppiumBy.xpath(loc[1]));
		else if(loc[0].equals("UiSelector"))
			return driver.findElements(AppiumBy.androidUIAutomator(loc[1]));
		else
			return null;
		
	}
	
	
	public void waitForVisible(WebElement... el)
	{
		if(el.length==1)
		wait.until(ExpectedConditions.visibilityOf(el[0]));
		else
			wait.until(ExpectedConditions.visibilityOf(el[0]));
	}
	
	public void waitForPresence(String locator)
	{
		By location=null;
		String[] loc = locator.split(";");
		if (loc[0].equals("accessibility id"))
			location = AppiumBy.accessibilityId(loc[1]);
		else if(loc[0].equals("class"))
			location = AppiumBy.className(loc[1]);
		else if(loc[0].equals("id"))
			location = AppiumBy.id(loc[1]);
		else if(loc[0].equals("name"))
			location = AppiumBy.name(loc[1]);
		else if(loc[0].equals("xpath"))
			location = AppiumBy.xpath(loc[1]);
		else if(loc[0].equals("UiSelector"))
			location = AppiumBy.androidUIAutomator(loc[1]);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(location));
	}
	
	public By getByObject(String locator)
	{
		By location=null;
		String[] loc = locator.split(";");
		if (loc[0].equals("accessibility id"))
			location = AppiumBy.accessibilityId(loc[1]);
		else if(loc[0].equals("class"))
			location = AppiumBy.className(loc[1]);
		else if(loc[0].equals("id"))
			location = AppiumBy.id(loc[1]);
		else if(loc[0].equals("name"))
			location = AppiumBy.name(loc[1]);
		else if(loc[0].equals("xpath"))
			location = AppiumBy.xpath(loc[1]);
		else if(loc[0].equals("UiSelector"))
			location = AppiumBy.androidUIAutomator(loc[1]);
		
		return location;
	}
	
	public void waitForClickable(WebElement el)
	{
		wait.until(ExpectedConditions.elementToBeClickable(el));
	}
	
	public void tap(WebElement el)
	{
		Point loc = el.getLocation();
		Dimension size = el.getSize();
		int cx = loc.getX()+size.getWidth()/2;
		int cy = loc.getY()+size.getHeight()/2;
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), cx, cy));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(List.of(tap));	
	}
	
	public void doubleTap(WebElement el)
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
	
	public void scroll(String direction)
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
	
	
	public void swipe(WebElement el,String direction)
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
	
	
	public void dragAndDrop(WebElement sourceElement, WebElement destinationElement)
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
	
	public void goToRecentApp()
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
	
	public void goToHomeScreen()
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
	
	public void switchApps(String direction)
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
	
	public void clickMenu()
	{
		waitForClickable(findElement(menuIcon));
		findElement(menuIcon).click();
		waitForVisible(findElement(webviewMenu));
	}
	
	public void scrollTillLast()
	{
		String pageSource = driver.getPageSource();
		scroll("up");
		while(true)
		{
			if(pageSource.equals(driver.getPageSource()))
				break;
			
			scroll("up");			
			pageSource = driver.getPageSource();
		}
	}
}
