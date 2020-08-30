package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseUtils;
import pageObjects.SignInPage;
import utilsPackage.CommonConstants;
import utilsPackage.VerificationUtils;

public class SignInPageTest extends BaseUtils {

	SignInPage signInPage;

	@Test(groups="Regression")
	public void verifySuccessfulLogin() throws InterruptedException {
		homePage.openHomePage();
		homePage.clickSignIn()
				.enterPhone(CommonConstants.VALID_PHONE_NUMER)
				.clickContinueButton()
				.enterPassword(CommonConstants.VALID_PASSWORD)
				.clickLoginButton();
		Assert.assertEquals(VerificationUtils.containsString(String.format("verify Greeting Text contains Logged in user name ",CommonConstants.VALID_USERNAME),homePage.getGreetingText(), CommonConstants.VALID_USERNAME), true);
	}

	@Test
	public void verifyErrorMessageForInvalidEmailID() throws InterruptedException {
		homePage.openHomePage();
		signInPage = homePage.clickSignIn();
		signInPage.enterPhone(CommonConstants.INVALID_PHONE_NUMER).clickContinueButton();
		Assert.assertEquals(VerificationUtils.containsString("Verify Error message on Wrong Phone number Input",signInPage.getWrongPhoneNumerErrorMessage(), CommonConstants.WRONG_PHONE_NUMER_ERROR_MESSAGE), true);

	}
	
	@Test
	public void verifyErrorMessageForBlankEmailID() throws InterruptedException {
		homePage.openHomePage();
		signInPage = homePage.clickSignIn();
		signInPage.clickContinueButton();
		Assert.assertEquals(VerificationUtils.containsString("Verify Error message for Blank phone number Input",signInPage.getBlankPhoneNumerErrorMessage(), CommonConstants.BLANK_PHONE_NUMER_EROR_MESSAGE), true);

	}
	
	@Test(groups="Regression")
	public void verifyErrorMessageForInvalidPassword() throws InterruptedException {
		homePage.openHomePage();
		signInPage = homePage.clickSignIn();
		signInPage.enterPhone(CommonConstants.VALID_PHONE_NUMER)
				  .clickContinueButton()
				  .enterPassword(CommonConstants.INVALID_PASSWORD)
				  .clickLoginButton();
		Assert.assertEquals(VerificationUtils.containsString("Verify Error message on Wrong Password Input",signInPage.getWrongPasswordErrorMessage(), CommonConstants.WRONG_PASSWORD_ERROR_MESSAGE), true);

	}
	
	@Test(priority=1)
	public void verifyErrorMessageForBlankPassword() throws InterruptedException {
		homePage.openHomePage();
		signInPage = homePage.clickSignIn();
		signInPage.enterPhone(CommonConstants.VALID_PHONE_NUMER)
				  .clickContinueButton()
				  .clickLoginButton();
		Assert.assertEquals(VerificationUtils.containsString("Verify Error message on Blank Password Input",signInPage.getBlankPasswordErrorMessage(), CommonConstants.BLANK_PASSWORD_EROR_MESSAGE), true);

	}
	
	@Test
	public void verifyResetPasswordPage() throws InterruptedException {
		homePage.openHomePage();
		signInPage = homePage.clickSignIn();
		signInPage.enterPhone(CommonConstants.VALID_PHONE_NUMER)
				  .clickContinueButton()
				  .clickForgotPasswordLink();
		Assert.assertEquals(signInPage.isPasswordRestPageOpened(), true);

	}

}
