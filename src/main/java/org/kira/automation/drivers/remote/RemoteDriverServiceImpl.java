package org.kira.automation.drivers.remote;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.cloud.CloudConfiguration;
import org.kira.automation.drivers.cloud.CloudRemoteDriverService;
import org.kira.automation.enums.Browsers;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteDriverServiceImpl implements CloudRemoteDriverService {

  @Override
  public WebDriver getWebDriver(Configuration configuration, Map<String, String> capabilityMap) {
    try {
      String gridUrl = String.format(
        "%s:%s/wd/hub",
        configuration.getWeb().getCloud().getProvider().getGridConfiguration().getGridUrl(),
        configuration.getWeb().getCloud().getProvider().getGridConfiguration().getGridPort()
      );

      return new RemoteWebDriver(
        (new URI(gridUrl)).toURL(),
        this.getPlatformSpecificCapabilities(configuration.getWeb().getCloud(), capabilityMap)
      );
    } catch (URISyntaxException | MalformedURLException e) {
      throw new FrameworkGenericException(
        "Error occurred while creating remote WebDriver reference",
        e
      );
    }
  }

  @Override
  public MutableCapabilities getPlatformSpecificCapabilities(
    CloudConfiguration cloudConfiguration,
    Map<String, String> capabilityMap
  ) {
    if (capabilityMap != null && !capabilityMap.isEmpty()) {
      IGridBrowserOptions gridBrowserOptions = GridBrowserOptionsFactory.getGridBrowserOptions(
        capabilityMap.get(CapabilityType.BROWSER_NAME)
      );
      return gridBrowserOptions.getGridBrowserOptions(capabilityMap);
    }
    return (
        cloudConfiguration.getProvider().getGridConfiguration().getBrowserName() ==
        Browsers.CHROME.getName()
      )
      ? new ChromeOptions()
      : new FirefoxOptions();
  }
}
