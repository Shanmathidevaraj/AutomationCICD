package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="#userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submitbtn;
	
	@FindBy(css="[class*='toast-error']")
	WebElement errorMessage;

	
	public productCatalogue login(String email,String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submitbtn.click();
		productCatalogue productcatalogue = new productCatalogue(driver);
		return productcatalogue;
	}
	
	public String getErrorMessage() throws InterruptedException
	{
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	
	
	
}
