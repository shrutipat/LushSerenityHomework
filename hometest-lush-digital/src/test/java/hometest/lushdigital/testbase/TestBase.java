package hometest.lushdigital.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * Created by Archita Patel
 */
public class TestBase {

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI = "http://localhost:8080";
    }
}
