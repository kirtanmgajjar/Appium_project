package utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

public class MobileUtils {
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
		AppDriver.getInstace().getDriver().perform(List.of(tap));	
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
		AppDriver.getInstace().getDriver().perform(List.of(doubleTap));	
	}
	
	public void scroll(String direction)
	{
		int sx = AppDriver.getInstace().getDriver().manage().window().getSize().getWidth()/2;
		int sy = AppDriver.getInstace().getDriver().manage().window().getSize().getHeight()/2;
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence scroll = new Sequence(finger, 0);
		
		scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),sx,sy));
		scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		
		int cy=0;
		
		if(direction.equalsIgnoreCase("up"))
			cy = (int) (AppDriver.getInstace().getDriver().manage().window().getSize().getHeight()*0.2);
		else if(direction.equalsIgnoreCase("down"))
			cy = (int) (AppDriver.getInstace().getDriver().manage().window().getSize().getHeight()*0.8);
		
		scroll.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),sx,cy));
		scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		AppDriver.getInstace().getDriver().perform(List.of(scroll));	
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
		AppDriver.getInstace().getDriver().perform(List.of(swipe));
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
		AppDriver.getInstace().getDriver().perform(List.of(dragAndDrop));
	}
	
	public void goToRecentApp()
	{
		WebElement navigationBar = AppDriver.getInstace().getDriver().findElement(By.id("android:id/navigationBarBackground"));
		Point loc = navigationBar.getLocation();
		Dimension size = navigationBar.getSize();
		int cx = loc.getX()+size.getWidth()/2;
		int cy = loc.getY()+size.getHeight()/2;
		int dy = (int) (AppDriver.getInstace().getDriver().manage().window().getSize().getHeight()*0.2);
		
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
		AppDriver.getInstace().getDriver().perform(List.of(recentApps));
	}
	
	public void goToHomeScreen()
	{
		WebElement navigationBar = AppDriver.getInstace().getDriver().findElement(By.id("android:id/navigationBarBackground"));
		Point loc = navigationBar.getLocation();
		Dimension size = navigationBar.getSize();
		int cx = loc.getX()+size.getWidth()/2;
		int cy = loc.getY()+size.getHeight()/2;
		int dy = (int) (AppDriver.getInstace().getDriver().manage().window().getSize().getHeight()*0.2);
		
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
		AppDriver.getInstace().getDriver().perform(List.of(homeScreen));
	}
	
	public void switchApps(String direction)
	{
		WebElement navigationBar = AppDriver.getInstace().getDriver().findElement(By.id("android:id/navigationBarBackground"));
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
		AppDriver.getInstace().getDriver().perform(List.of(switchApps));
	}
	
	public void scrollTillLast()
	{
		String pageSource = AppDriver.getInstace().getDriver().getPageSource();
		scroll("up");
		while(true)
		{
			if(pageSource.equals(AppDriver.getInstace().getDriver().getPageSource()))
				break;
			
			scroll("up");			
			pageSource = AppDriver.getInstace().getDriver().getPageSource();
		}
	}
	
	public enum ScrollDirection
	{
		UP, DOWN, LEFT, RIGHT;
	}
}
