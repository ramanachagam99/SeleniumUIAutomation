package com.endclothing.automation.reports;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.endclothing.automation.base.TestBase;

/**
 * This class configures html report properties and returns ExtentReports instance
 * **/
public class ExtentManager extends TestBase{

	private static ExtentReports extent;

	/**
	 * This class configures html report properties and returns ExtentReports instance
	 * @filename - Location of the Reports to store extent report
	 * **/
	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Ramana");
		extent.setSystemInfo("Organization", "EndClothing");
		
		return extent;
	}

	  public static String screenshotPath; 
	  public static String screenshotName;
	  
	  /**
	   * Utility function to capture screenshot on Test Failure
	   * The Screenshot is stored in //screenshots folder 
	   */
	  public static void captureScreenshot() {
	  
		  File scrFile = ((TakesScreenshot)
				  driver).getScreenshotAs(OutputType.FILE);
	  
		  Date d = new Date(); screenshotName = d.toString().replace(":",
				  "_").replace(" ", "_") + ".jpg";
	  
		  try { FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +
				  "//screenshots//" + screenshotName)); 
		  } catch (IOException e)
		  {	
		  		e.printStackTrace(); 
		  }
	  	}
}
