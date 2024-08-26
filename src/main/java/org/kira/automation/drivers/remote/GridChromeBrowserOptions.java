package org.kira.automation.drivers.remote;

import static org.kira.automation.constants.FrameworkConstants.HEADLESS;
import static org.kira.automation.drivers.web.ChromeOptionsDecorator.CHROME_HEADLESS_DECORATOR;

import java.util.Map;
import java.util.Optional;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class GridChromeBrowserOptions implements IGridBrowserOptions {

  @Override
  public MutableCapabilities getGridBrowserOptions(Map<String, String> browserOptionsMap) {
    ChromeOptions chromeOptions = new ChromeOptions();
    var isHeadless = Optional.of(Boolean.parseBoolean(System.getProperty(HEADLESS))).orElse(true);
    CHROME_HEADLESS_DECORATOR.accept(isHeadless, chromeOptions);

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
