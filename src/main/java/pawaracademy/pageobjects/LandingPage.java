package pawaracademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pawaracademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.id("userEmail")).sendKeys("nirvi@gmail.com");
	//driver.findElement(By.id("userPassword")).sendKeys("Nirvi@123");
	//driver.findElement(By.id("login")).click();
	
	@FindBy(id ="userEmail")
	WebElement username;
	
	@FindBy(id ="userPassword")
	WebElement passwordEle;
	
	@FindBy(id ="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement webElementErrormsg;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalog loginToApplication(String email, String password)
	{
		username.sendKeys(email);
		passwordEle.sendKeys(password);
		login.click();
		return new ProductCatalog(driver);
	}
	
	public String getLoginErrorMessage()
	{
		waitUntilVisibilityOfWebelemt(webElementErrormsg);
		return webElementErrormsg.getText();
		
	}
}
