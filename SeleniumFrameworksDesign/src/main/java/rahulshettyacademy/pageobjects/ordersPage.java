package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ordersPage extends AbstractComponent {
	
	WebDriver driver;
	public ordersPage(WebDriver driver)
	
	{
		super(driver);

this.driver=driver;
PageFactory.initElements(driver, this);
}

	@FindBy(xpath="//tr/td[2]")
	List<WebElement> ordersList;
	
	
	
	public Boolean verifyOrderProducts(String productName)
	{
		Boolean match = ordersList.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
	
	
	
	
	
	
	
	
}
