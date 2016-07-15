package BrowserFactory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.DefaultDriverFactory;
import org.openqa.selenium.remote.server.DefaultDriverProvider;
import org.openqa.selenium.remote.server.DriverFactory;
import org.openqa.selenium.remote.server.DriverProvider;

import java.util.LinkedList;
import java.util.ServiceLoader;

public abstract class WebDriverFactoryInternal {

  public abstract WebDriver getDriver(String hub, Capabilities capabilities);
  public abstract void dismissDriver(WebDriver driver);
  public abstract void dismissAll();
  public abstract boolean isEmpty();

  private String defaultHub = null;
  protected DriverAlivenessChecker alivenessChecker = new DefaultDriverAlivenessChecker();

  private DriverFactory factory = new DefaultDriverFactory();
  {
    factory.registerDriverProvider(
        new DefaultDriverProvider(DesiredCapabilities.chrome(),
            "org.openqa.selenium.chrome.ChromeDriver"));
    factory.registerDriverProvider(
        new DefaultDriverProvider(DesiredCapabilities.firefox(),
            "org.openqa.selenium.firefox.FirefoxDriver"));
    factory.registerDriverProvider(
        new DefaultDriverProvider(DesiredCapabilities.internetExplorer(),
            "org.openqa.selenium.ie.InternetExplorerDriver"));
    factory.registerDriverProvider(
        new DefaultDriverProvider(DesiredCapabilities.edge(),
            "org.openqa.selenium.edge.EdgeDriver"));
   /* factory.registerDriverProvider(
        new DefaultDriverProvider(DesiredCapabilities.opera(),
            "com.opera.core.systems.OperaDriver"));
    factory.registerDriverProvider(
        new DefaultDriverProvider(DesiredCapabilities.operaBlink(),
            "org.openqa.selenium.opera.OperaDriver"));*/
    factory.registerDriverProvider(
        new DefaultDriverProvider(DesiredCapabilities.safari(),
            "org.openqa.selenium.safari.SafariDriver"));
   /* factory.registerDriverProvider(
        new DefaultDriverProvider(DesiredCapabilities.phantomjs(),
            "org.openqa.selenium.phantomjs.PhantomJSDriver"));
    factory.registerDriverProvider(
        new DefaultDriverProvider(DesiredCapabilities.htmlUnit(),
            "org.openqa.selenium.htmlunit.HtmlUnitDriver"));*/
  }

  private LinkedList<RemoteDriverProvider> remoteDriverProviders
      = new LinkedList<RemoteDriverProvider>();
  {
    remoteDriverProviders.add(new RemoteDriverProvider.Default());
    for (RemoteDriverProvider provider : ServiceLoader.load(RemoteDriverProvider.class)) {
      remoteDriverProviders.add(provider);
    }
  }

  void addDriverProvider(DriverProvider provider) {
    factory.registerDriverProvider(provider);
  }

  void addRemoteDriverProvider(RemoteDriverProvider provider) {
    remoteDriverProviders.addFirst(provider);
  }

  public void setDefaultHub(String defaultHub) {
    this.defaultHub = defaultHub;
  }

  public WebDriver getDriver(Capabilities capabilities) {
    return getDriver(defaultHub, capabilities);
  }

  protected String createKey(Capabilities capabilities, String hub) {
    return capabilities.toString() + ":" + hub;
  }

  protected WebDriver newDriver(String hub, Capabilities capabilities) {
    return (hub == null)
        ? createLocalDriver(capabilities)
        : createRemoteDriver(hub, capabilities);
  }

  private WebDriver createRemoteDriver(String hub, Capabilities capabilities) {
    for (RemoteDriverProvider provider : remoteDriverProviders) {
      WebDriver driver = provider.createDriver(hub, capabilities);
      if (driver != null) {
        return driver;
      }
    }
    throw new Error("Can't find remote driver provider for capabilities " + capabilities);
  }

  private WebDriver createLocalDriver(Capabilities capabilities) {
    return factory.newInstance(capabilities);
  }

  public void setDriverAlivenessChecker(DriverAlivenessChecker alivenessChecker) {
    this.alivenessChecker = alivenessChecker;
  }
}