package common;

import org.testng.annotations.BeforeTest;

public class RestAssuredBase extends RestAssuredFactory {

    @BeforeTest
    public void runBeforeEachApiTest(){
        RestAssuredFactory.tearUp();
    }
}
