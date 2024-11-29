package pawaracademy.Tests;

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

import com.sun.net.httpserver.Authenticator.Retry;

import pawaracademy.AbstractComponents.AbstractComponents;
import pawaracademy.TestComponents.BaseTest;
import pawaracademy.pageobjects.CartPage;
import pawaracademy.pageobjects.CheckoutPage;
import pawaracademy.pageobjects.ConfirmationPage;
import pawaracademy.pageobjects.LandingPage;
import pawaracademy.pageobjects.ProductCatalog;

public class ErrorValidationTest extends BaseTest {
	
	@Test (groups= {"errorHanding"}, retryAnalyzer= pawaracademy.TestComponents.Retry.class)
	public void loginErrorValidation() throws IOException {

		loginPage.loginToApplication("anshika@gmail.com", "Iamkin@000");
		String expectedMsg = loginPage.getLoginErrorMessage();
		Assert.assertEquals("Incorrect email or password", expectedMsg);
	}
	
	@Test
	public void productErrorValidation() {
		String productName = "ZARA COAT 3";

		ProductCatalog proCatalog = loginPage.loginToApplication("nirvi@gmail.com", "Nirvi@123");
		proCatalog.getproductlist();
		proCatalog.getProductByName(productName);
		proCatalog.addToCart(productName);

		CartPage cartPage = proCatalog.goToCartPage();
		Boolean match = cartPage.verifyDisplayProducts("ZARA COAT 33");
		Assert.assertFalse(match);
	}


	

	
}
