package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//checking CICD pipeline 
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();

		LandingPage landingPage=new LandingPage(driver);
		
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("sanjud@gmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("Sh@nm@thi8*");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);

		driver.findElement(By.xpath("//button[text()='Checkout']")).click();

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("(//button[contains(@class,'list-group-item')])[2]")).click();

		driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmMessgae = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();

		Assert.assertTrue(confirmMessgae.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.quit();

	}

}
