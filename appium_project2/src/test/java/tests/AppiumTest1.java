package tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import static utilities.AndroidLocators.*;
public class AppiumTest1 {
	
	@Test
	public void appiumTest() throws MalformedURLException
	{
		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\2234444\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
									.withIPAddress("127.0.0.1").usingPort(4723).build();
		
//		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\2234444\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//				.withIPAddress("127.0.0.1").usingAnyFreePort().build();
		
		service.start();
		UiAutomator2Options options = new UiAutomator2Options();
		
		options.setCapability("appium:deviceName","Pixel 7");
		//options.setCapability("app", System.getProperty("user.dir")+"/src/test/java/resources/POP SnF.apk");
//		options.setCapability("appium:app", System.getProperty("user.dir")+"/src/test/java/resources/ApiDemos-debug.apk");
		options.setCapability("appium:platformName", "Android");
		options.setCapability("appium:platformVersion", "12.0");
		
		options.setCapability("appium:app", System.getProperty("user.dir")+"/src/main/resources/MyDemoApp.apk");
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
//		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
//		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
//		for(WebElement i: driver.findElements(By.className("android.widget.RelativeLayout")))
//		{
//			i.click();
//		}
//		driver.findElement(By.id("android:id/edit")).sendKeys("Abc");
//		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(AppiumBy.accessibilityId("open menu")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.accessibilityId("menu item webview"))));
		Reporter.log(driver.findElements(AppiumBy.xpath("//android.view.ViewGroup[contains(@content-desc,'menu item')]/android.widget.TextView")).stream().map(k->k.getText()).toList().toString());
		Reporter.log(driver.findElements(AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"menu item\").childSelector(new UiSelector().className(\"android.widget.TextView\"))")).stream().map(k->k.getText()).toList().toString());
		
		
		service.stop();
	}
}
