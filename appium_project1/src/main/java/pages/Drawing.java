package pages;

import io.appium.java_client.AppiumDriver;
import static utilities.AndroidLocators.*;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class Drawing extends BasePage{
	
	public Drawing(AppiumDriver driver)
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
	}
	
	public void draw()
	{
		WebElement drawingPanel = findElement(signaturePad);
		Point location = drawingPanel.getLocation();
		Dimension size = drawingPanel.getSize();
		Point pSource = new Point(location.x+size.getWidth()/2,location.y+size.getHeight()+10);
		Point pDest = new Point(location.x+size.getWidth()/2,location.y+size.getHeight()-10);
		
	}
}
