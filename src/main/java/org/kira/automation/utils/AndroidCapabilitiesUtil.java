package org.kira.automation.utils;

import static org.kira.automation.drivers.WebDriverService.getCapabilityWithAppiumPrefix;

import io.appium.java_client.remote.AutomationName;
import org.apache.commons.lang3.SystemUtils;
import org.kira.automation.configuration.mobile.AndroidConfiguration;
import org.kira.automation.configuration.mobile.DeviceLockConfiguration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;

public class AndroidCapabilitiesUtil {

  private AndroidCapabilitiesUtil(){}

  public static void configureCapabilities(
      AndroidConfiguration androidConfiguration, MutableCapabilities capabilities) {
    capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
    capabilities.setCapability(getCapabilityWithAppiumPrefix("appPackage"), androidConfiguration.getAppPackage());
    capabilities.setCapability(getCapabilityWithAppiumPrefix("appActivity"), androidConfiguration.getAppActivity());
    String appName = androidConfiguration.getApp();
    capabilities.setCapability(getCapabilityWithAppiumPrefix("app"), appName.startsWith("http") ? appName
        : String.format("%s/%s", SystemUtils.getUserDir(), appName));
    capabilities.setCapability(getCapabilityWithAppiumPrefix("automationName"), AutomationName.ANDROID_UIAUTOMATOR2);
    capabilities.setCapability(getCapabilityWithAppiumPrefix("udid"), androidConfiguration.getUdid());
    capabilities.setCapability(getCapabilityWithAppiumPrefix("platformVersion"), androidConfiguration.getPlatformVersion());
    capabilities.setCapability(getCapabilityWithAppiumPrefix("deviceName"), androidConfiguration.getDeviceName());

  }
  public static void addDeviceLockCapabilities(AndroidConfiguration androidConfiguration, MutableCapabilities capabilities) {
    DeviceLockConfiguration deviceLockConfiguration = androidConfiguration.getDeviceLockConfiguration();
    capabilities.setCapability("skipUnlock", deviceLockConfiguration.isDeviceUnlock());
    capabilities.setCapability("unlockType", deviceLockConfiguration.getUnlockType());
    capabilities.setCapability("unlockKey", deviceLockConfiguration.getUnlockKey());
    capabilities.setCapability("unlockStrategy", deviceLockConfiguration.getUnlockStrategy());
    capabilities.setCapability("unlockSuccessTimeout", deviceLockConfiguration.getUnlockSuccessTimeout());

  }
}
