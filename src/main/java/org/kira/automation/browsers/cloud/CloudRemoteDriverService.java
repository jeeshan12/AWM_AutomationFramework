package org.kira.automation.browsers.cloud;

import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.cloud.CloudConfiguration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.Optional;

public interface CloudRemoteDriverService {

  WebDriver getWebDriver(final Configuration configuration, Optional<Map<String, String>> capabilityMapOptional);

  MutableCapabilities getPlatformSpecificCapabilities(CloudConfiguration cloudConfiguration, Optional<Map<String, String>> capabilityMapOptional);


}
