package org.kira.automation.drivers.remote;

import static org.kira.automation.constants.FrameworkConstants.HEADLESS;
import static org.kira.automation.drivers.web.FirefoxOptionsDecorator.FIREFOX_HEADLESS_DECORATOR;

import java.util.Map;
import java.util.Optional;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

public class GridFirefoxBrowserOptions implements IGridBrowserOptions {

  @Override
  public MutableCapabilities getGridBrowserOptions(Map<String, String> browserOptionsMap) {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    var isHeadless = Optional.of(Boolean.parseBoolean(System.getProperty(HEADLESS))).orElse(true);
    FIREFOX_HEADLESS_DECORATOR.accept(isHeadless, firefoxOptions);
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
