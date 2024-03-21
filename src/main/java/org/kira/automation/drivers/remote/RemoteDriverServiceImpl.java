package org.kira.automation.drivers.remote;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.drivers.WebDriverService;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteDriverServiceImpl implements WebDriverService {

  @Override
  public WebDriver getWebDriver(Configuration configuration) {
    try {
      MutableCapabilities mutableCapabilities = new MutableCapabilities();
      return new RemoteWebDriver(
        (new URI(configuration.getWeb().getSeleniumGrid().getGridUrl())).toURL(),
        mutableCapabilities
      );
    } catch (URISyntaxException | MalformedURLException e) {
      throw new FrameworkGenericException(
        "Error occurred while creating remote WebDriver reference",
        e
      );
    }
  }
}
