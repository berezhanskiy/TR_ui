package BrowserFactory;

import org.openqa.selenium.WebDriver;

public interface DriverAlivenessChecker {
  boolean isAlive(WebDriver driver);
}
