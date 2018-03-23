package com.crm.qa.testcases;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.crm.qa.pages.ContactsPage;

public class HomePageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	
	
	
	public HomePageTest() {
		super();
	}
	
	
	//Test Cases should be separated -- independent with each other
	//@Before each test case launch the browser and login
	//@Test execute the Test Case
	//@After each Test case Close the browser
	
/*	@BeforeSuite
	public void beforeSuite() {
		extent = new ExtentReports ("/home/lwyz/Workspace/FreeCRMTest/test-output/MyExtentReport.html", true);
		extent.loadConfig(new File("/home/lwyz/Workspace/FreeCRMTest/src/main/resources/extent-config.xml"));
		
	}
	*/
	@BeforeMethod
	public void setup() {
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();	
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
		try        
		{
		    Thread.sleep(1000);
		} 
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}
	
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO", "Home Page title is not Correct");
		
	}
	
	@Test(priority=2)
	public void verifyUserNameTest() {
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());		
	}
	
	@Test(priority=3)
	public void verifyContactLinkTest() {
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
		
		
		
	}
	
		
	@AfterMethod
	public void tearDown() {
				driver.quit();
	}
	
	/*@AfterSuite
	public void adterSuite() {
		extent.flush();
		extent.close();
	}
	*/
	
}
