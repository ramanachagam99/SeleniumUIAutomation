package com.endclothing.automation.tests;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.endclothing.automation.base.BrowserBase;
import com.endclothing.automation.base.TestBase;
import com.endclothing.automation.pages.ClothingPage;


public class ClothingPageTest extends TestBase{
	private static final Logger LOG = LogManager.getLogger(ClothingPageTest.class);
	ClothingPage clothingPage;
	SoftAssert sa=new SoftAssert();
	@BeforeMethod
	public void setup() {
		BrowserBase.startBrowser();
		clothingPage=new ClothingPage();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	/** This test navigates to Shirts sub category in Clothing category in the US Store
	 *  Filters the product list based on the Black Colour Checkbox
	 **/
	
	@Test(description="Verifies Black colour filter and Products count",priority=0)
	public void verifyShirtsFilterAndProductCountTest() throws Throwable {
		
		clothingPage.moveMouseToClothing();
		LOG.info("Mouse Moved to Clothing");
		
		clothingPage.clickOnShirtsLink();
		LOG.info("Clicked on Shirts sub category");
		
		clothingPage.clickOnBlackColourCheckbox();
		LOG.info("Clicked on Black Colour Checkbox");
		Thread.sleep(2000);
		clothingPage.getProductsCount();
		
		//Assert.assertEquals(clothingPage.getClothingText(),"CLOTHING",  "Clothing Link not found");
		sa.assertEquals(clothingPage.getClothingText(),"CLOTHING",  "Clothing Link not found");
		
		sa.assertEquals(clothingPage.getShirtsText(),"Shirts","Shirts Link not found");
		//Verifies Black colour filter is present
		
		sa.assertTrue(clothingPage.isBlackFilterPresent(), "Black filter not Present");
		LOG.info("Total Black  Products Count is :"+clothingPage.getProductsCount());
		
		sa.assertTrue(clothingPage.isShirtsLabelPresent(), "Black Shirts Label not present");
		sa.assertAll();
	}
	
	@AfterMethod
	public void teardown(){
		driver.close();
		driver.quit();
		
	}
	
	
}
