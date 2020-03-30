package com.amazon.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonSearchPage {
	

	public WebDriver driver = null;
	
	public AmazonSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}

    @FindBy(xpath="//input[@id='twotabsearchtextbox']")
    public WebElement input_search_box;
   
	public String getProductPrice(String product_name) {
		String xpath = String.format("//span[contains(text(),'%s')]/../../../../../../following-sibling::div//span[@class='a-price']//span[@class='a-price-whole']", product_name);
		WebElement ele = driver.findElement(By.xpath(xpath));
		String price = ele.getText();
		return price.replaceAll("[^a-zA-Z0-9]", "");
	}
}
