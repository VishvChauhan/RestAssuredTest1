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

public class TC005_DeleteUserRecord extends TestBase implements Logging {
    TestBase testBase;
    RequestSpecification requestSpecification;
    Response response;
    String baseURI;
    String ServiceUri;

    @BeforeClass

    void deleteUserRecord(){
        getLogger().info("TC005_DeleteUserRecord");
        testBase = new TestBase();
        baseURI = testBase.prop.getProperty("BaseURI");
        ServiceUri =testBase.prop.getProperty("UserUrl");
        RestAssured.baseURI = baseURI;
        requestSpecification = RestAssured.given();
        response = requestSpecification.request(Method.DELETE,ServiceUri);
    }

    @Test
    void validateUserDeletion(){
        String responsebody = response.getBody().asString();
        System.out.println(responsebody);
        Assert.assertEquals(responsebody.contains("s"), false);
    }

    @Test
    public void checkStatusCode(){
        Assert.assertEquals(response.getStatusCode(),RESPONSE_STATUS_CODE_204 );
    }
}
