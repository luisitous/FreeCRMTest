//TEST GIT
package com.crm.qa.testcases;

import org.openqa.selenium.mobile.NetworkConnection;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	
	public ContactsPageTest() {
		super();
	}
	
	//Test Cases should be separated -- independent with each other
		//@Before each test case launch the browser and login
		//@Test execute the Test Case
		//@After each Test case Close the browser
		
		
		@BeforeMethod
		public void setup() {
			initialization();
			testUtil = new TestUtil();
			contactsPage = new ContactsPage();
			loginPage = new LoginPage();	
			homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
			testUtil.switchToFrame();
			contactsPage = homePage.clickOnContactsLink();
			
			
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
		public void verifyContactsPageLabel() {
			Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contacts label is Missing on the Page");
		}
		
		@Test(priority=2)
		public void selectSingleContactsTest(){
			contactsPage.selectContactsByName("A3 A4");
		}
		
		@Test(priority=3)
		public void selectMultipleContactsTest(){
			contactsPage.selectContactsByName("A3 A4");
			contactsPage.selectContactsByName("Aashley Aaskers");
		}
		
		
		@DataProvider
		public Object[][] getCRMTestData() {
			Object data [][] = TestUtil.getTestData(sheetName);
			return data;
			
		}
		
		@Test(priority=4, dataProvider="getCRMTestData")
		public void validateCreateNewContact(String title, String ftName, String ltName, String company) {
			homePage.clickOnNewContactLink();
			//contactsPage.createNewContact("Mr.", "Tomas", "Peters", "Google");
			contactsPage.createNewContact(title, ftName, ltName, company);
		}
		
		
		@AfterMethod
		public void tearDown() {
					driver.quit();
		}
		
		
		
		
		
}
