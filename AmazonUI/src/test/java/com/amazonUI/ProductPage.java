package com.amazonUI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	WebDriver driver;
	@FindBy(id = "productTitle") //        Apple iPhone 13, 128GB, (PRODUCT)RED - Unlocked (Renewed)       
	WebElement prodName;
	
	@FindBy(xpath = "(//div[@id = 'averageCustomerReviews']//span[@aria-hidden])[1]")
	WebElement rating ;
	
	
	
	@FindBy(xpath = "//a[text() = 'Write a customer review']")
	WebElement reviewButton ;
	
	
	ProductPage(WebDriver driver)
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
		
	}
	
	public String getProdName()
	{
		return Helper.getText(prodName);
		
		
	}

	public String getRating() {
		return Helper.getText(rating);
	}

	


	
	public void clickReviewButton() {
		Helper.click(reviewButton);
	}

	

	


	

	
	
	
	
	
	
	
}
