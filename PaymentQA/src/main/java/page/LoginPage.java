package page;

import framework.SolventSelenium;
import framework.YamlReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;

public class LoginPage extends SolventSelenium {

    public LoginPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    HashMap<String, String> details;

    @FindBy(xpath="(//div[@class='container'])[1]//div//ul//li[2]")
    public WebElement loginButton;

    @FindBy(xpath="(//div[@class='modal-body'])[1]//input[1]")
    public WebElement userName;

    @FindBy(xpath="(//div[@class='modal-body'])[1]//input[@type='password']")
    public WebElement password;

    @FindBy(xpath="(//div[@class='modal-body'])[1]//button[@type='submit']")
    public WebElement login;

    public void loginIntoApps(){
        details = YamlReader.read("application");

        waitForElementToBeVisible(loginButton);
        elementClick(loginButton);
        waitForElementToBeVisible(userName);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        type(details.get("user"),userName);
        waitForElementToBeVisible(password);

        type(details.get("password"),password);
        waitForElementToBeVisible(password);

        enter(password);
    }
}
