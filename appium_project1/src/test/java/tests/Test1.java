package tests;

import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pages.CartPage;
import pages.CatalogPage;
import pages.DrawingPage;
import pages.ProductPage;

public class Test1 extends BaseClass{
	
	private CatalogPage catalogPage;
	private CartPage cartPage;
	private ProductPage productPage;
	@Test
	public void test1() throws Exception
	{
		catalogPage = new CatalogPage(driver);
		Reporter.log("Product details\n"+catalogPage.getAllProductDetails());
		catalogPage.navigateToProduct(2);
		productPage = new ProductPage(driver);
//		productPage.addProducts(3);
//		productPage.navigateToCatalog();
//		catalogPage.navigateToProduct(4);
//		productPage.addProducts(1);
//		productPage.navigateToCatalog();
//		catalogPage.navigateToProduct(2);
//		productPage.addProducts(3);
//		productPage.navigateToCatalog();
//		catalogPage.navigateToProduct(1);
//		productPage.addProducts(5);
//		productPage.navigateToCatalog();
		productPage.addProducts(1);
		productPage.navigateToCatalog();
		
		
		catalogPage.navigateToCart();
		cartPage = new CartPage(driver);
		
		Reporter.log("All items in cart\n"+cartPage.getProductDetails());
		
		Reporter.log("Total Items: "+cartPage.getTotalItems());
		Reporter.log("Total Price: "+cartPage.getTotalPrice());
		
		
	}
}
