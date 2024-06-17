package org.kira.automation.drivers.remote;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.kira.automation.enums.Browsers;

public class GridBrowserOptionsFactory {

  private GridBrowserOptionsFactory() {}

  private static final Supplier<IGridBrowserOptions> GRID_CHROME_BROWSER_OPTIONS_SUPPLIER =
    GridChromeBrowserOptions::new;

  private static final Supplier<IGridBrowserOptions> GRID_FIREFOX_BROWSER_OPTIONS_SUPPLIER =
    GridChromeBrowserOptions::new;

  static final Map<String, Supplier<IGridBrowserOptions>> GRID_BROWSER_OPTIONS_MAP =
    new HashMap<>();

  static {
    GRID_BROWSER_OPTIONS_MAP.put(Browsers.CHROME.getName(), GRID_CHROME_BROWSER_OPTIONS_SUPPLIER);
    GRID_BROWSER_OPTIONS_MAP.put(Browsers.FIREFOX.getName(), GRID_FIREFOX_BROWSER_OPTIONS_SUPPLIER);
  }

  public static IGridBrowserOptions getGridBrowserOptions(String browserName) {
    return GRID_BROWSER_OPTIONS_MAP.get(browserName).get();
  }
}
