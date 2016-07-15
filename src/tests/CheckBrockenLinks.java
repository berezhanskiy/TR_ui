package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import help.BrowserFactroryCastom;
import pages.ReviewPage;


public class CheckBrockenLinks extends BaseTest {
	
	private ReviewPage reviewPage;
	private static String previewURL = "http://topreviews.best/main-review/best-scrapbooking-cutting-machines";
	private static List<WebElement> allLinks;
	
	
	
	@Test
	public void testBrockenLinks() throws FileNotFoundException, IOException {
		reviewPage = new ReviewPage(driver);
		checkLinks();
	}

	 public static void checkLinks(){
		allLinks = driver.findElements(By.tagName("a"));
		System.out.println("total count of all link on the page is "+allLinks.size()); 
		boolean isValid = false;
		  for (int i = 0; i < allLinks.size(); i++) {
		   String url = allLinks.get(i).getAttribute("href");

		   if (url != null) {
		    
		    //Call getResponseCode function for each URL to check response code.
		    isValid = getResponseCode(url);
		    
		    //Print message based on value of isValid which Is returned by getResponseCode function.
		    if (isValid) {
		     System.out.println("Valid Link:" + url);
		     System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
		     System.out.println();
		    } else {
		     System.out.println("Broken Link ------> " + url);
		     System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
		     System.out.println();
		    }
		   } else {    
		    //If <a> tag do not contain href attribute and value then print this message
		    System.out.println("String null");
		    System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
		    System.out.println();
		    continue;
		   }
		  }
		  
	}
		
	
	
	
	//Function to get response code of link URL.
	 //Link URL Is valid If found response code = 200.
	 //Link URL Is Invalid If found response code = 404 or 505.
	 public static boolean getResponseCode(String chkurl) {
	  boolean validResponse = false;
	  try {   
	   //Get response code of URL
	   HttpResponse urlresp = new DefaultHttpClient().execute(new HttpGet(chkurl));
	   int resp_Code = urlresp.getStatusLine().getStatusCode();
	   System.out.println("Response Code Is : "+resp_Code);
	   if ((resp_Code == 404) || (resp_Code == 505)) {
	    validResponse = false;
	   } else {
	    validResponse = true;
	   }
	  } catch (Exception e) {

	  }
	  return validResponse;
	 }
	 
	
}
	
	


