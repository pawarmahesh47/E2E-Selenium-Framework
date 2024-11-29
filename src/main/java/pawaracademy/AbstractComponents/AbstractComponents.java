package pawaracademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pawaracademy.pageobjects.CartPage;
import pawaracademy.pageobjects.OrderPage;

public class AbstractComponents   {	
	
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	WebDriver driver;
	
	
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "button[routerlink='/dashboard/cart']")
	WebElement cartButton;
	
	@FindBy(css = "button[routerlink='/dashboard/myorders']")
	WebElement orderButton;

	public void waitUntilVisibilityOfLocator(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitUntilInvisibilityOfWebelemt(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitUntilInvisibilityOfLocator(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void waitUntilVisibilityOfWebelemt(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public CartPage goToCartPage()
	{
		cartButton.click();
		return new CartPage(driver);
	}
	
	public OrderPage goToOrderPage()
	{
		orderButton.click();
		return new OrderPage(driver);
	}


}
