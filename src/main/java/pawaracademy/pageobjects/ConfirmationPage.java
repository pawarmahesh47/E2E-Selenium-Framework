package pawaracademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pawaracademy.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".hero-primary")
	WebElement confimationMessage;
	
	public String confirmationMessage()
	{
		return confimationMessage.getText();
	}
}
