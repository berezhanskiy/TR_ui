package BrowserFactory;

import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class DefaultDriverAlivenessChecker implements DriverAlivenessChecker {
  @Override
  public boolean isAlive(WebDriver driver) {
    try {
      return driver.getWindowHandles().size() > 0;
    } catch (UnhandledAlertException ex) {
      return true;
    } catch (WebDriverException ex) {
      return false;
    }
  }
}
