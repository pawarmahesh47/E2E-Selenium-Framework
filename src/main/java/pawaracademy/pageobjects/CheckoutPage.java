package pawaracademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pawaracademy.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css = "section[class*='ng-star-inserted'] button")
	List<WebElement> listedCountry; 
	
	@FindBy(css= ".action__submit")
	WebElement placeOrderButton;
	
	By dropDownList = By.cssSelector("section[class*='ng-star-inserted'] button");
	
	public void selectCountry(String ContryName)
	{
		selectCountry.sendKeys(ContryName);
		waitUntilVisibilityOfLocator(dropDownList);
		listedCountry.stream().filter(country -> country.getText().equalsIgnoreCase(ContryName))
		.findFirst().orElse(null).click();
		
	}
	
	public ConfirmationPage placeOrder()
	{
		placeOrderButton.click();
		return new ConfirmationPage(driver);
	}

}
