package com.amazonUI;



import org.openqa.selenium.WebElement;

public class Helper {


	public static String getText(WebElement element)
	{
		
		String text = element.getText();
	 
		System.out.println("Value present" + "  "  + text);
		return text;
		
	}
	
	
	public static void click(WebElement element)
	{
		
		element.click();
		
		
	}
	
	
	
}
