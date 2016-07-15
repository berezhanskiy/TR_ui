package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.DefaultDriverProvider;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import BrowserFactory.WebDriverFactory;
import BrowserFactory.WebDriverFactoryInternal;
import BrowserFactory.WebDriverFactoryMode;
import help.BrowserFactroryCastom;

public class BaseTest {
	
	private WebDriverFactoryInternal factory;
	private DesiredCapabilities myCapabilities;
	protected static WebDriver driver;
	//protected ProfilesIni profile;
	//FirefoxProfile myprofile;
	//String FFprofilePath = "C:\\Users\\berez\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\wm9yt2kv.QAprofile";
    //File profilePath;
	 

	@BeforeSuite
	public void setUp() {
		
		String name = "chrome";
		switch(name){
		 case "chrome":{
			 WebDriverFactory.setMode(WebDriverFactoryMode.SINGLETON);
			 System.setProperty("webdriver.chrome.driver", "D:\\work\\selenium\\web drivers\\chromedriver_win32\\chromedriver.exe");
			 Capabilities chrome = DesiredCapabilities.chrome();
			 driver = WebDriverFactory.getDriver(chrome);
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 break;
		 }
		 case "firefox":{
			 WebDriverFactory.setMode(WebDriverFactoryMode.SINGLETON);
			// DesiredCapabilities.firefox().setCapability(FirefoxDriver.PROFILE, myprofile);
			 Capabilities firefox = DesiredCapabilities.firefox();
			 driver = WebDriverFactory.getDriver(firefox);
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 break;
		 }
		 case "edge":{
			 WebDriverFactory.setMode(WebDriverFactoryMode.SINGLETON);
			 System.setProperty("webdriver.edge.driver", "C:\\Program Files (x86)\\Microsoft Web Driver\\MicrosoftWebDriver.exe");
			 Capabilities edge = DesiredCapabilities.edge();
			 driver = WebDriverFactory.getDriver(edge);
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 //driver.manage().window().maximize();
			 break;
		 }
		 default:{
			 System.out.println("this is default switch flow");
			 break;
		 }
			 
	   }
	}
	/*@BeforeMethod
	public void setFFprofile(){
		profilePath = new File(FFprofilePath);
		//profile  = new ProfilesIni();
		myprofile = new FirefoxProfile(profilePath);
		
		DesiredCapabilities cap = new DesiredCapabilities();
		FirefoxDriver.PROFILE, myprofile));
		
		Capabilities capp = cap.setCapability(FirefoxDriver.PROFILE, myprofile);
		driver = WebDriverFactory.getDriver(capp);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}*/
	
	
	@AfterSuite
	public void gameOver(){
		WebDriverFactory.dismissAll();
	}
	
	
 
}
