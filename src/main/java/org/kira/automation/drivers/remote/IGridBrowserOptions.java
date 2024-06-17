package org.kira.automation.drivers.remote;

import java.util.Map;
import org.openqa.selenium.MutableCapabilities;

public interface IGridBrowserOptions {
  MutableCapabilities getGridBrowserOptions(Map<String, String> browserOptionsMap);
}
