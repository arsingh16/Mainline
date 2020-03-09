package com.amazon.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.amazon.page.AmazonSearchPage;
import com.amazon.page.FlipkartSearchPage;

public class ZestTest extends CommonLibrary {
	public String amazon_url = "https://www.amazon.in/";
	public String flipkart_url = "https://www.flipkart.com/";
	public static WebDriver driver;
	
	@BeforeTest
	public void setBaseURL() {
		
		System.out.println("!!!!!!!! Executing Amazon Test Suite !!!!!!!!!!");
		System.setProperty("webdriver.chrome.driver","C:/Users/Ashwin/eclipse-workspace/Zest/src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(amazon_url);
	}
	
	@Test
	public void compare_product_price() {
		
		String product_name = "iPhone XR (64GB) - Yellow";

		AmazonSearchPage amz_search_page = PageFactory.initElements(driver, AmazonSearchPage.class);
		amz_search_page.input_search_box.sendKeys(product_name);
		amz_search_page.input_search_box.sendKeys(Keys.ENTER);
		int amz_price = Integer.parseInt(amz_search_page.getProductPrice(product_name));
		
		driver.get(flipkart_url);
		FlipkartSearchPage flpkt_search_page = PageFactory.initElements(driver, FlipkartSearchPage.class);
		flpkt_search_page.input_search_box.sendKeys("iPhone XR (64GB) - Yellow");
		flpkt_search_page.input_search_box.sendKeys(Keys.ENTER);
		int flpkt_price = Integer.parseInt(flpkt_search_page.getProductPrice(product_name));
		if (compareValues(amz_price, flpkt_price)==amz_price) {
			System.out.println("After comparing Flipkart and Amazon , we found that Amazon sells"+product_name+" at Lowest price: "+ amz_price );
		}else {
		System.out.println("After comparing Flipkart and Amazon , we found that Flipkart sells"+product_name+" at Lowest price: "+ flpkt_price );
	}
	}
	
	@AfterTest
	public void endSession() {
		driver.quit();
	}
	
}
