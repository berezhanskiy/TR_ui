package tests;

import org.apache.commons.lang3.time.StopWatch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import help.BrowserFactroryCastom;
import pages.MainPage;



public class PageLoadChecks extends BaseTest {

	StopWatch pageLoad = new StopWatch();
	static String pingdomUrl = "https://tools.pingdom.com/";
	static String URL = "http://topreviews.best/";
	private MainPage mainPage;
	
	
	@Test
	public  void  testPageLoad() throws InterruptedException{
		mainPage = new MainPage(driver);
		testPageLoad(URL, "Pingdom");
	}
	
	
	//method for pick one of three methods for check page load time
	public static void testPageLoad(String URL, String method) throws InterruptedException{
		switch(method){
			case "SW":
				{
					PageLoadWithSW(URL);
					break;
				}
			case "Sys":	
				{
					PageLoadWithSys(URL);
					break;
				}
			case "Pingdom":
			{
				PageLoadWith3rd(URL);
				break;
			}
			default:
			{
				System.out.println("page load default case, oops");
				break;
			}
		}
	}
	
	//method for check page load via StopWatch class
	public static void PageLoadWithSW(String url){
		StopWatch pageLoad = new StopWatch();
		pageLoad.start();
		driver.get(url);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/footer/div[2]/div")));
		pageLoad.stop();
		long pageLoadTime_ms = pageLoad.getTime();
	    long pageLoadTime_Seconds = pageLoadTime_ms / 1000;
	    System.out.println("Total Page Load Time: " + pageLoadTime_ms + " milliseconds");
	    System.out.println("Total Page Load Time: " + pageLoadTime_Seconds + " seconds");
	}

	//method for check page load time via java system time
	public static void PageLoadWithSys(String url){
		long start = System.currentTimeMillis();
		driver.get(url);
		driver.findElement(By.xpath("/html/body/footer/div[2]/div"));
		long total = System.currentTimeMillis()- start;
		System.out.println(total);
	}
	
	//method for check page load time via pingdom service
	public static void PageLoadWith3rd( String urlTR) throws InterruptedException{
		driver.get("https://tools.pingdom.com/");
		WebElement fild  = driver.findElement(By.id("urlinput"));
		fild.sendKeys(urlTR);
		fild.submit();
		Thread.sleep(10000);
		System.out.println(driver.findElement(By.cssSelector("#content > div.section.section-test-data.rbc-summary > div.rbc-summary-group > div.rbc-summary-item.rbc-summary-loadtime > div.rbc-summary-info-value")).getText());
	}
	
}


	

