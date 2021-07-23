package common;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredFactory {

    public static String environment;
    public static String host;
    public static RestAssured restAssured;
    public static Response response;
    public static String token;

    public static RestAssured tearUp(){
        if(PropertyReader.getProperty("qa").contains("true")){
            environment=PropertyReader.getProperty("qa");
            host=PropertyReader.getProperty("hostQAUrl");
        }
        else if(PropertyReader.getProperty("staging").contains("true")){
            environment=PropertyReader.getProperty("staging");
            host=PropertyReader.getProperty("hostStagingUrl");
        }
        else if(PropertyReader.getProperty("production").contains("true")){
            environment=PropertyReader.getProperty("production");
            host=PropertyReader.getProperty("hostProductionUrl");
        }

        restAssured.baseURI = host;
        token = CommonFunctions.getAccessToken();
        return restAssured;
    }
}
