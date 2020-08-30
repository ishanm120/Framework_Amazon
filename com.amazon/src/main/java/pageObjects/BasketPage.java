package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilsPackage.CommonUtils;
import utilsPackage.VerificationUtils;

public class BasketPage extends CommonUtils{
	
	public BasketPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'a-fixed-left-grid-col a-col-right')]/ul/li/span[contains(@class,'a-list-item')])[1]")
	WebElement itemTitle;
	
	@FindBy(how = How.XPATH, using = "(//span[contains(@class,'a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold')])[1]")
	WebElement itemPrice;
	
	@FindBy(how = How.XPATH, using = "//select[@name='quantity']")
	WebElement itemQty;
	
	
	public boolean isProductAddedToBasket() {
		stepInfo("Verify if Product is Added to Basket");
		boolean isOpened= true;
		isOpened &= VerificationUtils.containsString("Verify Product tile on Basket Page", itemTitle.getText(), productName);
		//isOpened &= VerificationUtils.containsString("Verify Product Price", itemPrice.getText(), productPrice);
		//isOpened &= VerificationUtils.equalIntegerData("Verify Product Quantity", Integer.parseInt(getSelectedValue(itemQty)), productQty);
	return isOpened;
	}
	
	

}
