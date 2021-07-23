package page;

import common.JsonReader;
import common.PropertyReader;
import framework.SolventSelenium;
import framework.YamlReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;

public class PaymentPortalPage extends SolventSelenium {

    public static HashMap<String, String> details;

    public PaymentPortalPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    @FindBy(xpath = "//div[@class='steps']//a[@href='/user/me/apps']")
    public WebElement goToApp;

    @FindBy(xpath = "//a[@class='add-app btn btn-default']")
    public WebElement addApp;

    @FindBy(xpath = "//input[@type='text']")
    public WebElement appName;

    @FindBy(xpath = "(//h2[@class='panel-title highlight'])[1]//a")
    public WebElement nameOfApp;

    @FindBy(xpath = "//div[@class='panel-heading']")
    public List<WebElement> listOfNames;

    @FindBy(xpath = "//div[@class='form-item form-item-api-product form-type-radio radio'][3]")
    public WebElement selectApp;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement create;

    @FindBy(xpath = "//div[@class='steps']//a[@href='/apis']")
    public WebElement goToApi;

    @FindBy(xpath = "//a[text()='FIF Branch API']")
    public WebElement branchApi;

    @FindBy(xpath = "//a[text()='fif-branch-sandbox']")
    public WebElement sandboxApi;

    @FindBy(xpath = "//span[@class='template_param']")
    public WebElement dprn;

    @FindBy(xpath = "//a[@class='link_open_oauth2 replaceOAuthClientCredentialForm-processed']")
    public WebElement auth;

    @FindBy(xpath = "//select[@id='edit-user-app']")
    public WebElement selectApplication;

    @FindBy(xpath = "//button[@id='edit-submit']")
    public WebElement generateToken;

    @FindBy(xpath = "//button[@id='send_request']")
    public WebElement sendRequest;

    @FindBy(xpath = "//a[@id='link_response_tab']")
    public WebElement responseTab;

    @FindBy(xpath = "//div[@id='response-content']//strong")
    public WebElement responseStatus;

    @FindBy(xpath = "//div[@id='response-content']//span[8]")
    public WebElement identificationNumber;

    @FindBy(xpath = "//a[text()='Delete']")
    public WebElement deleteTab;

    @FindBy(xpath = "//button[contains(text(),'Delete')]")
    public WebElement deleteApp;


    public void createApp(){
        details = YamlReader.read("application");

        elementClick(goToApp);
        elementClick(addApp);

        type(details.get("appln"),appName);

        elementClick(selectApp);
        elementClick(create);
    }

    public String requestApiForResponseCode(){
        elementClick(goToApi);
        elementClick(branchApi);
        elementClick(sandboxApi);
        clearThenType(JsonReader.getResource(PropertyReader.getProperty("data"),"dprnNumber"),dprn);
        elementClick(auth);
        waitForElementToBeVisible(selectApplication);
        elementClick(selectApplication);
        selectDropDownByVisibleText(selectApplication,details.get("appln"));
        elementClick(generateToken);
        waitForElementToBeVisible(sendRequest);
        elementClick(sendRequest);
        elementClick(responseTab);
        return getText(responseStatus);
    }

    public String requestApiForIdentificationNumber(){
        return getText(identificationNumber);
    }

    public boolean isAppCreated(String name){
        waitForElementToBeVisible(nameOfApp);
        if(!getText(nameOfApp).contains(details.get("appln")))
            return false;
        return true;
    }

    public boolean isAppDeleted(){
        elementClick(goToApp);
        elementClick(nameOfApp);
        waitForElementToBeVisible(deleteTab);
        elementClick(deleteTab);
        waitForElementToBeVisible(deleteApp);
        elementClick(deleteApp);

        if(getText(nameOfApp).contains(details.get("appln")))
            return false;
        return true;
    }
}
