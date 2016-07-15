package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import help.BrowserFactroryCastom;
import help.ExelDataConfig;
import pages.MainPage;
import pages.ReviewPage;

public class CheckTags extends BaseTest {
  
	private static ReviewPage reviewPage;
	private static String URL = "http://topreviews.best/main-review/best-scrapbooking-cutting-machines";
	
	private static List <WebElement> itemElements;
	private static List <WebElement> allProductLinks;
	private static List<String> productsName = new ArrayList<String>();
	private static String targetParametr = "_blank"; 
	private static String seoTag = "tcb119-20";
	
	
	
		
		
	
	@Test
    public void checks() throws FileNotFoundException, IOException, InterruptedException {
		StringBuffer errorBuffer = new StringBuffer();
	    reviewPage = new ReviewPage(driver);
	    beseTest1();
	   
	}
	
	
	
	//getin all links from the page and init String array by its
	public static  void getAllProductLink()throws FileNotFoundException, IOException{
		allProductLinks = driver.findElements(By.tagName("a"));
		System.out.println("total count of all link on the page is "+allProductLinks.size());
		}
	
	//method for sort WebElemts array 
	public static void sortLinks()	throws FileNotFoundException, IOException{
		String urlLink = "";
		boolean res = true;
		itemElements = new ArrayList();
		for(WebElement e : allProductLinks){
			urlLink = e.getAttribute("href");
			if(urlLink.contains("product"))
				itemElements.add(e);
			}
		System.out.println("total count of sorted link on the page is "+itemElements.size());
	}
	
	
	
	//method for check that all links contains taget_blank param 
	public static void chackTargerBlank()throws FileNotFoundException, IOException{
		String param = "";
		boolean result = false;
		for(int i = 0; i < itemElements.size(); i++){
			param = itemElements.get(i).getAttribute("target");
			if(param.contains("_blank"))
				result = true;
			else
				System.out.println(i+"th link link doesn't contain targer blank param");
		}
		System.out.println("links contain target blank param"+result);
	}
	
	//method for click by each link element and verify that amazon url contain right seo tag with "value" and
	//url doesn't contain UNDEFINED param
	//also method verify product names by compare predefined names with names in opened amazon product page
	public static void checkAmazonUrl() throws InterruptedException{
		boolean resultUndefined = false;
		String amazonUrl = "";
		String parrentHandle = driver.getWindowHandle();
		System.out.println("parren handle "+parrentHandle);
		System.out.println("sile of itemElemtnst " +itemElements.size());
		
		List <String> amazonHandles = new ArrayList<String>();
		
		for(WebElement e : itemElements){
			e.click();
		}
		amazonHandles.addAll(driver.getWindowHandles());
		System.out.println("size of windows handles "+amazonHandles.size());
		for(int i = 1; i < amazonHandles.size(); i++){
			driver.switchTo().window(amazonHandles.get(i));
			amazonUrl = driver.getCurrentUrl();
			if(amazonUrl.contains("UNDEFINED")){
				resultUndefined = true;
				System.out.println(i + "url contain UNDEFINED param");
			}	
		}
		System.out.println("does URL contain UNDEFINED? - "+resultUndefined);
	}
	
	
	public static void printMass(List mass){
			for(int i = 0; i < mass.size(); i++){
				System.out.println(mass.get(i));
			}
		}
		
	//method for print mass
	public static void printMassWeb(List<WebElement> mass){
		for(int i = 0; i < mass.size(); i++){
			System.out.println(mass.get(i).getAttribute("href"));
		}
	}
	
	//base method for test
	public static void beseTest1() throws FileNotFoundException, IOException, InterruptedException{
		driver.get(URL);
		getAllProductLink();
		sortLinks();
		//printMassWeb(itemElements);
		//chackTargerBlank();
		checkAmazonUrl();
	}
				
}
	
	
	
	
	
	

