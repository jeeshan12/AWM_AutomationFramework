package org.kira.automation.drivers.cloud;

import java.util.Map;
import java.util.Optional;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.cloud.CloudConfiguration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public interface CloudRemoteDriverService {
  WebDriver getWebDriver(Configuration configuration, Map<String, String> capabilityMap);

  MutableCapabilities getPlatformSpecificCapabilities(
    CloudConfiguration cloudConfiguration,
    Map<String, String> capabilityMap
  );

  public static Optional<Map<String, String>> toOptionalMap(Map<String, String> capabilityMap) {
    return Optional.ofNullable(capabilityMap).filter(m -> !m.isEmpty());
  }
}
