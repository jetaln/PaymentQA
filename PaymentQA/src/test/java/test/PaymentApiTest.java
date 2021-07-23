package test;

import common.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

public class PaymentApiTest extends RestAssuredBase {
    static Logger logger = Logger.getLogger(PaymentApiTest.class);

    @Test
    public void verifyStatusCode(){
        CommonFunctions.setUpResource(
                JsonReader.getResource(PropertyReader.getProperty("resource"), "dprn")
                        +
                        JsonReader.getResource(
                                PropertyReader.getProperty("data"),
                                "dprnNumber")
        );

        HashMap<String, String> headers = Headers.getHeaders("dprnHeaders");
        headers.put("Authorization",headers.get("Authorization")+" "+token);

        CommonFunctions.sendGetRequest(headers);

        System.out.println(CommonFunctions.isStatusCode());

        Assert.assertEquals(CommonFunctions.isStatusCode(),
                Integer.parseInt(JsonReader.getResource(
                PropertyReader.getProperty("status"),"success")));

        logger.info("########TEST SUCCESSFUL############");
    }

    @Test
    public void verifyIdentificationNumber(){
        CommonFunctions.setUpResource(
                JsonReader.getResource(PropertyReader.getProperty("resource"), "dprn")
                        +
                        JsonReader.getResource(
                                PropertyReader.getProperty("data"),
                                "dprnNumber")
        );

        HashMap<String, String> headers = Headers.getHeaders("dprnHeaders");
        headers.put("Authorization",headers.get("Authorization")+" "+token);
        CommonFunctions.sendGetRequest(headers);

        String identificationNumber = CommonFunctions.getResponseData("identificationNumber");
        identificationNumber = identificationNumber.substring(0,identificationNumber.length()-1);

        Assert.assertEquals(identificationNumber,JsonReader.getResource(
                PropertyReader.getProperty("data"),
                "dprnNumber"));

        logger.info("########TEST SUCCESSFUL############");
    }
}
