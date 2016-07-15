package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import help.BrowserFactroryCastom;
import help.ExelDataConfig;
import pages.MainPage;

public class PreviewChecks extends BaseTest {
	
	public static String exlPath = "D:\\work\\eclipse\\eclipse-workspace(EE)\\TRlocators.xlsx";
	public static List<String> previewElementsPathes = new ArrayList<String>();
	public static List<String> previewTitleT = new ArrayList<String>();
	private static String URL = "http://topreviews.best/";
	private MainPage mainPage;
	
	@Test
	public void testPreviewElements() throws FileNotFoundException, IOException, InterruptedException {
			mainPage = new MainPage(driver);
			driver.get(URL);
			isPreviewElementsPresents();
			previewElementsWork(previewElementsPathes);
	}

	//method for intiPreviewPathes
	public static void initPrintPathes() throws FileNotFoundException, IOException{
		ExelDataConfig exlFile = new ExelDataConfig(exlPath);
		previewElementsPathes = exlFile.getMainPagePreviewPathes();
	}
	
	//method for check all preview into Main Page are present
	public static void isPreviewElementsPresents() throws InterruptedException, IOException{
		initPrintPathes();
		boolean elementsIsPresen = true;
		for(int i = 0; i < previewElementsPathes.size(); i++){
			elementsIsPresen = true;
		//	elementsIsPresen = driver.findElement(By.xpath(previewElementsPathes.get(i))).isDisplayed();
				if(elementsIsPresen){
					System.out.println("element of " + i + "container item is present");
					}
				else{
					System.out.println(i + "th element of the container item is ebsent");
				
				}
			}
		}
		
	//method for get preview title then move into review, compare titles and check country;
	public static void previewElementsWork(List <String> str){
		WebElement previewItem;
		String previewTitle = "";
		//Paths for preview title contains in 10-19 rows of exel file
		for(int j = 0, i = 9; i <= 17; i++, j++){
			previewTitle = driver.findElement(By.xpath(str.get(i))).getText().concat(" 2016");
			System.out.println(previewTitle);
			MainPage.clickOnPreview(str.get(j));
			if(driver.getTitle().substring(18, driver.getTitle().length()).equalsIgnoreCase(previewTitle)){
				System.out.println("all ok");
				}
			else{
				System.out.println("titles is wrong");
				}
			MainPage.checkCountry();
			driver.navigate().back();
		}
	}	
		
}

