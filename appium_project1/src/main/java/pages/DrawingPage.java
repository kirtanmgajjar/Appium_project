package pages;

import static utilities.AndroidLocators.*;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import io.appium.java_client.AppiumDriver;

public class DrawingPage extends BasePage{
	
	public DrawingPage(AppiumDriver driver)
	{
		super(driver);
	}
	
	public void clearDrawing()
	{
		findElement(clearDrawingButton).click();
	}
	
	public void saveDrawing()
	{
		findElement(saveDrawingButton).click();
//		findElement(grantPermission).click();
		findElement(savedMessageOKButton).click();
	}
	
	public void drawCircle(int radius)
	{
		WebElement drawingPanel = findElement(signaturePad);
		Point location = drawingPanel.getLocation();
		Dimension size = drawingPanel.getSize();
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence draw = new Sequence(finger, 1);
		
		int cx = location.x + size.getWidth()/2;
		int cy = location.y + size.getHeight()/2;
		
		int sx = (int) (radius*Math.cos(0.0) + cx);
		int sy = (int) (radius*Math.sin(0.0) + cy);
		
		if(radius<size.getHeight()/2)
		{
			draw.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),sx,sy));
			draw.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			for(double i=0.0;i<=375.0;i++)
			{
				int x = (int) (radius*Math.cos(i*2*Math.PI/360.0) + cx);
				int y = (int) (radius*Math.sin(i*2*Math.PI/360.0) + cy);
				draw.addAction(finger.createPointerMove(Duration.ofMillis(1), PointerInput.Origin.viewport(),x,y));
			}
		}
		draw.addAction(new Pause(finger, Duration.ofMillis(100)));
		draw.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		
		driver.perform(List.of(draw));
		
	}
	
	public void drawRectangle(int length, int breadth)
	{
		WebElement drawingPanel = findElement(signaturePad);
		Point location = drawingPanel.getLocation();
		Dimension size = drawingPanel.getSize();
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence draw = new Sequence(finger, 1);
		
		int cx = location.x + size.getWidth()/2;
		int cy = location.y + size.getHeight()/2;
		
		draw.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),(int) cx-length/2,(int) cy+breadth/2));
		draw.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		draw.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),(int) cx+length/2,(int) cy+breadth/2));
		draw.addAction(new Pause(finger, Duration.ofMillis(100)));
		draw.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),(int) cx+length/2,(int) cy-breadth/2));
		draw.addAction(new Pause(finger, Duration.ofMillis(100)));
		draw.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),(int) cx-length/2,(int) cy-breadth/2));
		draw.addAction(new Pause(finger, Duration.ofMillis(100)));
		draw.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),(int) cx-length/2,(int) cy+breadth/2));
		draw.addAction(new Pause(finger, Duration.ofMillis(100)));
		draw.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		
		
		driver.perform(List.of(draw));
	}
}
