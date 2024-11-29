package pawaracademy.StepDefination;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pawaracademy.TestComponents.BaseTest;
import pawaracademy.pageobjects.CartPage;
import pawaracademy.pageobjects.CheckoutPage;
import pawaracademy.pageobjects.ConfirmationPage;
import pawaracademy.pageobjects.LandingPage;
import pawaracademy.pageobjects.ProductCatalog;

public class StepDefinationImp extends BaseTest {

	LandingPage loginPage;
	ProductCatalog proCatalog;
	ConfirmationPage cp;

	@Given("I landed on E-commerence page")
	public void i_landed_on_ecommerence_page() throws IOException {
		loginPage = launchApplication();

	}
	
	@Given("^I login to e-commerence website with username (.+) and password (.+)$")
	public void i_login_ecommerce_website_with_username_password(String email, String password) {
		proCatalog = loginPage.loginToApplication(email, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_prod_to_cart(String productName)
	{
		proCatalog.getproductlist();
		proCatalog.getProductByName(productName);
		proCatalog.addToCart(productName);
	}

	@And("^checkout (.+) & submit the order$")
	public void i_checkout_prod_submit_order(String productName)
	{
		CartPage cartPage = proCatalog.goToCartPage();
		Boolean match = cartPage.verifyDisplayProducts(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();

		checkoutPage.selectCountry("india");
		cp = checkoutPage.placeOrder();
	}
	
	@Then("{string} messsage displayed on confirmation page")
	public void confirm_msg_display_on_page(String string)
	{
		String confirmMessage = cp.confirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} error msg display on page")
	public void error_msg_diaplay_on_page(String string)
	{
		String expectedMsg = loginPage.getLoginErrorMessage();
		Assert.assertEquals(string, expectedMsg);
		driver.close();
	}
}
