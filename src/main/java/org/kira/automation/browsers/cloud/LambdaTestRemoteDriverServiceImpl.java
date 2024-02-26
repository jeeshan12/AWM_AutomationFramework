package org.kira.automation.browsers.cloud;

import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.cloud.CloudConfiguration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.Optional;

public class LambdaTestRemoteDriverServiceImpl implements CloudRemoteDriverService {
  @Override public WebDriver getWebDriver(Configuration configuration, Optional<Map<String, String>> capabilityMapOptional) {

    return null;
  }

  @Override
  public MutableCapabilities getPlatformSpecificCapabilities(CloudConfiguration cloudConfiguration, Optional<Map<String, String>> capabilityMapOptional) {
    return null;
  }
}
