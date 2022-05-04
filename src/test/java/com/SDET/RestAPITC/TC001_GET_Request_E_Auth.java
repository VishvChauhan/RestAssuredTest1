package com.SDET.RestAPITC;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_GET_Request_E_Auth {

    @Test
    public void validationWithAuth(){

        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.request(Method.GET, "/api/users?page[2]");

        //for Authentication
        PreemptiveBasicAuthScheme preemptiveBasicAuthScheme = new PreemptiveBasicAuthScheme();
        preemptiveBasicAuthScheme.setUserName("hello");
        preemptiveBasicAuthScheme.setPassword("abc");
        RestAssured.authentication =preemptiveBasicAuthScheme;

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);


    }

}
