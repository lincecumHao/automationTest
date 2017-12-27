package page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utility.CustomListener;


@Listeners(CustomListener.class)
public class LoginPage {
    ExtentReports extent;
	ExtentTest test;
	WebDriver driver = null;

	public LoginPage(WebDriver driver, ExtentReports extent,ExtentTest test) {
		this.driver = driver;
		this.extent = extent;
		this.test = test;
	}
	
	// Find the sign up link
	public void clickSignUpLink() {
		WebElement signupLink = driver.findElement(By.id("comp-iiqg1vggactionTitle"));
		signupLink.click();
		test.log(Status.INFO, "Clicked on signup link");
	}
	
	// Find the login link
	public void clickLoginLink() {
		WebElement loginLink = driver.findElement(By.id("signUpDialogswitchDialogLink"));
		loginLink.click();
		test.log(Status.INFO, "Clicked on login link");
	}
	
	// Input the email
	public void enterEmail(String email) {
		WebElement emailField = driver.findElement(By.xpath("//div[@id='memberLoginDialogemail']//input"));
		emailField.sendKeys(email);
		test.log(Status.INFO, "Enter email");
	}
	
	// Input the password
	public void enterPassword(String password) {
		WebElement passwordField = driver.findElement(By.xpath("//div[@id='memberLoginDialogpassword']//input"));
		passwordField.sendKeys(password);
		test.log(Status.INFO, "Enter password");
	}
	
	// Click the Login button
	public void clickGoLink() {
		test.log(Status.INFO, "watch");
		WebElement goButton = driver.findElement(By.xpath("//div[@id='memberLoginDialogoklink']"));//
		goButton.click();
		test.log(Status.INFO, "Clicked Go button");
	}
	
	// Website show the invalid login message
	public void invalidLoginMessage() {
		test.log(Status.INFO, "invalidLoginMessage");
		WebElement invalidTextField =driver.findElement(By.xpath("//p[@id=\"memberLoginDialogpasswordInputerrorMessage\"]"));
	
		if(invalidTextField.getText().equals("Wrong email or password"))
			test.log(Status.INFO,"It shows Wrong email or password");
		
	}
	
	// Login success, it will show the welcome
	public boolean isWelcomeTextPresent() {
		
		WebElement welcomeText = null;
		try {
			welcomeText = driver.findElement(By.xpath("//div[text()='Hello test@email.com']"));
			if (welcomeText != null) 
				return true;
			else
				return false;
			
		}
		catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			test.log(Status.INFO, e.getMessage());
			test.log(Status.INFO, "return false");
			return false;
		}
	}
	
	public ExtentTest login(String email, String password) {
		test.log(Status.INFO, "Start login work");
		clickSignUpLink();
		clickLoginLink();
		enterEmail(email);
		enterPassword(password);
		clickGoLink();

		return test;
	}
	
	// Logout function
	public void Logout() {
		test.log(Status.INFO, "watch");
		WebElement LogoutButton = driver.findElement(By.xpath("//button[@id=\"comp-iiqg1vggactionTitle\"]"));
		LogoutButton.click();
		test.log(Status.INFO, "Logout");
	}
}
