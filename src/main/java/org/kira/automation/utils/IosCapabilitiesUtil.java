package org.kira.automation.utils;

import static org.kira.automation.drivers.WebDriverService.getCapabilityWithAppiumPrefix;

import io.appium.java_client.remote.AutomationName;
import org.apache.commons.lang3.SystemUtils;
import org.kira.automation.configuration.mobile.IosConfiguration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;

public class IosCapabilitiesUtil {

  private IosCapabilitiesUtil() {
  }

  public static void configureCapabilities(
      IosConfiguration iosConfiguration, MutableCapabilities capabilities) {
    capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.IOS);
    String appName = iosConfiguration.getApp();
    capabilities.setCapability(getCapabilityWithAppiumPrefix("app"),
        appName.startsWith("http") ? appName
            : String.format("%s/%s", SystemUtils.getUserDir(), appName));
    capabilities.setCapability(getCapabilityWithAppiumPrefix("automationName"),
        AutomationName.IOS_XCUI_TEST);
    capabilities.setCapability(getCapabilityWithAppiumPrefix("udid"), iosConfiguration.getUdid());
    capabilities.setCapability(getCapabilityWithAppiumPrefix("platformVersion"),
        iosConfiguration.getPlatformVersion());
    capabilities.setCapability(getCapabilityWithAppiumPrefix("deviceName"),
        iosConfiguration.getDeviceName());
  }
}
