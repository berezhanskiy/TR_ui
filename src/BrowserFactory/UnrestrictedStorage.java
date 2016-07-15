package BrowserFactory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class UnrestrictedStorage extends WebDriverFactoryInternal {

  private List<WebDriver> drivers = new ArrayList<WebDriver>();

  @Override
  public WebDriver getDriver(String hub, Capabilities capabilities) {
    WebDriver driver = newDriver(hub, capabilities);
    drivers.add(driver);
    return driver;
  }

  @Override
  public void dismissDriver(WebDriver driver) {
    if (! drivers.contains(driver)) {
      throw new Error("The driver is not owned by the factory: " + driver);
    }
    driver.quit();
    drivers.remove(driver);
  }

  @Override
  public void dismissAll() {
    for (WebDriver driver : new ArrayList<WebDriver>(drivers)) {
      driver.quit();
      drivers.remove(driver);
    }
  }

  @Override
  public boolean isEmpty() {
    return drivers.isEmpty();
  }

}
