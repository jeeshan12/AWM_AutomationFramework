package org.kira.automation.browsers;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.kira.automation.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteDriverServiceImpl implements BrowserDriverService{
  @Override
   public WebDriver getWebDriver(Configuration configuration) {
    try {

      WebDriver driver = new RemoteWebDriver((new URI("")).toURL(), null);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    return null;
  }
}
