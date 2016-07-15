package tests;

import org.testng.annotations.Test;

public class CheckPageSourseCod extends BaseTest {
  
	@Test
	public void f() {
		driver.get("http://automated-testing.info/tools");
		System.out.println(driver.getTitle());
		
	}

}
