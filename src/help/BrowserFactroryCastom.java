package help;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class BrowserFactroryCastom {
	private static WebDriver driver = null;
	private static String driverChromPath = "D:\\work\\selenium\\web drivers\\chromedriver_win32\\chromedriver.exe";
	private static String driverEdgePath = "C:\\Program Files (x86)\\Microsoft Web Driver\\MicrosoftWebDriver.exe";
	private static String appURL = "http://topreviews.best/";
	private static String ukURL = "http://uk.http://topreviews.best/";
	
	
	
	
	
	
	//method for start or get driver
	public static WebDriver startGetDriver(String driverName){
		if(driver==null){
			switch(driverName){
				case "Chrome":
					driver = BrowserFactroryCastom.initChromeDriver(appURL);
					break;
		
				case "Firefox":
					driver = BrowserFactroryCastom.initFireFoxDriver(appURL);
					break;
			
				case "Edge":
					driver = BrowserFactroryCastom.initEdgeDriver(appURL);
					break;
					
				default:
					System.out.println("this browser name doesn't exist");
					break;
				}
			}
		return driver;
	}
	
	
	
	//method for initialization Chrome driver
	public static WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", driverChromPath);
		driver = new ChromeDriver();
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(appURL);
		return driver;
	}
	
	//method for initialization Edge driver
	public static WebDriver initEdgeDriver(String appURL){
		System.out.println("Launching windows edge with new profile..");
		System.setProperty("webdriver.edge.driver", new File(driverEdgePath).getAbsolutePath());
		DesiredCapabilities capabilities = DesiredCapabilities.edge();
		driver = new EdgeDriver(capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(appURL);
		return driver;
	}
	
	//method for initialization FireFox driver
	public static WebDriver initFireFoxDriver(String appURL){
		System.out.println("Launching windows FireFox with new profile..");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		driver = new FirefoxDriver(capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(appURL);
		return driver;
	}
	
	
	
	
	//method for return current URL of the page
	public static String currentURL(){
		String url = driver.getCurrentUrl();
		return url;
	} 
	
	//method for return page title
	public static String pageTitle(){
		String pageTitle = driver.getTitle();
		return pageTitle;
	}
		 
	public static String getPageSource(String URL){
		String pageSource = driver.getPageSource();
		return pageSource;
	}
		

	public boolean isElementPresentByClass(String classNameSearch){
		try{
			driver.findElement(By.className(classNameSearch));
			return true;
		}
		catch(NoSuchElementException e){
			return false;
		}
	}

	public static boolean isElementPresentByXpath(String elementXpath){
		try{
			driver.findElement(By.xpath(elementXpath));
			return true;
		}
		catch(NoSuchElementException e){
			return false;
		}
	}

	public boolean isElementPresentByName(String elementName){
		try{
			driver.findElement(By.linkText(elementName));
			return true;
		}
		catch(NoSuchElementException e){
			return false;
		}
	}

	//method for return web element founded via xpath
	public static WebElement findElementByXpath(String xpath){
		WebElement element;
		element = driver.findElement(By.xpath(xpath));
		return element;
	}
	
	public static WebDriver getDriverObj(){
		return driver;
	}
	
	//method for navigate driver back to previous page
	public static void moveBack(){
		driver.navigate().back();
	}
	
	//method for open url in browser
	public static void moveOn(String URL){
		driver.get(URL);
	}
	
	//method for find element by id
	public static WebElement findElementById(String id){
		WebElement element;
		element = driver.findElement(By.id(id));
		return element;
	}
	
	//method for find element by css selector
		public static WebElement findElementByCSS(String CSS){
			WebElement element;
			element = driver.findElement(By.cssSelector(CSS));
			return element;
		}
		

	
	public static void tearDown() {
		driver.quit();
	}
}


