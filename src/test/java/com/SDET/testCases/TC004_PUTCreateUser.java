package com.SDET.testCases;

import com.SDET.base.Logging;
import com.SDET.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.SDET.utilities.RestUtils;

public class TC004_PUTCreateUser extends TestBase implements Logging {

    TestBase testBase;
    RequestSpecification requestSpecification;
    Response response;
    String baseURI;
    String ServiceUri;

    String UserName = RestUtils.userName("Vishu");

    @BeforeClass
    public void updateEmployee() throws InterruptedException {
        getLogger().info("Testing TC004_PUTCreateUser");
        testBase = new TestBase();
        baseURI = testBase.prop.getProperty("BaseURI");
        ServiceUri = testBase.prop.getProperty("UserUrl");
        RestAssured.baseURI = baseURI;
        requestSpecification = RestAssured.given();


        JSONObject requestParam = new JSONObject();
        requestParam.put("name", UserName);
        requestParam.put("job", "zion resident");
        requestSpecification.header("Content-Type","application/json");
        requestSpecification.body(requestParam.toJSONString());
        response = requestSpecification.request(Method.PUT,ServiceUri);
        Thread.sleep(5);

    }
    @Test
    public void checkResponseBody(){
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(UserName), true);
        Assert.assertEquals(responseBody.contains("zion resident"),true);
    }

    @Test
    public void checkStatusCode(){
        Assert.assertEquals(response.getStatusCode(),RESPONSE_STATUS_CODE_200 );
    }

    @Test
    public void checkStatusLine(){
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
    }


    @Test(priority = 9)
    public void tearDown(){
        getLogger().info("All Validation done");
    }

}
