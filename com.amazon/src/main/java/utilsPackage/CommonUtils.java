package utilsPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.EmailException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("deprecation")
public class CommonUtils {

	public static WebDriver driver = null;
	@SuppressWarnings("deprecation")
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();
	public static Properties properties;
	public static Properties environmentProperties;
	public static String propertiesFilePath = "/src/test/resources/configuration.properties";
	public static String environementPropertiesFilePath = String.format("/src/test/resources/properties/%s",
			System.getProperty("configuration"));

	public static String productName;
	public static String productPrice;
	public static int productQty;
	public static String productSize;
	public static String reportFolderPath = System.getProperty("user.dir") + "/Reports";
	public static String extentReportPath = System.getProperty("user.dir") + "/Reports/ExtentReport.html";
	public static String suiteName;

	public void setUpDriverPage() throws FileNotFoundException {
		stepInfo("------- STARTING NEW WEB INSTANCE ------------");
		readPropertiesFile(System.getProperty("user.dir") + propertiesFilePath);
		readEnvironmentPropertiesFile(System.getProperty("user.dir") + environementPropertiesFilePath);
		if (environmentProperties.getProperty("Environment").equalsIgnoreCase("Desktop")) {
			setUpDesktopDriver();
		} else {
			throw new FileNotFoundException("Not implemented for mobile");
		}
		driver.manage().window().maximize();
	}

	public void setUpDesktopDriver() {
		String browserName = System.getProperty(CommonConstants.BROWSER_PARAMETER) == null
				? environmentProperties.getProperty("Browser")
				: System.getProperty(CommonConstants.BROWSER_PARAMETER);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

	}

	@SuppressWarnings("deprecation")
	public void extentReportSetup() {
		// location of the extent report
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/ExtentReport.html");
		extent = new ExtentReports(); // create object of ExtentReports
		extent.attachReporter(htmlReporter);

		htmlReporter.config().setDocumentTitle("Project Report : Amazon.com"); // Tittle of Report
		htmlReporter.config().setReportName("Extent Report"); // Name of the report
		htmlReporter.config().setTheme(Theme.DARK);// Default Theme of Report

		// General information releated to application
		extent.setSystemInfo("Application Name", "Amazon.com");
		extent.setSystemInfo("User Name", "Ashutosh Mittal");
		extent.setSystemInfo("Envirnoment", "Test Env");
	}

	public static void stepInfo(String message) {
		logger.info(message);
		test.info(message);
	}

	public void selectByVisibleText(WebElement element, String keyToSelect) throws InterruptedException {
		Select selectOption = new Select(element);
		stepInfo("Select key from Dropdown : " + keyToSelect);
		selectOption.selectByVisibleText(keyToSelect);
		Thread.sleep(2000);
	}

	public String getSelectedValue(WebElement element) {
		Select selectOption = new Select(element);
		stepInfo("Get Selected Option");
		return selectOption.getFirstSelectedOption().getText();
	}

	public void clearCookies() {
		stepInfo("Clear all cookies");
		driver.manage().deleteAllCookies();
	}

	public void readPropertiesFile(String filePath) throws FileNotFoundException {
		FileReader reader = new FileReader(filePath);
		properties = new Properties();
		try {
			properties.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readEnvironmentPropertiesFile(String filePath) throws FileNotFoundException {
		FileReader reader = new FileReader(filePath);
		environmentProperties = new Properties();
		try {
			environmentProperties.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sendEmail(String folderPath, String reportPath, String emailSubject, String senderEmail,
			String senderPassword, String[] recipient, String minimumPasspercentage) throws EmailException {
		EmailCode.sendEmailReport(folderPath, reportPath, emailSubject, senderEmail, senderPassword, recipient,
				minimumPasspercentage);
	}

}
