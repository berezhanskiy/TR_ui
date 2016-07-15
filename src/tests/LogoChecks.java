package tests;

import java.io.IOException;


import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import help.BrowserFactroryCastom;
import pages.MainPage;

import static org.assertj.core.api.Assertions.*;

public class LogoChecks extends BaseTest {
	
	private static MainPage mainPage;
	private static String URL = "http://topreviews.best/";
	
	@Test
	public void testLogo() throws InterruptedException, IOException {
		mainPage = new MainPage(driver);
		driver.get(URL);
	    logoTextCheckWithAssertJ();
		}
	
	//method for compare logo text in site with constant	
	public static boolean logoTextCheck() throws InterruptedException, IOException{
		boolean logoTextCorrect=true;
		String logoText = MainPage.getLogoText();
		if(logoText.contains("TopReview")){
			logoTextCorrect = true;
			System.out.println("checks with logo text is ok");
			}
		else{
			logoTextCorrect = false;
			System.out.println("checks with logo text is not ok");
			mainPage.takeSkeanshot();
			}
		return logoTextCorrect;
		}

	//method for compare logo text in site with constant	
	public static boolean logoTextCheckWithAssertJ() throws InterruptedException, IOException{
			boolean logoTextCorrect=true;
			String logoText = MainPage.getLogoText();
			assertThat(logoText.contains("TopReview"));
			return logoTextCorrect;
		}
}
			
	
	
	
