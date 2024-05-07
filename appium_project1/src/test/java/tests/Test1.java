package tests;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import pages.Catalog;

public class Test1 extends BaseClass{
	
	@Test
	public void test1() throws Exception
	{
		catalog.verifyPage();
		catalog.clickMenu();
		catalog.verifyMenuItems();
	}
}
