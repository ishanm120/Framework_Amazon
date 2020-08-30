package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseUtils;
import utilsPackage.CommonConstants;
import utilsPackage.VerificationUtils;

public class HomePageTest extends BaseUtils{
	
	
	@Test
	public void verifySearchFieldDisplayed() {
		homePage.openHomePage();
		Assert.assertEquals(true, homePage.isSearchFieldDisplayed());
	}
	
	//@Test(groups="Regression")
	public void verifySearchKeyword() {
		homePage.openHomePage();
		homePage.searchForKeyword(CommonConstants.SEARCH_KEYWORD_SHOES);
		Assert.assertEquals(true, VerificationUtils.containsString("Verify Shoes is presnet in title: ", driver.getTitle(), CommonConstants.SEARCH_KEYWORD_SHOES));
	}
}
