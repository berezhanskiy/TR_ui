package pages;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import help.BrowserFactroryCastom;


public class MainPage {

	private static String usURL = "http://topreviews.best/";	
	private static String screenShotPath = "D:\\work\\screenshots\\main page\\";
	private static long screenShotTime;
	private static String sreenShotName = "mainpage.png";
	
	private static WebDriver driver;

	//block with defined page elements via FindBy anatotion

	@FindBy(xpath = "/html/body/div[1]/div/a") 
	static WebElement logo;

	@FindBy(xpath = "/html/body/div[1]/div/div[1]/form/input")
	static WebElement searchField;

	@FindBy (xpath = "/html/body/div[1]/div/div[1]/form/button") 
	static WebElement searchButton;

	@FindBy (xpath = "/html/body/div[1]/div/div[2]/div[1]/a")
	static WebElement bestPickButton;

	@FindBy (xpath = "/html/body/div[3]/div/div[1]")
	static WebElement previewFirst;

	@FindBy (xpath = "/html/body/footer/div[1]/ul/li[2]/ul/li[3]/a")
	static WebElement contactUs;

	@FindBy (xpath = "/html/body/footer/div[1]/ul/li[3]/ul/li[1]")
	static WebElement legalInformation;

	@FindBy (xpath = "/html/body/footer/div[1]/ul/li[3]/ul/li[3]/a")
	static WebElement termsEndConditions;

	@FindBy (xpath = "/html/body/footer/div[1]/ul/li[3]/ul/li[2]/a")
	static WebElement privacy;

	//constructor for create POM Main Page object
	public MainPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//method for extract and save text in site logo
	public static String getLogoText(){
		String logoText = logo.getText();
		return logoText;
	}

	//method for input user request
	public static void compileSeachField(String request){
		searchField.click();
		searchField.sendKeys(request);
	}

	//method for compile and submit search request on site
	public static void searchOnSite(String request){
		compileSeachField(request);
		searchField.submit();
	}

	//method for search preview via button 'lupa'
	public static void searchViaButton(String request){
		compileSeachField(request);
		searchButton.click();
	}

	//method for open best picks sub menu
	public static void clickOnBestPick(){
		bestPickButton.click();
	}

	//method for open and save all names of the BestPicks items
	public static List<WebElement> getBestPickItem(){
		List <WebElement> bestPickItems;
		bestPickButton.click();
		bestPickItems = bestPickButton.findElements(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div"));
		return bestPickItems;
	}

	//method for click and go on preview
	public static void clickOnPreview(String path){
		driver.findElement(By.xpath(path)).click();
	}

	//method for extract preview img url
	public static String getPreviewImg(String path){
		WebElement previewI = driver.findElement(By.xpath(path));
		String previewImg = previewI.getAttribute("style");
		return previewImg;
	}

	//method for extract and save preview name
	public static String getPreviewTitle(String path){
		String previeTitleText =  driver.findElement(By.xpath(path)).getText();
		return previeTitleText;
	}

	//method for recognize in witch country you are 
	public static String checkCountry(){
		String country=""; 
			if(driver.getCurrentUrl().substring(7, 9)== "uk."){
				country = "UK";
				System.out.println("you are in the "+country);
			}
			else{
				country = "US";
				System.out.println("you are in the "+country);
			}
			return country;
	}
	
	//method for generate screen name for saving img file, used system current time
	public static String generateScreenShotName(){
		Long screenShotTime = System.currentTimeMillis();
		String screenTime = Long.toString(screenShotTime);
		screenTime = screenTime + sreenShotName;
		screenShotPath = screenShotPath + screenTime;
		return screenShotPath;
	}
	
	//method for get skreanshot from page and save it at disc
	public static void takeSkeanshot()throws InterruptedException, IOException{
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
		String screenTime;
		screenShotTime = System.currentTimeMillis();
		screenTime = Long.toString(screenShotTime);
		screenTime.concat(sreenShotName);
		screenShotPath.concat(sreenShotName);
		FileUtils.copyFile(screenshot, new File(generateScreenShotName()));
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
