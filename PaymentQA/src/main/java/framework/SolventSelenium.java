package framework;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SolventSelenium {

	public WebDriver driver;
	
	public SolventSelenium(WebDriver driver){
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/test/resources/log4j.properties");
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	
	/*
	 * FUNCTION : To click to any Web Element
	 * */
	public void elementClick(WebElement element){
		element.click();
	}
	
	public void type(String data, WebElement element){
		element.sendKeys(data);
	}

	public void clearThenType(String data, WebElement element){
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(data);
	}

	public void enter(WebElement element){
		element.sendKeys(Keys.ENTER);
	}

	public void waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * To select a value from drop down box
	 *
	 * @param value
	 *            :value to be selected
	 */
	public void selectDropDownByVisibleText(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public String getText(WebElement element){return element.getText();}

	public boolean isElementDisplayed(WebElement element){
		return element.isDisplayed();
	}
}
