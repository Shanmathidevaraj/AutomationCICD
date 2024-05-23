package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.checkoutPage;
import rahulshettyacademy.pageobjects.confirmationPage;
import rahulshettyacademy.pageobjects.ordersPage;
import rahulshettyacademy.pageobjects.productCatalogue;

public class submitOrderTest extends BaseTest {

	public String productName = "ZARA COAT 3";
	public String countryName = "ind";
	@Test(dataProvider="getData",groups={"purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		

		productCatalogue productcatalogue = landingPage.login(input.get("email"), input.get("password"));

		productcatalogue.getProductByName(input.get("product"));
		productcatalogue.addProducttoCart(input.get("product"));
		cartPage cartpage = productcatalogue.goToCartPage();

		Boolean match = cartpage.verifyCartProducts(input.get("product"));
		Assert.assertTrue(match);
		checkoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry(countryName);
		confirmationPage confirmationpage = checkoutpage.submitOrder();

		String confirmMessgae = confirmationpage.verifyMessage();

		Assert.assertTrue(confirmMessgae.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void validateOrder() throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		
	

		productCatalogue productcatalogue = landingPage.login("sanjud@gmail.com", "Sh@nm@thi8*");
		ordersPage orderspage=productcatalogue.goToOrdersPage();
		
		Assert.assertTrue(orderspage.verifyOrderProducts(productName));
	}
	
		
	
	@DataProvider
	public Object[][] getData() throws IOException
	
	{
		
		
		/*HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "sanjud@gmail.com");
		map.put("password", "Sh@nm@thi8*");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "arung@gmail.com");
		map1.put("password", "Sh@nm@thi8*");
		map1.put("product", "ADIDAS ORIGINAL");*/
		
		List<HashMap<String, String>>  data=getJsonDatatoMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\Purchaseorder.json");
		
		return new Object[] [] {{data.get(0)},{data.get(1)}};	
}
}