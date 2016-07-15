package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import org.junit.Before;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import help.ExelDataConfig;
import help.BrowserFactroryCastom;
import pages.MainPage;

public class BestPickChecks extends BaseTest{
	
	public static ExelDataConfig exlFile;
	public static String exlPath = "D:\\work\\eclipse\\eclipse-workspace(EE)\\TRlocators.xlsx";
	private static String URL = "http://topreviews.best/";
	private static MainPage mainPage;

	@Test
	public  void testBestPick() throws IOException, InterruptedException{
		mainPage = new MainPage(driver);
		driver.get(URL);
		checkBestPicks(); 
		moveOnBestPicks();
	}
	
	//method for extract all catalog names from BestPick list and compare it with list in xls file
	public static  void checkBestPicks() throws IOException, InterruptedException
	{
		exlFile = new ExelDataConfig(exlPath);
		List <WebElement> bestPicksElements = MainPage.getBestPickItem();
		for(int i = 0; i < bestPicksElements.size(); i++){
			if(bestPicksElements.get(i).getText()== exlFile.getOneValue(3, i, 0)){	
				System.out.println(bestPicksElements.get(i).getText());
				System.out.println("\n");
			}
			else {
				System.out.println(bestPicksElements.get(i).getText()+"doesn't match with file \n");
				mainPage.takeSkeanshot();
			}
		}
	}
	
	//method for click by click each other element
	public static void moveOnBestPicks() throws InterruptedException{
		String bestPickItemPath = "";
		for(int i = 1; i < 12; i++){
			MainPage.clickOnBestPick();
			bestPickItemPath =  String.format("/html/body/div[1]/div/div[2]/div[1]/div/a[%d]",i);
			WebElement bestPicksItem = driver.findElement(By.xpath(bestPickItemPath));
			bestPicksItem.click();
			String title = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]")).getText();
			System.out.println("yoy are - " + title);
	    }
	}

}
