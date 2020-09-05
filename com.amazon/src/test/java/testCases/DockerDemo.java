package testCases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class DockerDemo {
	
	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setBrowserName(BrowserType.FIREFOX);
		cap.setPlatform(Platform.LINUX);
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.58:4445/wd/hub"), cap);
		driver.get("http://www.google.com");
		Thread.sleep(5000);
		driver.quit();
		
	}
	
//	@Test
//	public void demo() throws MalformedURLException, InterruptedException {
//		DesiredCapabilities cap= new DesiredCapabilities();
//		cap.setBrowserName(BrowserType.CHROME);
//		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4546/web/hub"),cap);
//		driver.get("http://www.google.com");
//		Thread.sleep(5000);
//		driver.quit();
//	}

}
