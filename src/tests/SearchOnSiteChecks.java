package tests;


import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import help.BrowserFactroryCastom;
import pages.MainPage;


public class SearchOnSiteChecks extends BaseTest {
	
	private static String URL = "http://topreviews.best/";
	private MainPage mainPage;
	
	@Test
	public void testSearchOnSite() {
		mainPage = new MainPage(driver);
		driver.get(URL);
		searchCheck("jekos-kokos");
		MainPage.checkCountry();
		searchCheck("best wirless");
		MainPage.checkCountry();
		searchCheck("");
		MainPage.checkCountry();
		searchCheck("^&%$^");
		MainPage.checkCountry();
		searchButtonChecks("best");
	}


	public void searchCheck(String request){
		MainPage.searchOnSite(request);
		if(driver.findElement(By.xpath("/html/body/div[3]/div/div[1]")).getText().contains("Search Results")){
			System.out.println("search title is ok");
		}
		else{
			System.out.println(driver.findElement(By.xpath("/html/body/div[3]/div/div[1]")).getText());
			System.out.println("uppss");
		}
		if(driver.getCurrentUrl().contains("http://topreviews.best/search?search=")){
			System.out.println("search url is ok");
		}
		else{
			System.out.println("uppss");
		}
		
	}	
	
	public void searchButtonChecks(String request){
		MainPage.searchViaButton(request);
		if(driver.findElement(By.xpath("/html/body/div[3]/div/div[1]")).getText().contains("Search Results")){
			System.out.println("search title is ok");
		}
		else{
			System.out.println(driver.findElement(By.xpath("/html/body/div[3]/div/div[1]")).getText());
			System.out.println("uppss");
		}
		if(driver.getCurrentUrl().contains("http://topreviews.best/search?search=")){
			System.out.println("search url is ok");
		}
		else{
			System.out.println("uppss");
		}
		
	}
	
	
	
}
