package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilsPackage.CommonUtils;

public class HomePage extends CommonUtils {

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@id='twotabsearchtextbox']")
	WebElement searchBox;

	@FindBy(how = How.XPATH, using = "//div[@class='nav-search-submit nav-sprite']//input[@class='nav-input']")
	WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Hello, Sign in')]")
	WebElement signInLink;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Hello,')]")
	WebElement greetingText;
	
	@FindBy(how = How.XPATH, using = "//input[@id='signInSubmit']")
	WebElement loginButton;
	
	@FindBy(how = How.XPATH, using = "//a[@id='createAccountSubmit']")
	WebElement creatAccountButton;
	
	@FindBy(how = How.XPATH, using = "//h4[@class='a-alert-heading']")
	WebElement wrongPhoneNumberErrorMessage;
	
	
	@FindBy(how = How.XPATH, using = "//i[@class='hm-icon nav-sprite']")
	WebElement hamburgerMenu;
	
	private static final String SUPER_CATEGORY= "//div[contains(@id,'hmenu-canvas')]//div[contains(text(),'%s')]";
	private static final String FIRST_LEVEL_CATEGORY= "//a[contains(text(),'%s')]";
	

	public boolean isSearchFieldDisplayed() {
		stepInfo("Check if Search field is displayed");
		return searchBox.isDisplayed();
	}

	public void searchForKeyword(String keyWord) {
		stepInfo("Enter Keyowrd " + keyWord);
		searchBox.sendKeys(keyWord);
		stepInfo("Click on Search button");
		searchButton.click();
	}

	public void clickSearchButton() {
		searchButton.click();
	}
	
	public SignInPage clickSignIn() throws InterruptedException {
		stepInfo("Click Sign In Link from header");
		signInLink.click();
		Thread.sleep(3000);
		return new SignInPage();
	}

	public void openHomePage() {
		String url = CommonUtils.properties.getProperty("appUrl");
		stepInfo("Open Home Page Url:" + url);
		driver.get(url);
	}
	
	public String getGreetingText() {
		stepInfo("Get the Greeting text from Account Block");
		return greetingText.getText();
	}
	
	public boolean isSignInLinkDisplayed() {
		stepInfo("Check if Signin link is displayed in header");
		return signInLink.isDisplayed();
	}
	
	public void clickHamBurgerMenu() throws InterruptedException {
		stepInfo("Click on Hamburger Menu");
		hamburgerMenu.click();
		Thread.sleep(2000);
	}
	
	public ProductListingPage navigateToProductListing(String superCategory, String firstLevelCategory) {
		stepInfo("Click on Super Category : "+ superCategory);
		driver.findElement(By.xpath(String.format(SUPER_CATEGORY, superCategory))).click();
		stepInfo("Click on First level Category : "+ firstLevelCategory);
		driver.findElement(By.xpath(String.format(FIRST_LEVEL_CATEGORY, firstLevelCategory))).click();
		return new ProductListingPage();
	}
}
