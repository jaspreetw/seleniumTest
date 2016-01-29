package testspackage;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pageObjects.login_page;

/**
 * @author Anand.Bajpai
 * This class contains the set tests for sNw
 * web. These tests uses user-defined parameters
 * given in config.properties file
 * 
 */

public class TestNGSimpleTest {
	login_page login_test = new login_page();
	FirefoxDriver driver;

	/**
	 * This is a set up step. 
	 * It will initialize the browser and set the
	 * firefox browser path for us. please check
	 * the entry "FireFoxPath" and change it as 
	 * per your system.
	 * 
	 * This also open the "url" as given in 
	 * config.properties file.
	 */
	@BeforeSuite(alwaysRun = true)
	public void driversetup() {
		driver = login_test.setUpProfile();
	}

	/**
	 * This test is basic test for login and  logout.
	 * Please check the parameters "username", password
	 * in config.properties file.
	 */
	@Test(groups = {"funtional"})
	public void basicTest() {
		login_test.login(driver);
		login_test.logout(driver);
	}
	
	/**
	 * This test is basic test for login
	 * Please check the parameters "username", password
	 * in config.properties file.
	 */
	@Test(groups = { "Sanity" })
	public void basicLoginTest() {
		login_test.login(driver);
	}
	
	
	/**
	 * This test is basic test for logout.
	 */
	@Test(groups = { "Sanity" })
	public void basicLogoutTest() {
		login_test.logout(driver);
	}

}