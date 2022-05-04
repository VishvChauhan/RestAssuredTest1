package com.SDET.testCases;

import com.SDET.base.Logging;
import com.SDET.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC002_GetUserDetails extends TestBase implements Logging {

    TestBase testBase;
    String baseURI;
    String serviceURL;
    RequestSpecification httpRequest;
    Response response;


    @BeforeClass
    public void setup(){
        testBase = new TestBase();
        baseURI= prop.getProperty("BaseURI");
        serviceURL = prop.getProperty("UserUrl");

        getLogger().info("starting TC001_GetAllUsers scenario");
        RestAssured.baseURI = baseURI;
        httpRequest = RestAssured.given();
        response =httpRequest.request(Method.GET,serviceURL);

    }

    @Test(priority = 1) // To get All user
    public void checkResponseBody(){
        getLogger().info("checking User body");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody!=null);
        System.out.println(responseBody);
    }

    @Test(priority = 2)
    public void ValidateStatusCode(){
        getLogger().info("Validate Status Code");
        Assert.assertEquals(response.getStatusCode(), RESPONSE_STATUS_CODE_200);
    }


    @Test(priority = 9)
    public void tearDown(){
        getLogger().info("All Validation done");
    }


}
