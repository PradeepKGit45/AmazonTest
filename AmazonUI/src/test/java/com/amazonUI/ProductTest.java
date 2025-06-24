package com.amazonUI;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductTest {
	
	WebDriver driver;
	
	@BeforeClass
	
	void setup()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.amazon.com/Apple-iPhone-13-128GB-Red/dp/B09LNT1F3H/ref=sr_1_4?crid=1MF915T6JIM12&dib=eyJ2IjoiMSJ9.QdNn2w6Yx33_bVIA9bSNLmXNFE1jNJMuMVHNKHek76lGUKLv3rkTv2h8ocg68kOwnncrc3FBd4NwZ5Ce_TaDi2Eh_bwPAQY58hj_yaI6Ur06XcVrO66Nn-AX4RANCviDWPoVqqbF2VpFAFaQ0aTo1Z2Bhp4Yb1aUVMt8_sR9HKJVocAsZl7Oq8z0vZQEE2K7wJHk-8FX5cwFkqC3RM7DbuUsXUbiI7zn-kC7ND8ZLWo.nui1lMxG2b-IJeMclJOZtz-9XyBP0NClBlEqrSW1pSk&dib_tag=se&keywords=iphone%2B16&qid=1747895125&sprefix=iphone%2B16%2Caps%2C736&sr=8-4&th=1");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@Test
	
	void test()
	{
		ProductPage page = new ProductPage(driver);
		String productName = page.getProdName();
		String rating = page.getRating();
		System.out.println("css " + driver.findElement(By.cssSelector(".a-size-large.product-title-word-break")).getText() );
		String h2 = driver.findElement(By.xpath("//h2[normalize-space()= 'Product information']")).getText();
		System.out.println(h2);
		
		System.out.println("content " + driver.findElement(By.xpath("(//h2[normalize-space()= 'Product information']//parent::div//child::div[@class='a-row'])[1]")).getText());
		try
		{
			page.clickReviewButton();
			System.out.println("Review Button successfully clicked");
		}
		
		catch (Exception e)
		{
			System.out.println("Review button error");
		}
		
		Assert.assertEquals(!productName.isEmpty(), true, "Product is different");
		Assert.assertEquals(!rating.isEmpty(), true, "rating absent");
		
		Actions act = new Actions(driver);
		
		
		
	}
	
	
	
	
	
	
}
