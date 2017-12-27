package main;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Grid.TestSuiteBase;
import page.LoginPage;
import utility.ExcelUtility;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

@Listeners(utility.Listener.class)
public class mainTestCase extends TestSuiteBase{
	private WebDriver driver;
	private ExtentReports extentReport;
	private ExtentTest extentTest;	
	private String Browser=null;
	private static LoginPage loginModule;
	
	//For Extent report test
	public ExtentTest getExtentTest() {
        return extentTest;
	}
	
  @BeforeClass
  // Reference the xml parameter
  @Parameters({"ReportPath","DocumentTitle","ReportName","UserName","Environment","ReportPhname","Browser","DDT"})
  public void beforeClass(String ReportPath,String DocumentTitle, String ReportName, String UserName, String Environment,String ReportPhname, String Browser, String DDT) throws Exception {
        driver = TestSuiteBase.getDriver();
        extentReport = TestSuiteBase.getExtentReport();		
		this.Browser = Browser;
		ExcelUtility.setExcelFile(DDT, "LoginTests");	//set the dataprovider
  }

  @Test
  public void loginTestCase(Method method) {
	  
	  extentTest = extentReport.createTest(Browser+"-"+method.getName());//Present the test case title
	  extentTest.assignCategory("Regression");//Add the regression tag
	  driver.get("http://www.letskodeit.com");
	  loginModule = new LoginPage(driver,extentReport, extentTest);
	  extentTest = loginModule.login("test@email.com", "abcabc");//Input account and password to test
	  boolean result = loginModule.isWelcomeTextPresent();// Return the test result
	  Assert.assertTrue(result);
	  loginModule.Logout();
  }
  
  
  @DataProvider(name = "loginData")
	public Object[][] dataProvider() {
		Object[][] testData = ExcelUtility.getTestData("Invalid_Login");
		return testData;
	}
  
  
  @Test(dataProvider="loginData",enabled=true)
  public void loginDataProviderCase(Method method,String username, String password) throws InterruptedException {
	  
	  extentTest = extentReport.createTest(Browser+"-"+method.getName());//Present the test case title

	  driver.get("http://www.letskodeit.com");
	  loginModule = new LoginPage(driver,extentReport, extentTest);	  
	  loginModule.clickSignUpLink(); // Find the sign up link  
	  loginModule.clickLoginLink();	 // Find the login link
	  loginModule.enterEmail(username);// Input the email
	  loginModule.enterPassword(password);// Input the password
	  loginModule.clickGoLink();// Click the Login button
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  boolean result = loginModule.isWelcomeTextPresent();	 //Return the result
	  
	  if(result==true)
	  {
		  extentTest.log(Status.INFO, "result:true");
		  Assert.assertTrue(result);
		  loginModule.Logout();
	  }
	  else if(result == false)
	  {   // Input the wrong account and password, the website should show the error message
		  extentTest.log(Status.INFO, "result:false");
		  loginModule.invalidLoginMessage();
	  }
  } 
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  @AfterMethod
	public void afterMethod(ITestResult testResult) throws URISyntaxException {
	  extentReport.flush();
	}
}

