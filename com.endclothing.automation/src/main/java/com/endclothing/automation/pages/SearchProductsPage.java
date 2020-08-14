package com.endclothing.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.endclothing.automation.base.TestBase;

public class SearchProductsPage extends TestBase{
	private static final Logger LOG = LogManager.getLogger(SearchProductsPage.class); 
	
	
	WebDriverWait wait=new WebDriverWait(driver,40);
	@FindBy(xpath="//button[@data-testid='Autosuggest__Button']")
	WebElement searchButton;
	
	//Search Input field 
	@FindBy(xpath="//input[@placeholder=' Search']")
	WebElement searchInput;
	
	//First Product in the Product Results
	@FindBy(xpath="//div[@id='app-container']//child::a[@data-test='ProductCard__ProductCardSC'][1]")
	WebElement firstProductResult;
	
	//First Size Button in the size guide
	@FindBy(xpath="//*[@id='__page_wrapper']//child::div[@data-testid='Size__List']/div[1]")
	WebElement productSizeButton1;
	
	//Second Size Button in the size guide
	@FindBy(xpath="//*[@id='__page_wrapper']//child::div[@data-testid='Size__List']/div[2]")
	WebElement productSizeButton2;
	
	//Add To Cart Button
	@FindBy(xpath="//button[@data-test='AddToCart__Button']/div")
	WebElement addToCardButton;
	
	//Screen Overlay to click after adding a product to come back to Product Detail Page 
	@FindBy(xpath="//div[@data-test='Overlay']")
	WebElement overLayScreen;
	
	
	//@FindBy(xpath="//div[@id='__page_wrapper']")
	//WebElement pageWrapperOverCart;
	
	//Shopping Cart Button
	@FindBy(xpath="//button[@data-testid='Open_Cart_Button']//div")
	WebElement addToCartButton;
	
	//Product counter button (displays product count on the basket at the top right corner
	@FindBy(xpath="//button[@data-testid='Open_Cart_Button']//span[@data-test='CartQuantityCounty__CartCount']")
	WebElement cartProductCounter;
	
	// Single Product Price in the Product details section with Dollor sign
	@FindBy(xpath="//*[@id=\"710736557013\"]/div/div[2]/span")
	WebElement productPriceText;
	
	//Shopping Cart Sub Total price (Excluding Vat)
	@FindBy(xpath="//span[@data-testid='CartQuantityCounty__CartCount__Subtotal']")
	WebElement cartSubTotalPrice;
	
	//Shipping Charges. Captures along with Dollor sign
	@FindBy(xpath="//span[@data-testid='CartQuantityCounty__CartCount__Shipping']")
	WebElement shippingCharge;
	
	//Cart Total Price label after adding two products with dollar sign EG: $249.95
	@FindBy(xpath="//span[@data-testid='CartQuantityCounty__CartCount__Total']")
	WebElement cartTotalPrice;
	
	//Constructor which initializes the PageFactory and the web elements
	public SearchProductsPage() {
		PageFactory.initElements(driver, this);
		LOG.info("SearchProductsPage PageFactory initialized");
	}
	
	//Clicks on Search button on the right hand side corner
	public void clickOnSearchButton() {
		click(searchButton);
	}
	//Sends 'Shirts' in the search input and presses ENTER button 
	public void searchForShirts(String text) {
		searchInput.sendKeys(text+Keys.ENTER);
		
	}

	
	public String getProductPrice() {
		
		return new WebDriverWait(driver,40).until(
				ExpectedConditions.visibilityOf(productPriceText)).getText();
	}
	public void clickOnFirstProductResult() {
		click(firstProductResult);
	}
	public void clickOnproductSizeButton1() {
		click(productSizeButton1);
	}
	
	public void clickOnproductSizeButton2() {
		click(productSizeButton2);
	}
	public void clickOnAddToCartButton()
	{
		click(addToCardButton);
	}
	
	
	public void clickOnOverLayScreen()  {
		click(overLayScreen);
	}
	public int getCartProductCounter() throws InterruptedException {
	
		return Integer.parseInt(
				new WebDriverWait(driver,30).until(
						ExpectedConditions.visibilityOf(cartProductCounter)).getText());
	}
	
	public String getCartSubTotalPrice() {
		return new WebDriverWait(driver,30).until(
				ExpectedConditions.visibilityOf(cartSubTotalPrice)).getText();
	}
	
	public String getCartTotalPrice() {
		return new WebDriverWait(driver,30).until(
				ExpectedConditions.visibilityOf(cartTotalPrice)).getText();		
	}
	
	public String getShippingCharge() {
		return new WebDriverWait(driver,30).until(
				ExpectedConditions.visibilityOf(shippingCharge)).getText();
	}
}

