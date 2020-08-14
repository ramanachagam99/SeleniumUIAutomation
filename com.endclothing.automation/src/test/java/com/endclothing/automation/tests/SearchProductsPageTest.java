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
import com.endclothing.automation.pages.SearchProductsPage;

//Search Products Test Class
public class SearchProductsPageTest extends TestBase{
	
	private static final Logger LOG = LogManager.getLogger(SearchProductsPageTest.class);
	SearchProductsPage searchPage;
	SoftAssert sa=new SoftAssert();

	/**
	 * This method runs before each test and starts the browser
	 */
	@BeforeMethod
	public void setup() {
		//Starts browser
		BrowserBase.startBrowser();
		searchPage=new SearchProductsPage();
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
	}
	
	/**Scenario: Search for shirts and add a product and with two different sizes 
	 * to the cart and ensure cart total matches
	 **/
	@Test(description="Verifies Products Count and Cart Total",priority=0)
	public void verifyTwoShirtsAddedToCartAndCartTotal() throws Throwable {
		
		searchPage.clickOnSearchButton();
		LOG.info("Clicked on Search Button Input");
		
		searchPage.searchForShirts("Shirts");
		LOG.info("Typed 'Shirts' in the Search Input");
		
		String productPrice=searchPage.getProductPrice();
		LOG.info("Product Price is : "+productPrice);
		
		searchPage.clickOnFirstProductResult();
		LOG.info("Clicked on First Product in the Product results");
		
		searchPage.clickOnproductSizeButton1();
		LOG.info("Clicked on First Product Size Button1");
		
		//Thread.sleep(7000);
		waitForFiveSeconds();
		searchPage.clickOnAddToCartButton();
		LOG.info("Clicked on AddToCartButton First time");
		
		searchPage.clickOnOverLayScreen();
		LOG.info("Clicked on OverLayScreen");
		
		//Thread.sleep(5000);
		waitForFiveSeconds();
		searchPage.clickOnproductSizeButton2();
		LOG.info("Clicked on First Product Size Button2");
		
		searchPage.clickOnAddToCartButton();
		LOG.info("Clicked on AddToCartButton Second time");
		
		//Thread.sleep(5000);
		waitForFiveSeconds();
		String strCartSubTotal=searchPage.getCartSubTotalPrice();
		LOG.info("Shopping Cart Sub Total is $: " + strCartSubTotal);
		
		
		//Thread.sleep(5000);
		//Fetches shopping cart total price
		float cartTotal=Float.parseFloat(searchPage.getCartTotalPrice().substring(1));
		
		float shippingCharge=Float.parseFloat(searchPage.getShippingCharge().substring(1));
		
		searchPage.clickOnOverLayScreen();
		LOG.info("Clicked on OvarLayScreen");
		
		int productCounter=searchPage.getCartProductCounter();
		LOG.info("Number of products in the Cart  :  "+productCounter);
		
		/**
		 * Verifies if two products added to the cart**/ 
		
		sa.assertEquals(productCounter, 2,"Number of Products in the Cart doesn't match");
		float price = Float.parseFloat(productPrice.substring(1));
		float actualSubTotal=Float.parseFloat(strCartSubTotal.substring(1));
		float expectedCartSubTotal=price*2;
		
		/**
		 * Verifies Sub totals excluding Vat in the Cart**/
		
		sa.assertEquals(actualSubTotal, expectedCartSubTotal,"Sub Totals doesn't match");
		LOG.info("Expected Sub total : $"+expectedCartSubTotal);
		LOG.info("Actual Sub total : $"+actualSubTotal);
		
		/**
		 * Verifies Cart total in the Shopping Cart
		 **/
		
		sa.assertEquals(cartTotal,actualSubTotal+shippingCharge,"Cart Total doesn't match" );
		LOG.info("Expected Cart total : $"+(actualSubTotal+shippingCharge));
		LOG.info("Actual Cart total : $"+cartTotal);
		sa.assertAll();
	}

	@AfterMethod
	public void teardown(){
		driver.close();
		driver.quit();	
		
	}
	
}
