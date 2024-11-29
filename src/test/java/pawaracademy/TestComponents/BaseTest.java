package pawaracademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pawaracademy.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage loginPage;

	public WebDriver initializeDriver() throws IOException {

		Properties pro = new Properties();

		String projectPath = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(
				projectPath + "\\src\\main\\java\\pawaracademy\\Resources\\GlobalData.properties");
		pro.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: pro.getProperty("browser"); // get browser property from mvn command or from globalData.properties

		// pro.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();

			if (browserName.contains("headless")) {
				chromeOptions.addArguments("headless");
			}

			driver = new ChromeDriver(chromeOptions);
		}

		else if (browserName.equalsIgnoreCase("FireFox")) {
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			// edge
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;
	}

	public List<HashMap<String, String>> getDataFromJsonToMap(String filePath) throws IOException {
		// read json to string
		File file = new File(filePath);
		String jsonContenttoString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

		// String to Hashmap Jaskson Databind
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContenttoString,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File screenshotFile = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, screenshotFile);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		loginPage = new LandingPage(driver);
		loginPage.goTo();

		return loginPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
