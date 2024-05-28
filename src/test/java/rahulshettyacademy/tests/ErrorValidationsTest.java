package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.checkoutPage;
import rahulshettyacademy.pageobjects.confirmationPage;
import rahulshettyacademy.pageobjects.productCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {
		
	 landingPage.login("sanjud@gmail.com", "Sh@nm@thi8");
         String errorMessage=landingPage.getErrorMessage();
         Assert.assertEquals("Incorrect email or password.", errorMessage);

	}
	
	@Test
	public void productErrorValidation() throws InterruptedException, IOException {
		
		String productName = "ZARA COAT 3";
		String countryName = "ind";
productCatalogue productcatalogue = landingPage.login("sanjud@gmail.com", "Sh@nm@thi8*");

		
		productcatalogue.getProductByName(productName);
		productcatalogue.addProducttoCart(productName);
		cartPage cartpage = productcatalogue.goToCartPage();

		

		Boolean match = cartpage.verifyCartProducts("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
