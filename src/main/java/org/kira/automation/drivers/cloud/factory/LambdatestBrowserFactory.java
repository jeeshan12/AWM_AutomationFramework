package org.kira.automation.drivers.cloud.factory;

import static org.kira.automation.constants.FrameworkConstants.CHROME;
import static org.kira.automation.constants.FrameworkConstants.EDGE;
import static org.kira.automation.constants.FrameworkConstants.FIREFOX;
import static org.kira.automation.constants.FrameworkConstants.IE;
import static org.kira.automation.constants.FrameworkConstants.SAFARI;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariOptions;

public class LambdatestBrowserFactory {

  private static final Supplier<AbstractDriverOptions<?>> CHROME_OPTIONS_SUPPLIER = ChromeOptions::new;
  private static final Supplier<AbstractDriverOptions<?>> FIREFOX_OPTIONS_SUPPLIER = FirefoxOptions::new;
  private static final Supplier<AbstractDriverOptions<?>> EDGE_OPTIONS_SUPPLIER = EdgeOptions::new;
  private static final Supplier<AbstractDriverOptions<?>> SAFARI_OPTIONS_SUPPLIER = SafariOptions::new;
  private static final Supplier<AbstractDriverOptions<?>> IE_OPTIONS_SUPPLIER = InternetExplorerOptions::new;
  private static final Map<String, Supplier<AbstractDriverOptions<?>>> BROWSER_OPTIONS_MAP = new HashMap<>();

  static {
    BROWSER_OPTIONS_MAP.put(CHROME, CHROME_OPTIONS_SUPPLIER);
    BROWSER_OPTIONS_MAP.put(FIREFOX, FIREFOX_OPTIONS_SUPPLIER);
    BROWSER_OPTIONS_MAP.put(EDGE, EDGE_OPTIONS_SUPPLIER);
    BROWSER_OPTIONS_MAP.put(SAFARI, SAFARI_OPTIONS_SUPPLIER);
    BROWSER_OPTIONS_MAP.put(IE, IE_OPTIONS_SUPPLIER);
  }

  private LambdatestBrowserFactory() {
  }

  @SuppressWarnings("unchecked")
  public static <T extends AbstractDriverOptions<T>> T getBrowserOptions(String browserName) {
    return (T) BROWSER_OPTIONS_MAP.get(browserName).get();
  }

}
