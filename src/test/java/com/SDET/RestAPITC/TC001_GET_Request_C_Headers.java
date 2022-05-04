package com.SDET.RestAPITC;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TC001_GET_Request_C_Headers {

    @Test
    public void getWeatherDetails(){

        RestAssured.baseURI = "https://www.google.com";
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.request(Method.GET,"/maps/place/Agra,+Uttar+Pradesh/@27.1762542,77.8364869,12z/data=!3m1!4b1!4m12!1m6!3m5!1s0x3bcb96b36a5948a1:0x803e0ff03186745f!2sLocationIQ!8m2!3d20.9880134!4d82.7525294!3m4!1s0x39740d857c2f41d9:0x784aef38a9523b42!8m2!3d27.1764691!4d78.0077362");
        // System.out.println(response);
        String responseBody = response.getBody().asString();


        Headers headers =  response.getHeaders();
        //System.out.println(headers);

        for(Header h : headers){
            if(h.getName().equals("Server")) {
                System.out.println(h.getName() + " : " + h.getValue());
            }
        }
    }
}
