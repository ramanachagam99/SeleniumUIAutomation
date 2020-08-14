package com.endclothing.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.endclothing.automation.base.TestBase;

public class ClothingPage extends TestBase{
	private static final Logger LOG = LogManager.getLogger(ClothingPage.class);
	
	WebDriverWait wait=new WebDriverWait(driver,40);
	@FindBy(css="#navigation > span:nth-child(3) > a")
	WebElement clothingLink;
	
	@FindBy(linkText="Shirts")
	WebElement shirtsLink;
	
	
	@FindBy(xpath="//div[@data-testid='colour_FilterItem']//child::span[contains(text(),'Black')]")
	WebElement blackColourCheckbox;
	
	//Eg: black filter for verification (In the SELECTED section)
	@FindBy(xpath="//div[@class='filtered-by']//child::button[1]")
	WebElement blackFilterButton;
	
	//Total number of products displayed for black shirts
	@FindBy(xpath="//div[@data-testid='Product__Counter']//child::span")
	WebElement productsCounter;
	
	@FindBy(xpath="//h2[contains(text(),'Shirts')]")
	WebElement shirtsLabel;
	
	
	public ClothingPage() {
		PageFactory.initElements(driver, this);
		LOG.info("Clothing PageFactory initialized");
	}
	
	public void moveMouseToClothing() {
		if(clothingLink.isDisplayed())
		moveToElement(clothingLink);
		
	}
	//Clicks on the Shirts sub category of Clothing
	public void clickOnShirtsLink() throws Throwable {
		//By shirtsLink=By.linkText("Shirts");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(shirtsLink)).click();
		try {
			waitAndClick(shirtsLink);
		}catch (Exception e){
			
		}	
		
	}
	
	//Clicks on the black Filter checkbox
	public void clickOnBlackColourCheckbox() throws Throwable {
		try {
				waitAndClick(blackColourCheckbox);
				
		}catch (Exception e){
			LOG.error(e.getStackTrace());
		}
		
	}
	public String getClothingText() {
		return clothingLink.getText();
	}
	
	public String getShirtsText() throws InterruptedException {
		
		
		if(!shirtsLink.isDisplayed()) {
			waitForThreeSeconds();
		}
		return shirtsLink.getText();
		
	}
	
	public Boolean isBlackFilterPresent() {
		if(blackFilterButton.getText().equalsIgnoreCase("Black")) 
			return true;
		return false;
		
	}
	//Returns Products Count
	public int getProductsCount() {
		//return Integer.parseInt(productsCounter.getText());
		return Integer.parseInt(new WebDriverWait(driver,40).until(
				ExpectedConditions.visibilityOf(productsCounter)).getText());
		  
	}
	public boolean isShirtsLabelPresent(){
		return shirtsLabel.isDisplayed();
		
	}
	
}
