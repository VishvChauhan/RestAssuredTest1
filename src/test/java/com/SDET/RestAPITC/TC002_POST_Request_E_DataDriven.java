package com.SDET.RestAPITC;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC002_POST_Request_E_DataDriven {


    @DataProvider(name = "testData")
    public Object[][] testDataTest(){
        return new Object[][]{
               {  "morpheus","leader"}, { "vishu","boss" }
        };
    }


// User this for data from Excel file.
/*    @DataProvider(name = "testData1")
    public Object[][] testDataTestExcel(){
        String path = System.getProperty("user.dir") + "";
        int rownum = XLUtil.getRowCount(path, "Sheet1");
        int colCount = XLUtil.getCellCount(path, "Sheet1",1);

        String empData[][] = new String[rownum][colCount];
        for(int i=0;i<=rownum;i++){
            for(int j=0;j<colCount;j++){
                empData[i-1][j] =XLUtils.getCellData(path, "Sheet1",i,j);
            }
        }
        return (empData);
    }*/

    @Test(dataProvider = "testData")
    public void RegistrationSuccessfullyDone(String name, String job){
        RestAssured.baseURI ="https://reqres.in";
        RequestSpecification requestSpecification = RestAssured.given();

// Directly hard coded.
 /*      JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "morpheus");
        jsonObject.put( "job", "leader");
        requestSpecification.header("Content-Type","application/json");
        requestSpecification.body(jsonObject.toJSONString());*/

// With Data Provider Method
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", name);
        jsonObject1.put("job", job);
        requestSpecification.header("Content-Type","application/json");
        requestSpecification.body(jsonObject1.toJSONString());

        Response response = requestSpecification.request(Method.POST,"/api/users");
        System.out.println(response);
        System.out.println(response.getBody().asString());

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 201);

        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(name),true);
        Assert.assertEquals(responseBody.contains(job),true);

    }



}
