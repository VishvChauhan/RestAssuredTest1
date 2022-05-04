package com.SDET.testCases;

import com.SDET.base.Logging;
import com.SDET.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;


public class TC001_GetAllUsers extends TestBase implements Logging, ITestListener {

    TestBase testBase;
    String baseURI;
    String serviceURL;
    RequestSpecification httpRequest;
    Response response;

    @BeforeClass
    public void SetUp() throws IOException {
        testBase = new TestBase();
        baseURI= prop.getProperty("BaseURI");
        serviceURL = prop.getProperty("serviceURL");

        getLogger().info("starting TC001_GetAllUsers scenario");
        RestAssured.baseURI = baseURI;
        httpRequest = RestAssured.given();
        response =httpRequest.request(Method.GET,serviceURL);

    }

    @Test(priority = 1) // To get All user
    public void checkResponseBody(){
        getLogger().info("checking response body");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody!=null);
        System.out.println(responseBody);
    }

    @Test(priority = 2)
    public void ValidateStatusCode(){
        getLogger().info("Validate Status Code");
        Assert.assertEquals(response.getStatusCode(), RESPONSE_STATUS_CODE_200);
    }

    @Test(priority = 3)
    public void ValidateResponseTime(){
        getLogger().info("Validate Response Time");
/*        long ResponseTime = response.getTime();
        if(ResponseTime>2000)
            getLogger().info("response time is : " + ResponseTime);*/
        Assert.assertTrue(response.getTime()< 2000);
    }

    @Test(priority = 4)
    public void ValidateStatusLine(){
        getLogger().info("Validate Status Line");
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
    }

    @Test(priority = 5)
    public void ValidateContentType(){
        getLogger().info("Validate Content Type");
        Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
    }

    @Test(priority = 6)
    public void ValidateServerType(){
        getLogger().info("Validate Server Type");
        Assert.assertEquals(response.header("Server"), "cloudflare");
    }

    @Test(priority = 7)
    public void ValidateContentEncoding(){
        getLogger().info("Validate Content Encoding");
        Assert.assertEquals(response.header("Content-Encoding"), "gzip");
    }

    @Test(priority = 8)
    public void ValidateCookies(){
        getLogger().info("Validate Cookies");
        String cookies = String.valueOf(response.getCookies());
        Assert.assertEquals(cookies, cookies);
    }

    @Test(priority = 9)
    public void tearDown(){
        getLogger().info("All Validation done");
    }

}
