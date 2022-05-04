package com.SDET.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter {

    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext testContext){
        extentSparkReporter= new ExtentSparkReporter( "./Reports/myReport.html");
        extentSparkReporter.config().setDocumentTitle("RestAssuredAutomationReport");
        extentSparkReporter.config().setReportName("RestAssuredTest");
        extentSparkReporter.config().setTheme(Theme.DARK);

        extent= new ExtentReports();
        extent.attachReporter(extentSparkReporter);
        extent.setSystemInfo("Host name ", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user","Vishu");
    }

    public void onTestSuccess(ITestResult result){
        test= extent.createTest(result.getName());
        test.log(Status.PASS, "Test case Passes is :"+ result.getName());
    }

    public void onTestFailure(ITestResult result){
        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test case failed is :"+result.getName());
        test.log(Status.FAIL, "Test case failed is :" +result.getThrowable());
    }

    public void onTestSkipped(ITestResult result){
        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test case skipped is :" +result.getName());
    }

    public void onFinish(ITestListener testContext){
        extent.flush();
    }
}
