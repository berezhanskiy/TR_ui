package BrowserFactory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface RemoteDriverProvider {

  /**
   * Creates a new driver with the desired capabilities, or returns null if the
   * capabilities does not match the provider's ability to create drivers.
   */
  WebDriver createDriver(String hub, Capabilities capabilities);

  static class Default implements RemoteDriverProvider {
    @Override
    public WebDriver createDriver(String hub, Capabilities capabilities) {
      try {
        return new RemoteWebDriver(new URL(hub), capabilities);
      } catch (MalformedURLException e) {
        Logger.getLogger(RemoteDriverProvider.class.getName())
            .log(Level.INFO, "Could not connect to WebDriver hub", e);
        return null;
      }
    }
  }
}
