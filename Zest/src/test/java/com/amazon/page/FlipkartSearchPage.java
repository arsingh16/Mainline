package com.amazon.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlipkartSearchPage {
	
public WebDriver driver = null;
	
	public FlipkartSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}

    @FindBy(xpath="//input[@title='Search for products, brands and more']")
    public WebElement input_search_box;
   
	public String getProductPrice(String product_name) {
		String xpath = "//div[contains(text(),'iPhone XR') and contains(text(),'Yellow') and contains(text(),'64 GB')]/../following-sibling::div//div//div//div";
		WebElement ele = driver.findElement(By.xpath(xpath));
		String price = ele.getText();
		System.out.println(price);
		return price.replaceAll("[^a-zA-Z0-9]", "");
	}
}


