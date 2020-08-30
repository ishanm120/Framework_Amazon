package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilsPackage.CommonConstants;
import utilsPackage.CommonUtils;
import utilsPackage.VerificationUtils;

public class ProductDetailsPage extends CommonUtils{
	
	public ProductDetailsPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//span[@id='productTitle']")
	WebElement itemTitle;
	
	@FindBy(how = How.XPATH, using = "//span[@id='priceblock_ourprice']")
	WebElement priceRange;
	
	@FindBy(how = How.XPATH, using = "//input[@id='add-to-cart-button']")
	WebElement addToCartButton;
	
	@FindBy(how = How.XPATH, using = "//input[@id='buy-now-button']")
	WebElement buyNowButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='a-section askDetailPageSearchWidgetSection']")
	WebElement havAQuestionSection;
	
	@FindBy(how = How.XPATH, using = "//a[@class='nav-a nav-a-2']")
	WebElement cartIcon;
	
	@FindBy(how = How.XPATH, using = "//select[@id='native_dropdown_selected_size_name']")
	WebElement itemSize;
	
	@FindBy(how = How.XPATH, using = "//span[@id='priceblock_ourprice']")
	WebElement itemPrice;
	
	@FindBy(how = How.XPATH, using = "//select[@id='quantity']")
	WebElement itemQty;
	
	public boolean isProductDetailPageOpened() {
		stepInfo("Check if Product detail page is opened");
		boolean isOpened= true;
		isOpened &= VerificationUtils.equalBooleanData("Verify Page title text is displayed", itemTitle.isDisplayed(), true);
		isOpened &= VerificationUtils.equalBooleanData("Verify 'Price Range' section is displayed", priceRange.isDisplayed(), true);
		isOpened &= 	VerificationUtils.equalBooleanData("Verify 'Add to Cart' button is displayed", addToCartButton.isDisplayed(), true);	
		isOpened &= 	VerificationUtils.equalBooleanData("Verify 'Buy Now' button displayed", buyNowButton.isDisplayed(), true);
		isOpened &= 	VerificationUtils.equalBooleanData("Verify Having Question section is displayed", havAQuestionSection.isDisplayed(), true);
	return isOpened;
	}
	
	public void clickAddToCartButton() {
		stepInfo("Click on Add to cart Button");
		addToCartButton.click();
	}
	
	public void saveProductInfo() {
		stepInfo("Save Product info from Product Details Page");
		productName = itemTitle.getText();
		stepInfo("Name of the Product is : "+ productName);
		productSize = getSelectedValue(itemSize);
		stepInfo("Size of the Product is : "+ productSize);
		productQty  = Integer.parseInt(getSelectedValue(itemQty));
		stepInfo("Quantity of the Product is : "+ productQty);
		productPrice= getProductPrice();
		stepInfo("Price of the Product is : "+ productPrice);
	}
	
	public BasketPage navigateToBasketPage() throws InterruptedException {
		stepInfo("Navigate to Basket Page");
		cartIcon.click();
		Thread.sleep(4000);
		return new BasketPage();
	}
	
	public void selectProductSize(String size) throws InterruptedException {
		selectByVisibleText(itemSize, size);
	}
	
	public void selectProductQty(String qty) throws InterruptedException {
		selectByVisibleText(itemQty, qty);
	}
	
	public String getProductPrice() {
		return itemPrice.getText();
	}
	
	 

}
