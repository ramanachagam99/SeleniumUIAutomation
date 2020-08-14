package com.endclothing.automation.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class reads the properties from environment.properties
 * and creates the browser instance and navigates to baseURL
 * **/
public class BrowserBase extends TestBase {
	
	public static Properties prop;
	private static final Logger LOG = LogManager.getLogger(BrowserBase.class);
	
	/** Starts Chrome or Firefox browser by reading environment.properties file 
	 **/
	
	public static WebDriver startBrowser() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/"
					+ "endclothing/automation/config/environment.properties");
			prop.load(fis);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String browserName = prop.getProperty("dev.browser");
		String baseURL = prop.getProperty("dev.baseURL");

		/** Launches Chrome Browser **/
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			driver = new ChromeDriver(options);
		}
		/** Launches Firefox Browser **/
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			driver = new FirefoxDriver(options);
		}
		LOG.info("\n");
		LOG.info("Browser Launched: " + browserName);
		
		//Navigates to base URL
		visit(baseURL);
		//driver.manage().window().maximize();
		LOG.info("Navigated to base URL:  " + baseURL);
		return driver;
	}
	
}
