package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import help.BrowserFactroryCastom;
import help.ExelDataConfig;
import pages.MainPage;



public class SiteInfoChecks extends BaseTest {
	
	private static String URL = "http://topreviews.best/";
	public static ExelDataConfig exfile;
	private static String exPath = "D:\\work\\eclipse\\eclipse-workspace(EE)\\TRlocators.xlsx";
	public static List<String> siteInfoPath = new ArrayList<String>();
	private MainPage mainPage;

		@Test
		public void testSiteInfo() throws FileNotFoundException, IOException {
			mainPage = new MainPage(driver);
			driver.get(URL);
			isSiteInfoElementsPresent();
			isSiteInfoElementsWork(siteInfoPath);
		}

		
		//method for init xpathes to site info elements
		public static void initSiteInfoPath() throws FileNotFoundException, IOException{
			exfile = new ExelDataConfig(exPath);
			siteInfoPath.addAll(exfile.getData(1,siteInfoPath));
		}
		
		//method for check that Site Info elements are present
		public static void isSiteInfoElementsPresent() throws FileNotFoundException, IOException{
			initSiteInfoPath();
			/*for(int i = 5; i<=10; i++){
				System.out.println(siteInfoPath.get(i));
			if(BrowserFactrory.isElementPresentByXpath(siteInfoPath.get(i))){
					System.out.println("element is present "+i);
				}
				else{
					System.out.println("element is empty "+i);
				}
			}*/
		}

		//method for check that links and titles of site info elements are correct
		public static void isSiteInfoElementsWork(List<String> str){
			String titleInfo;
			for(int i = 7; i<str.size(); i++){
				titleInfo = driver.findElement(By.xpath(str.get(i))).getText();
				driver.findElement(By.xpath(siteInfoPath.get(i))).click();
				System.out.println(driver.getTitle());
					if(driver.getTitle().substring(18, driver.getTitle().length()).equalsIgnoreCase(titleInfo)){
						System.out.println("all ok");
					}
					else{
						System.out.println("titles is wrong");
						System.out.println(driver.getTitle());
					}
				MainPage.checkCountry();
				driver.navigate().back();
			}
		}
		
		

	}
