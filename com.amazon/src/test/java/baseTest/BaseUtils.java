package baseTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;

import pageObjects.HomePage;
import utilsPackage.CommonConstants;
import utilsPackage.CommonUtils;
import utilsPackage.EmailCode;

public class BaseUtils extends CommonUtils {
	
	public static SoftAssert softAssert = new SoftAssert();

	public HomePage homePage;

	@BeforeMethod
	public void setUpDriver(Method m) throws FileNotFoundException, MalformedURLException, InterruptedException {
		test = extent.createTest(m.getName());
		setUpDriverPage();
		homePage = new HomePage();
		stepInfo("<-------START TEST CASE :  " + m.getName() + " ----------->");
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			stepInfo("-------Test Case with name " + result.getName() + " is [ FAILED ] ------------------");
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			String screenshotPath = TakeScreenshot(driver, result.getName());

			test.fail("Test Case Failed Snapshot is below " , MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			stepInfo("-------Test Case with name " + result.getName() + " is [ SKIPPED ] ------------------");
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			stepInfo("-------Test Case with name " + result.getName() + " is  [ PASSED ] --------------------");
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		}
		//clearCookies();
		stepInfo("Closing current Browser Instance ======== " + Thread.currentThread().getId());
		
		driver.close();
	}

	@BeforeSuite
	public void extentReportInt(ITestContext itc) throws EmailException {
		suiteName= itc.getCurrentXmlTest().getSuite().getName();
		extentReportSetup();
	}

	@AfterSuite
	//@Parameters("sendEmail")
	public void endReport() throws EmailException {
		extent.flush();
		String sendEmail= System.getProperty(CommonConstants.SEND_EMAIL_PARAMETER)==null ? environmentProperties.getProperty(CommonConstants.SEND_EMAIL_PARAMETER)  :System.getProperty(CommonConstants.SEND_EMAIL_PARAMETER);
		if(sendEmail.equals("Yes")) {
		sendEmail(reportFolderPath, extentReportPath, EmailCode.reportEmailSubject, EmailCode.senderEmailId, EmailCode.senderEmailPassword, EmailCode.recipitentEmail, EmailCode.minimumPassPercentage);
		}
	}

	public static String TakeScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String destination = null;
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destination;
	}
}
