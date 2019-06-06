package hometest.lushdigital.api_requests;

import hometest.lushdigital.model.LoginPojo;
import hometest.lushdigital.model.OrderPojo;
import hometest.lushdigital.model.ProductPojo;
import hometest.lushdigital.testbase.TestBase;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Created by Archita Patel
 */
public class PostRequests extends TestBase {

    @Title("Login User")
    @Test
    public void loginUser(){
        LoginPojo loginPojo = new LoginPojo();
        loginPojo.setUsername("alice");
        loginPojo.setPassword("a37d3106168770fa4477903703c548c7010ea570");

        given().header("Content-Type", "application/json")
                .when()
                .body(loginPojo)
                .post("/login")
                .then()
                .statusCode(200);
    }

    @Title("Create Product")
    @Test
    public void createProduct(){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Marmalade");
        productPojo.setAvailable(180);

        given().header("Content-Type", "application/json")
                .when()
                .body(productPojo)
                .post("/products")
                .then()
                .statusCode(200);
    }

    @Title("Verify That Product Is Created")
    @Test
    public void verifyThatProductIsCreated() {

        List<String> product = given()
                .when()
                .get("/products")
                .then()
                .statusCode(404)        //this status code should appear as Maggie product does not exist, but actual status code is 200 (BUG!)
                .extract()
                .path("data.products.findAll{it.name=='Marmalade'}");

        if (product.size()>0){
            System.out.println("Product is created successfully");
        }
        else System.out.println("Product does not exist");
    }

    @Title("Buy Product By ID")
    @Test
    public void buyProductByID(){
        OrderPojo orderPojo = new OrderPojo();
        orderPojo.setUser_id(1);
        orderPojo.setAmount(20);

        given().header("Content-Type", "application/json")
                .when()
                .body(orderPojo)
                .post("/products/3")
                .then()
                .statusCode(200);
    }

   /* @Title("Verify that buying product works")
    @Test
    public void verifyThatBuyingProductWorks(){
         int quantity = given()
                .when()
                .get("/products")
                .then()
                .extract()
                .path("data.products.findAll{it.name=='Marmalade'}.available");
        System.out.println(quantity);
    }*/
}
