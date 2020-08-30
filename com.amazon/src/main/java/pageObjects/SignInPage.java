package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilsPackage.CommonConstants;
import utilsPackage.CommonUtils;
import utilsPackage.VerificationUtils;

public class SignInPage extends CommonUtils{
	
	public SignInPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@id='ap_email']")
	WebElement emailorPhoneField;

	@FindBy(how = How.XPATH, using = "//input[@id='ap_password']")
	WebElement passwordField;
	
	@FindBy(how = How.XPATH, using = "//input[@id='continue']")
	WebElement continueButton;
	
	@FindBy(how = How.XPATH, using = "//input[@id='signInSubmit']")
	WebElement loginButton;
	
	@FindBy(how = How.XPATH, using = "//a[@id='createAccountSubmit']")
	WebElement creatAccountButton;
	
	@FindBy(how = How.XPATH, using = "//h4[@class='a-alert-heading']")
	WebElement wrongPhoneNumberErrorMessage;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Enter your')]")
	WebElement blankPhoneNumberErrorMessage;
	
	@FindBy(how = How.XPATH, using = "//div[@id='auth-error-message-box']")
	WebElement wrongPasswordErrorMessage;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Enter your')]")
	WebElement blankPasswordErrorMessage;
	
	@FindBy(how = How.XPATH, using = "//a[@id='auth-fpp-link-bottom']")
	WebElement forgotPasswordLink;
	
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Password assistance')]")
	WebElement resetPasswordTitle;
	
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Enter the email address or mobile phone number')]")
	WebElement resetPasswordDescription;
	
	@FindBy(how = How.XPATH, using = "//input[@id='ap_email']")
	WebElement resetPasswordEmailInput;
	
	@FindBy(how = How.XPATH, using = "//input[@id='continue']")
	WebElement resetPasswordContinueButton;
	
	
	public void enterEmailId(String email) {
		stepInfo("Enter Email id: " + email);
		emailorPhoneField.sendKeys(email);
	}
	
	public SignInPage enterPhone(String phoneNumber) {
		stepInfo("Enter Phone Number: " + phoneNumber);
		emailorPhoneField.sendKeys(phoneNumber);
		return new SignInPage();
	}
	
	public SignInPage enterPassword(String password) {
		stepInfo("Enter Password: " + password);
		passwordField.sendKeys(password);
		return new SignInPage();
	}
	
	public HomePage clickLoginButton() throws InterruptedException {
		stepInfo("Click on Login button");
		loginButton.click();
		Thread.sleep(3000);
		return new HomePage();
	}
	
	public SignInPage clickContinueButton() throws InterruptedException {
		stepInfo("Click on Continue button");
		continueButton.click();
		Thread.sleep(3000);
		return new SignInPage();
	}
	
	public String getWrongPhoneNumerErrorMessage() {
		stepInfo("Get Wrong Phone Number Error message");
		return wrongPhoneNumberErrorMessage.getText();
	}
	
	public String getBlankPhoneNumerErrorMessage() {
		stepInfo("Get Blank Phone Number Error message");
		return blankPhoneNumberErrorMessage.getText();
	}
	
	public String getWrongPasswordErrorMessage() {
		stepInfo("Get Wrong Password Error message");
		return wrongPasswordErrorMessage.getText();
	}
	
	public String getBlankPasswordErrorMessage() {
		stepInfo("Get Blank Password Error message");
		return blankPasswordErrorMessage.getText();
	}
	
	public void clickForgotPasswordLink() throws InterruptedException {
		stepInfo("Click on Forgot password link");
		forgotPasswordLink.click();
		Thread.sleep(2000);
	}
	
	public boolean isPasswordRestPageOpened() {
		stepInfo("Check if Password Reset page is opened");
		boolean isOpened= true;
		isOpened &= VerificationUtils.containsString("Verify Page title text is displayed", resetPasswordTitle.getText(), CommonConstants.RESET_PASSWORD_PAGE_TITLE);
		isOpened &= VerificationUtils.containsString("Verify Page Description text is displayed", resetPasswordDescription.getText(), CommonConstants.RESET_PASSWORD_PAGE_DESCRIPTION);
		isOpened &= 	VerificationUtils.equalBooleanData("Verify Email/Phone input filed is displayed", resetPasswordEmailInput.isDisplayed(), true);	
		isOpened &= 	VerificationUtils.equalBooleanData("Verify Continue button is displayed", resetPasswordContinueButton.isDisplayed(), true);
	return isOpened;
	}
	

}
