package org.kira.automation.drivers.remote;

import java.util.Map;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

public class GridFirefoxBrowserOptions implements IGridBrowserOptions {

  @Override
  public MutableCapabilities getGridBrowserOptions(Map<String, String> browserOptionsMap) {
    MutableCapabilities firefoxOptions = new FirefoxOptions();

    firefoxOptions.setCapability(
      CapabilityType.BROWSER_VERSION,
      browserOptionsMap.get(CapabilityType.BROWSER_VERSION)
    );

    firefoxOptions.setCapability(
      CapabilityType.PLATFORM_NAME,
      browserOptionsMap.get(CapabilityType.PLATFORM_NAME)
    );

    firefoxOptions.setCapability(
      CapabilityType.BROWSER_NAME,
      browserOptionsMap.get(CapabilityType.BROWSER_NAME)
    );

    return firefoxOptions;
  }
}
