package pawaracademy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pawaracademy.AbstractComponents.AbstractComponents;
import pawaracademy.TestComponents.BaseTest;
import pawaracademy.pageobjects.CartPage;
import pawaracademy.pageobjects.CheckoutPage;
import pawaracademy.pageobjects.ConfirmationPage;
import pawaracademy.pageobjects.LandingPage;
import pawaracademy.pageobjects.OrderPage;
import pawaracademy.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void submitOrderTest(HashMap<String, String> input) throws IOException {

		String email = input.get("email");
		String password = input.get("password");
		String productName = input.get("productName");

		ProductCatalog proCatalog = loginPage.loginToApplication(email, password);
		proCatalog.getproductlist();
		proCatalog.getProductByName(productName);
		proCatalog.addToCart(productName);

		CartPage cartPage = proCatalog.goToCartPage();
		Boolean match = cartPage.verifyDisplayProducts(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();

		checkoutPage.selectCountry("india");
		ConfirmationPage cp = checkoutPage.placeOrder();

		String confirmMessage = cp.confirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrderTest" })
	public void verifyOrderProductTest() {
		ProductCatalog proCatalog = loginPage.loginToApplication("nirvi@gmail.com", "Nirvi@123");
		OrderPage orderPage = proCatalog.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderedProducts(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getDataFromJsonToMap(System.getProperty("user.dir") + "\\src\\test\\java\\pawaracademy\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

		// HashMap<Object, Object> hashmap = new HashMap<Object, Object>();
		// hashmap.put("email", "nirvi@gmail.com");
		// hashmap.put("password", "Nirvi@123");
		// hashmap.put("productName", "ZARA COAT 3");

		// HashMap<Object, Object> hashmap1 = new HashMap<Object, Object>();
		// hashmap1.put("email", "mahesh47@gmail.com");
		// hashmap1.put("password", "Mahesh@123");
		// hashmap1.put("productName", "ADIDAS ORIGINAL");

		// return new Object[][] {{hashmap},{hashmap1}};

		// return new Object[][] {{"nirvi@gmail.com", "Nirvi@123", "ZARA COAT
		// 3"},{"mahesh47@gmail.com","Mahesh@123","ADIDAS ORIGINAL"}};
	}
	
	

}
