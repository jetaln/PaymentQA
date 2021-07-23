package test;

import common.CommonFunctions;
import common.JsonReader;
import common.PropertyReader;
import framework.TestBase;
import framework.YamlReader;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import page.PaymentPortalPage;

import java.util.HashMap;

public class PaymentPortalTest extends TestBase {
    public static HashMap<String, String> details;
    static Logger logger = Logger.getLogger(PaymentPortalTest.class);

    @Test(priority = 1)
    public void verifyCreateApp(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginIntoApps();

        PaymentPortalPage paymentPortalPage = new PaymentPortalPage(driver);
        paymentPortalPage.createApp();

        details = YamlReader.read("application");
        Assert.assertTrue(paymentPortalPage.isAppCreated(details.get("appln")),"App creation is failed");

        logger.info("########TEST SUCCESSFUL############");
    }

    @Test(priority = 2)
    public void verifySandboxApi(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginIntoApps();

        PaymentPortalPage paymentPortalPage = new PaymentPortalPage(driver);
        Assert.assertTrue(paymentPortalPage.requestApiForResponseCode().contains("200"),"Status code is not 200");

        String identificationNumber = paymentPortalPage.requestApiForIdentificationNumber();
        identificationNumber = identificationNumber.substring(1,identificationNumber.length()-2);
        Assert.assertEquals(identificationNumber, JsonReader.getResource(PropertyReader.getProperty("data"),"dprnNumber"));

        logger.info("########TEST SUCCESSFUL############");
    }

    @Test(priority = 3)
    public void verifyDeleteApp(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginIntoApps();

        PaymentPortalPage paymentPortalPage = new PaymentPortalPage(driver);
        Assert.assertTrue(paymentPortalPage.isAppDeleted(),"App is not deleted");

        logger.info("########TEST SUCCESSFUL############");
    }
}
