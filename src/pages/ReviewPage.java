package pages;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import help.BrowserFactroryCastom;
import help.ExelDataConfig;



public class ReviewPage {

	private static WebDriver driver;	
	private static String screenShotPath = "D:\\review.png";
	private static ExelDataConfig exlFile;
	private static List<String> preivewElementsPathes;
	
	@FindBy (xpath = "/html/body/div[3]/div/div/h1") static WebElement reviewName;
	@FindBy (xpath ="/html/body/div[3]/div/div/div[1]") static WebElement revieIntro;
	@FindBy (className = "item-label label-gold") static WebElement bestPicksFlag;
	@FindBy (className = "item-label label-silver") static WebElement resonableFlag;
	@FindBy (xpath ="/html/body/div[3]/div/div/div[2]/div[1]/div[1]/div[1]") static WebElement tableCaption;
	@FindBy (xpath ="/html/body/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div") static WebElement firstItem;
	
	
	//constructor for create POM review page object
	public ReviewPage (WebDriver driver) throws FileNotFoundException, IOException{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		preivewElementsPathes = new ArrayList();
	
	}

	
	public static String getReviewName(String path){
		String reviewName;
		reviewName = driver.findElement(By.xpath(path)).getText();
		return reviewName;
	}
	
	public static String getReviewIntro(){
		String name;
		name = revieIntro.getText();
		return name;
	}
	
	//method for getting text from best pick flag
	public static String getBestPickFlagText(){
		String bestPickFlagText;
		bestPickFlagText = bestPicksFlag.getText();
		return bestPickFlagText;
	}
	
	//method for getting text from reasonable price flag
		public static String getReasonablePriceFlagText(){
			String reasonablePriceFlagText;
			reasonablePriceFlagText = resonableFlag.getText();
			return reasonablePriceFlagText;
		}
		
		public static WebElement getItem(String path){
			WebElement reviewProduct;
			reviewProduct = driver.findElement(By.xpath(path));
			return reviewProduct;
		}
	
	
	//method for get skreanshot from page and save it at disc
		public static void takeSkeanshot()throws InterruptedException, IOException{
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File screenshot = ((TakesScreenshot)augmentedDriver).
	        getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenShotPath));
		}

		
		
		//method for check that source cod contain GMT
		public void checkGTM(){
			String sour = driver.getPageSource();
			if(sour.contains("GTM-K99CZD")){
				System.out.println("GTM ok");
			}
			else{
				System.out.println("fuck");
			}
		}

	
}
