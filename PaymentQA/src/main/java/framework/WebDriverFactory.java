package framework;

import java.util.concurrent.TimeUnit;

import common.CommonFunctions;
import common.PropertyReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
	static Logger logger = Logger.getLogger(WebDriverFactory.class);

	public static WebDriver driver;
	
	public static WebDriver getWebDriver(){

		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+PropertyReader.getProperty("chromepath"));
		driver = new ChromeDriver();
		logger.info("Launching Application");
		driver.get(PropertyReader.getProperty("appUrl"));
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyReader.getProperty("implicitWait")), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
}
