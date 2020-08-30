package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseUtils;
import pageObjects.BasketPage;
import pageObjects.ProductDetailsPage;
import pageObjects.ProductListingPage;
import utilsPackage.CommonConstants;

public class BasketPageTest extends BaseUtils{
	ProductListingPage productListingPage;
	ProductDetailsPage productDetailsPage;
	BasketPage basketPage;
	
	@Test(groups="Regression")
	public void verifyProductAddedToBasket() throws InterruptedException {
		homePage.openHomePage();
		homePage.clickSignIn()
				.enterPhone(CommonConstants.VALID_PHONE_NUMER)
				.clickContinueButton()
				.enterPassword(CommonConstants.VALID_PASSWORD)
				.clickLoginButton();
		homePage.clickHamBurgerMenu();
		productListingPage= homePage.navigateToProductListing(CommonConstants.SUPER_CATEGORY_MEN_FASHION, CommonConstants.FIRST_LEVEL_CATEGORY_SHIRTS);
		productDetailsPage= productListingPage.clickOnProductImage(CommonConstants.ONE);
		productDetailsPage.selectProductSize(CommonConstants.SHIRT_SIZE_FORTY);
		productDetailsPage.selectProductQty(CommonConstants.ONE);
		productDetailsPage.saveProductInfo();
		productDetailsPage.clickAddToCartButton();
		basketPage= productDetailsPage.navigateToBasketPage();
		Assert.assertEquals(basketPage.isProductAddedToBasket(), true);
	}

}
