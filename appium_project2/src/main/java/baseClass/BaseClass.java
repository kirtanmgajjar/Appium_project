package baseClass;

import java.io.File;
import java.io.InputStream;
import java.time.Duration;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import pages.CatalogPage;
import utilities.DriverSetup;
import utilities.JSONReader;
import utilities.PropertiesFileReader;

public class BaseClass {

	private InputStream config;
	private PropertiesFileReader reader;
	private File devices;
	protected AppiumDriver driver;
	private JSONReader jReader;
	private AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\2234444\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
			.withIPAddress("127.0.0.1").usingPort(4723).build();
	
	@BeforeSuite
	public void setup() throws Exception
	{
		
		config = this.getClass().getResourceAsStream("/config.properties");
		reader = new PropertiesFileReader(config);
		String OS = reader.getValue("OS");
		String device = reader.getValue("device");
		String app = reader.getValue("app");
		String infoFile = reader.getValue("infoFile");
		devices = new File(System.getProperty("user.dir")+"/src/main/resources/"+infoFile);
		
		jReader = new JSONReader(devices);
		service.start();
		driver = DriverSetup.setupDriver(OS, jReader.getDeviceInfo(device),app);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.println(driver.getCapabilities());
	}
	
	
	
	@AfterSuite(alwaysRun = true)
	public void tearDown()
	{
		service.stop();
	}
}
