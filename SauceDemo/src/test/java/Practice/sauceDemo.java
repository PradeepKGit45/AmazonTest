package Practice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class sauceDemo {

	WebDriver driver;
	int noOfItems;
	static List<WebElement> ProductList;
	@BeforeClass
	
	void setup()
	{
		WebDriverManager.edgedriver().setup();
		driver= new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
	public boolean isAlertPresent(WebDriver driver) {
	    try {
	        driver.switchTo().alert();
	        return true;
	    } catch (NoAlertPresentException ex) {
	        return false;
	    }
	}
	@Test(priority=1)
	void login() throws InterruptedException
	{
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(2000);
		
		
		if (isAlertPresent(driver)) {
		    Alert alert = driver.switchTo().alert();
		    System.out.println("Alert Text: " + alert.getText());
		    alert.accept(); // or alert.dismiss()
		} else {
		    System.out.println("No alert present.");
		}

	
	
		
	}
	
	public static boolean findProduct(String product)
	{
		
		for (WebElement webElement : ProductList) {
			String productName = webElement.getText();
			if(productName.contains(product))
			{
				
				return true;
				 
			}
			
				
			
		}
		return false;
	}
	
	@Parameters({"product", "items"})
	@Test(priority=2)
	
	void addToCart(@Optional("Backpack") String product, @Optional ("1") String items)
	{
		noOfItems = Integer.parseInt(items);
		ProductList = driver.findElements(By.xpath("//div[normalize-space(@class)='inventory_item_name']"));
		if (findProduct(product)) {
			driver.findElement(By.xpath("//div[normalize-space(@class)='inventory_item_name' and contains(text(), '" + product + "') ]//ancestor::div[@class='inventory_item_label']/following-sibling::div//button")).click();
			
		}
		
		
		for (WebElement webElement : ProductList) {
			webElement.getText();
		}
		
		
	}
	
	@Test(priority=3)
	void pay() throws InterruptedException
	{
		
		
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		driver.findElement(By.id("first-name")).sendKeys("TestFirst");
		driver.findElement(By.id("last-name")).sendKeys("TestLast");
		driver.findElement(By.id("postal-code")).sendKeys("600200");
		Thread.sleep(2000);
		driver.findElement(By.id("continue")).click();
		
		int noOfItemsAtCheckOut = driver.findElements(By.xpath("//div[@class='cart_item']")).size();
		Assert.assertEquals(noOfItemsAtCheckOut, noOfItems, "More Items added than expected!!");
		Thread.sleep(2000);
		driver.findElement(By.id("finish")).click();
		
		String text = driver.findElement(By.tagName("h2")).getText();
		if (text.contains("Thank you for your order!"))
		{
			System.out.println("Order successful!");
		}
		Thread.sleep(2000);
		
	}
	
	@AfterClass
	
	void teardown()
	{
		
		driver.quit();
		
		
		
	}
}