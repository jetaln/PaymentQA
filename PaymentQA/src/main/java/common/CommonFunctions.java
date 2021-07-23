package common;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class CommonFunctions extends RestAssuredFactory{
    static Logger logger = Logger.getLogger(CommonFunctions.class);

    public static void setUpHeaders(HashMap<String, String> headers){
        restAssured.given().headers(headers);
    }

    public static void sendGetRequest(HashMap<String, String> headers){
        response = restAssured.given().headers(headers).when().get();
    }

    public static void setUpResource(String resource){
        restAssured.basePath = resource;
    }

    public static int isStatusCode(){
        return response.getStatusCode();
    }

    public static String getAccessToken(){
        logger.info("Getting access token");

        return restAssured.given()
                .headers(Headers.getHeaders("tokenHeaders"))
                .formParams(Headers.getHeaders("tokenParams"))
                .when().
                        post(JsonReader.getResource(PropertyReader.getProperty("resource"),"accesstoken"))
                .then().
                        extract().
                        response().jsonPath().get(PropertyReader.getProperty("token"));
    }

    public static String getResponseData(String value){
        return response.jsonPath().get(value);
    }
}
