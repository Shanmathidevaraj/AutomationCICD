package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class checkoutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	
	//driver.findElement(By.xpath("")).sendKeys("ind");
	//wait.until(ExpectedConditions.visibilityOfElementLocated());
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryfield;
	
	@FindBy(xpath="(//button[contains(@class,'list-group-item')])[2]")
	WebElement countryReq;
	
	@FindBy(css=".action__submit")
	WebElement submitbtn;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) throws InterruptedException
	{
		countryfield.sendKeys(countryName);
		waitForElementToAppear(results);
		countryReq.click();
	}
	
	public confirmationPage submitOrder()
	{

		submitbtn.click();
		confirmationPage confirmationpage=new confirmationPage(driver);
		return confirmationpage;
	}
	
	
}
