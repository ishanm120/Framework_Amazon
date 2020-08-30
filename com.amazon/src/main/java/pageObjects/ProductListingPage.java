package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilsPackage.CommonUtils;

public class ProductListingPage extends CommonUtils {

	public ProductListingPage() {
		PageFactory.initElements(driver, this);
	}

	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(@class,'a-section octopus-pc-item-hue-shield octopus-pc-item-image-background-v3')]") })
	private List<WebElement> productItems;
	
	public ProductDetailsPage clickOnProductImage(String index) throws InterruptedException {
		stepInfo("Click on "+index+ " Product on Listing page");
		productItems.get(Integer.parseInt(index)-1).click();
		Thread.sleep(5000);
		return new ProductDetailsPage();
	}

}
