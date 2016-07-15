package BrowserFactory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;

public class SingletonStorage extends WebDriverFactoryInternal {

  private String key;
  private WebDriver driver;

  @Override
  public WebDriver getDriver(String hub, Capabilities capabilities) {
    String newKey = createKey(capabilities, hub);
    if (driver == null) {
      createNewDriver(hub, capabilities);

    } else {
      if (!newKey.equals(key)) {
        // A different flavour of WebDriver is required
        dismissDriver();
        createNewDriver(hub, capabilities);

      } else {
        // Check the browser is alive
        if (! alivenessChecker.isAlive(driver)) {
          createNewDriver(hub, capabilities);
        }
      }
    }

    return driver;
  }

  @Override
  public void dismissDriver(WebDriver driver) {
    if (driver != this.driver) {
      throw new Error("The driver is not owned by the factory: " + driver);
    }
    dismissDriver();
  }

  @Override
  public void dismissAll() {
    dismissDriver();
  }

  @Override
  public boolean isEmpty() {
    return driver == null;
  }

  private void createNewDriver(String hub, Capabilities capabilities) {
    String newKey = createKey(capabilities, hub);
    driver = newDriver(hub, capabilities);
    key = newKey;
  }

  private void dismissDriver() {
    if (driver != null) {
      driver.quit();
      driver = null;
      key = null;
    }
  }
}