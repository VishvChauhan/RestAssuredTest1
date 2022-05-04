package com.SDET.RestAPITC;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_GET_Request_A_CodeAndBody {

    @Test
        void getWeatherDetails(){

        RestAssured.baseURI = "https://reqres.in/api/users?page[2]";

        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET,"");
        System.out.println(response);
        System.out.println("--------------------------");

        //------------      // Json Body------>
        String responseBody = response.getBody().asString();
        //------------      // Json Body Printing ------>
        System.out.println("Response Body" + responseBody);

        //------------- //Status Code----->
        int statusCode = response.getStatusCode();
        System.out.println("statusCode ---->   :" + statusCode);
        Assert.assertEquals(statusCode, 200);

        //------------      // Json Body------>
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
        System.out.println("--------------------------");
        System.out.println(response.getBody().peek() + "   "+ responseBody.contains("per_page"));


        // All Header
        Headers headers = response.getHeaders();
        System.out.println(headers.asList());
        System.out.println( headers.getList("Connection"));
        System.out.println(headers.hasHeaderWithName("Connection"));
        System.out.println(headers.exist() + " : "+  headers.get("Connection")+ " : "+ headers.getValue("Connection"));

        //From JSON Array:-

       int name =  response.jsonPath().get("per_page");
        System.out.println(name);

    }

}
