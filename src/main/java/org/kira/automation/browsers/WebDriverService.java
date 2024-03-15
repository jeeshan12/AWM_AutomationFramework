package org.kira.automation.browsers;

import io.appium.java_client.internal.CapabilityHelpers;
import java.util.List;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.mobile.MobileConfiguration;
import org.kira.automation.utils.ConverterUtil;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public interface WebDriverService {

  WebDriver getWebDriver(final Configuration configuration);

  default String getCapabilityWithAppiumPrefix(String capabilityName) {
    return String.format("%s%s", CapabilityHelpers.APPIUM_PREFIX, capabilityName);
  }

  default void addAdditionalCapabilities(List<String> additionalCapabilities, MutableCapabilities capabilities) {
    for (String capability : additionalCapabilities) {
      String[] caps = capability.split("=");
      String key = caps[0];
      String value = caps[1];
      if (ConverterUtil.isBoolean(value)) {
        capabilities.setCapability(key, Boolean.parseBoolean(value));
      } else if (ConverterUtil.isNumeric(caps[1])) {
        capabilities.setCapability(key, Integer.parseInt(value));
      } else {
        capabilities.setCapability(key, value);
      }
    }
  }

  default void addGlobalCapabilitiesForAppium(MobileConfiguration mobileConfiguration, MutableCapabilities capabilities) {
    capabilities.setCapability("noReset", mobileConfiguration.isNoReset());
    capabilities.setCapability("fullReset", mobileConfiguration.isFullReset());
    capabilities.setCapability("eventTimings", mobileConfiguration.isEventTimings());
    capabilities.setCapability("fullReset", mobileConfiguration.isPrintPageSourceOnFindFailure());
    capabilities.setCapability("newCommandTimeout", mobileConfiguration.getNewCommandTimeout());

  }
}
