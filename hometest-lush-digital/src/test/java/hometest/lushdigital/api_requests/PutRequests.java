package hometest.lushdigital.api_requests;

import hometest.lushdigital.model.ProductPojo;
import hometest.lushdigital.testbase.TestBase;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Archita Patel
 */
public class PutRequests extends TestBase {

    @Title("Update Product")
    @Test
    public void updateProduct(){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Orange Curd");
        productPojo.setAvailable(220);

        given().header("Content-Type", "application/json")
                .when()
                .body(productPojo)
                .put("/products/2")
                .then()
                .statusCode(200);
    }
}
