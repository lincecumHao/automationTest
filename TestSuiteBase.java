package Grid;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import report.ExtentManager;
import utility.utilityClass;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;

public class TestSuiteBase {
	protected static WebDriver driver;
	
	private static ExtentReports extentReport;
	protected static ExtentTest test;
	private static String RemoteMode=null;
	
	public static WebDriver getDriver() {
        return driver;
	}
	public static ExtentReports getExtentReport() {
        return extentReport;
	}
	
	@Parameters({ "DocumentTitle","ReportName","UserName","Environment","ReportPhname","RemoteMode" })
	@BeforeSuite
    public void BeforeSuite(String DocumentTitle, String ReportName, String UserName, String Environment,String ReportPhname,String  RemoteMode) throws MalformedURLException, URISyntaxException
	{	
		this.RemoteMode = RemoteMode;
		//Create the extentReport
		extentReport = ExtentManager.createInstance(utilityClass.GetRunLocation(),DocumentTitle,ReportName,UserName,Environment,ReportPhname);
    }

	@Parameters({ "platform","Browser","version", "url","ReportPath","DocumentTitle","ReportName","UserName","Environment","ReportPhname","applicationName" })
    @BeforeClass
    public void setup(String platform, String Browser, String
      version, String url,String ReportPath,String DocumentTitle, String ReportName, String UserName, String Environment,String ReportPhname, String applicationName) throws MalformedURLException, URISyntaxException
	{
		//Set the driver parameter
		driver = getDriverInstance(platform, Browser, version, url, applicationName);
    }
	
	public static WebDriver getDriverInstance(String platform, String browser, String version, String url, String applicationName)
			throws MalformedURLException {
				
		WebDriver driver = null;
		DesiredCapabilities caps = new DesiredCapabilities();
		
		// Platforms
		if (platform.equalsIgnoreCase("Windows")) {
			caps.setPlatform(Platform.WINDOWS);
		}
		else if (platform.equalsIgnoreCase("MAC")) {
			caps.setPlatform(Platform.MAC);
		}
		// Browsers
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\10606303\\eclipse-workspace\\ProjectTemplate\\WebDriver\\chromedriver.exe");
			caps = DesiredCapabilities.chrome();
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.firefox.driver", "C:\\Users\\10606303\\eclipse-workspace\\ProjectTemplate\\WebDriver\\geckodriver.exe");
			caps = DesiredCapabilities.firefox();
		}
		
		// applicationName
		if (applicationName.equalsIgnoreCase("windows10_64bits_chrome") & RemoteMode.equals("true")) {
			String nodeURL = "http://127.0.0.1:5555/wd/hub";
			caps.setCapability(applicationName, "windows10_64bits_chrome");
			caps.setVersion(version);
			driver = new RemoteWebDriver(new URL(nodeURL), caps);
			// Maximize the browser's window
		    driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			// Open the Application
			driver.get(url);
		}
		else if (applicationName.equalsIgnoreCase("windows7_32bits_firefox") & RemoteMode.equals("true")) {
			String nodeURL = "http://127.0.0.1:5556/wd/hub";
			caps.setCapability(applicationName, "windows7_32bits_firefox");
			caps.setVersion(version);
			driver = new RemoteWebDriver(new URL(nodeURL), caps);
			// Maximize the browser's window
		    driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			// Open the Application
			driver.get(url);
		}
		else // Single Mode
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\10606303\\eclipse-workspace\\ProjectTemplate\\WebDriver\\chromedriver.exe");
			caps = DesiredCapabilities.chrome();
			caps.setCapability(applicationName, "windows10_64bits_chrome");
			caps.setVersion(version);
			driver = new ChromeDriver(caps);
			// Maximize the browser's window
		    driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			// Open the Application
			driver.get(url);
		}		
		return driver;
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Class:quit");
		driver.quit();
	}
	@AfterSuite
	public void afterSuite() {
		System.out.println("Suite:quit");
	}
}