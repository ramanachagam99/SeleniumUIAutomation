package com.endclothing.automation.listeners;

import com.endclothing.automation.reports.ExtentManager;


import java.util.Arrays;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

/**
 * Listener class to handle ExtentReports 
 * and implements TestNG interface ITestListener
 * **/
public class ExtentListener implements ITestListener {
	
	private static final Logger LOG = LogManager.getLogger(ExtentListener.class);
	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"//reports//"+fileName);
	
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	
	//Invoked on Test Start
	public void onTestStart(ITestResult result) {
		
		ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
        testReport.set(test);
	}
	
	// Invoked when a Test Passed
	public void onTestSuccess(ITestResult result) {
		
		String methodName=result.getMethod().getMethodName();
		LOG.info("TEST CASE "+methodName.toUpperCase()+ " PASSED");
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " PASSED"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
	
	}
	
	//Invoked On Test Failure. Takes Screenshot on failure
	public void onTestFailure(ITestResult result) {
		
		String excepionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		LOG.info("TEST CASE FAILED "+result.getName() +"  "+excepionMessage);
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
		String failureLog="TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
		
		//Takes Screenshot and stores it in screenshots folder
		ExtentManager.captureScreenshot();
		
		testReport.get().log(Status.FAIL, m);
		
	}
	//Invoked on Test Skip
	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		LOG.info("Test Skipped : "+methodName);
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {
		
	}
	//Invoked on completing the and flushes report content 
	public void onFinish(ITestContext context) {
		
		if (extent != null) {

			extent.flush();
		}

	}

}

