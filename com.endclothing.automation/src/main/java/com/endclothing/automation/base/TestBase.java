package com.endclothing.automation.base;


import java.util.Properties;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** TestBase Class - contains re-usable methods for actions
 * **/

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;

	
	public static void visit(String url) {
		driver.get(url);
	}

	public static void moveToElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	public static void click(WebElement element) {
		try {
			new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static WebElement findString(By xpath) {
			return driver.findElement(xpath);
		
	}
	
	public static void waitAndClick(WebElement element) throws InterruptedException {
		
		Thread.sleep(2000);
		new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(element)).click();

	}

	public static void type(WebElement element, String text) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(text);
	}
	public static void waitForThreeSeconds() throws InterruptedException {
		Thread.sleep(3000);
	}
	public static void waitForFiveSeconds() throws InterruptedException {
		Thread.sleep(5000);
	}

}
