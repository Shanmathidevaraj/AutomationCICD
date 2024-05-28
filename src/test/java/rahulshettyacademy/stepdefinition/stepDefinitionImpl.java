package rahulshettyacademy.stepdefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.checkoutPage;
import rahulshettyacademy.pageobjects.confirmationPage;
import rahulshettyacademy.pageobjects.productCatalogue;

public class stepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public productCatalogue productcatalogue;
	public confirmationPage confirmationpage;
	public cartPage cartpage;
	public String countryName = "ind";
	@Given ("I landed on Ecommerce page")
	public void i_landed_on_Ecommerce_page() throws IOException
	{
		landingPage=launchApplication();
	}
	
	
	//Logged in with username <name> and password <password>
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username,String password)
	{
		productcatalogue = landingPage.login(username, password);
	}
	
	//I added product <productName> to cart
	@When("^I added product (.+) to cart$")
	public void I_added_product_to_cart(String productName) throws InterruptedException
	{
		productcatalogue.getProductByName(productName);
		productcatalogue.addProducttoCart(productName);
	}
	
	//checkout <productName> and submit the order
	@When("^checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException
	{
		 cartpage = productcatalogue.goToCartPage();

		Boolean match = cartpage.verifyCartProducts(productName);
		Assert.assertTrue(match);
		checkoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry(countryName);
		 confirmationpage = checkoutpage.submitOrder();
	}
	
	//Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page
	@Then("{string} message is displayed on confirmation page")
	public void Message_is_displayed_on_confirmation_page(String string)
	{
		
		String confirmMessgae = confirmationpage.verifyMessage();

		Assert.assertTrue(confirmMessgae.equalsIgnoreCase(string));
		driver.quit();
		
	}
	//"Incorrect email or password." message is displayed.
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) throws InterruptedException
	{
		String errorMessage=landingPage.getErrorMessage();
		Assert.assertEquals(string, errorMessage);
		driver.quit();
	}
	
	
	

}
