package pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class login_page {
	public Properties prop;
	public FirefoxDriver driver;
	FileInputStream input;
	
	@FindBy(how = How.CLASS_NAME, using = "dropdown-toggle")
	private WebElement login_button;
	@FindBy(how = How.ID, using = "loginform:user_username")
	private WebElement userField;
	@FindBy(how = How.ID, using = "loginform:user_password")
	private WebElement passwordField;
	@FindBy(how = How.ID, using = "loginform:login_button")
	private WebElement login_submit;
	@FindBy(how = How.LINK_TEXT, using = "Logout")
	private WebElement logoutLink;
	
	

	public void load_data() {
		prop = new Properties();
		try {
			input = new FileInputStream("config.properties");
			// load a properties file
			prop.load(input);
			// get the property value and print it out
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public FirefoxDriver setUpProfile() {
		load_data();
		String FireFoxPath = prop.getProperty("FireFoxPath");
		File pathToBinary = new File(FireFoxPath);
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		FirefoxDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
		String base_url = prop.getProperty("url");
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		driver.get(base_url);
		return driver;
	}

	public void pageTearDown(FirefoxDriver driver) {
		driver.quit();
	}

	public void login(FirefoxDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		login_button.click();
		String username = prop.getProperty("userName");
		String password = prop.getProperty("password");
		userField.sendKeys(username);
		passwordField.sendKeys(password);
		login_submit.click();
	}
	
	
	public void logout(FirefoxDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		String s =  driver.getPageSource();
		System.out.println(s);
		logoutLink.click();
	}
	

}