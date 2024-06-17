package org.kira.automation.runner;

import java.lang.reflect.Method;
import java.util.Map;
import org.kira.automation.annotations.Grid;
import org.openqa.selenium.remote.CapabilityType;

public class GridCapabilityBuilder implements CapabilityBuilder {

  @Override
  public Map<String, String> buildCapabilities(Method method) {
    Grid gridAnnotation = method.getAnnotation(Grid.class);
    return Map.of(
      CapabilityType.BROWSER_NAME,
      gridAnnotation.browserName(),
      CapabilityType.BROWSER_VERSION,
      gridAnnotation.browserVersion(),
      CapabilityType.PLATFORM_NAME,
      gridAnnotation.platformName()
    );
  }
}
