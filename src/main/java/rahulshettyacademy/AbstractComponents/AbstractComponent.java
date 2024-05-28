package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.ordersPage;

public class AbstractComponent {
	
 WebDriver driver;
 
 
 @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
 WebElement cartbtn;
 
 @FindBy(css="button[routerlink*='myorders']")
 WebElement ordersbtn;
 
	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void waitForElementToAppear(By findBy) throws InterruptedException
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement ele) throws InterruptedException
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	
	public cartPage goToCartPage() throws InterruptedException
	{
		Thread.sleep(3000);
		cartbtn.click();
		cartPage cartpage = new cartPage(driver);
		return cartpage;
	}
	
	public ordersPage goToOrdersPage() throws InterruptedException
	{
		Thread.sleep(3000);
		ordersbtn.click();
		ordersPage orderspage = new ordersPage(driver);
		return orderspage;
	}

}
