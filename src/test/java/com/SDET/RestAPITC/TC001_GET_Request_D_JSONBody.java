package com.SDET.RestAPITC;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_GET_Request_D_JSONBody {

    @Test
    public void ValidateJsonResponse(){

        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.request(Method.GET,"/api/users?page[2]");
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        Assert.assertTrue(responseBody.contains("george.bluth@reqres.in"));

        // To check JSON Object and JSON Array Object
        JsonPath jsonPath = response.jsonPath();
        int a= jsonPath.get("per_page");
        System.out.println(a);


    }

}
