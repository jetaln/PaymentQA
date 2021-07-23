package framework;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class TestBase {
	protected WebDriver driver;

	@BeforeMethod
	public WebDriver setUp(){
		driver = WebDriverFactory.getWebDriver();
		return driver;
		
	}
	@AfterMethod
	public void exitBrowser(){
		driver.quit();
	}
}
