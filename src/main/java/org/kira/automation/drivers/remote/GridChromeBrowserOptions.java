package org.kira.automation.drivers.remote;

import java.util.Map;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class GridChromeBrowserOptions implements IGridBrowserOptions {

  @Override
  public MutableCapabilities getGridBrowserOptions(Map<String, String> browserOptionsMap) {
    ChromeOptions chromeOptions = new ChromeOptions();

    chromeOptions.setCapability(
      CapabilityType.BROWSER_VERSION,
      browserOptionsMap.get(CapabilityType.BROWSER_VERSION)
    );

    chromeOptions.setCapability(
      CapabilityType.PLATFORM_NAME,
      browserOptionsMap.get(CapabilityType.PLATFORM_NAME)
    );

    chromeOptions.setCapability(
      CapabilityType.BROWSER_NAME,
      browserOptionsMap.get(CapabilityType.BROWSER_NAME)
    );

    return chromeOptions;
  }
}
