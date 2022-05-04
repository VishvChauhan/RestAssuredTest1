package com.SDET.RestAPITC;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_POST_Request {

    @Test
    public void RegistrationSuccessful(){

        RestAssured.baseURI= "https://reqres.in/api/users";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type","application/json");

        JSONObject requestParam = new JSONObject();
        requestParam.put( "name", "morpheus");
        requestParam.put( "job", "leader");

        httpRequest.body(requestParam.toJSONString());

        //Response object
        Response response = httpRequest.request(Method.POST);
        System.out.println(response);

        //print response in console window
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 201);

        //success code validation
        String name=response.jsonPath().get("name");
        String job=response.jsonPath().get("job");
        String id = response.jsonPath().get("id");

        System.out.println(name + " ; " + job +" ; " + id);
        Assert.assertEquals(name, "morpheus");


    }

}
