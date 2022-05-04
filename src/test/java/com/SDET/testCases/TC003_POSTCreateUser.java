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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.SDET.utilities.RestUtils.userName;

public class TC003_POSTCreateUser extends TestBase implements Logging {

    TestBase testBase;
    RequestSpecification requestSpecification;
    Response response;
    String baseURI;
    String ServiceUri;

    @DataProvider(name = "createUser")
    public Object[][] testData(){
        return new Object[][]{
                {"morpheus", "leader"},
                {"vishu", "boss"}
        };
    }


    @BeforeClass
    public  void SetUp(){
        testBase = new TestBase();
        baseURI = testBase.prop.getProperty("BaseURI");
        ServiceUri = testBase.prop.getProperty("ServiceURI");
        RestAssured.baseURI = baseURI;
        requestSpecification = RestAssured.given();


    }

    @Test(dataProvider ="createUser" )
    public void createNewUsers(String name, String job){

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", userName(name));
        jsonObject1.put("job", job);
        requestSpecification.header("Content-Type","application/json");
        requestSpecification.body(jsonObject1.toJSONString());

        response = requestSpecification.request(Method.POST,ServiceUri);
        System.out.println(response);
        System.out.println(response.getBody().asString());

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 201);

        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(name),true);
        Assert.assertEquals(responseBody.contains(job),true);

    }

    @Test(priority = 9)
    public void tearDown(){
        getLogger().info("All Validation done");
    }

}
