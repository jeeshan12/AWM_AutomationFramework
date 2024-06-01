package org.kira.automation.framework;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import io.appium.java_client.remote.AutomationName;
import org.kira.automation.configuration.mobile.AndroidConfiguration;
import org.kira.automation.configuration.mobile.DeviceLockConfiguration;
import org.kira.automation.utils.AndroidCapabilitiesUtil;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AndroidCapabilitiesUtilTest {

  private AndroidConfiguration androidConfiguration;
  private MutableCapabilities capabilities;
  private DeviceLockConfiguration deviceLockConfiguration;

  @BeforeMethod
  public void setUp() {
    androidConfiguration = mock(AndroidConfiguration.class);
    deviceLockConfiguration = mock(DeviceLockConfiguration.class);
    capabilities = new DesiredCapabilities();
  }

  @Test
  public void testConfigureCapabilities() {
    when(androidConfiguration.getAppPackage()).thenReturn("com.example.app");
    when(androidConfiguration.getAppActivity()).thenReturn("com.example.app.MainActivity");
    when(androidConfiguration.getApp()).thenReturn("app.apk");
    when(androidConfiguration.getUdid()).thenReturn("123456");
    when(androidConfiguration.getPlatformVersion()).thenReturn("9.0");
    when(androidConfiguration.getDeviceName()).thenReturn("emulator-5554");

    AndroidCapabilitiesUtil.configureCapabilities(androidConfiguration, capabilities);

    assertEquals(capabilities.getCapability(CapabilityType.PLATFORM_NAME), Platform.ANDROID);
    assertEquals(capabilities.getCapability("appium:appPackage"), "com.example.app");
    assertEquals(capabilities.getCapability("appium:appActivity"), "com.example.app.MainActivity");
    assertEquals(
      capabilities.getCapability("appium:app"),
      System.getProperty("user.dir") + "/app.apk"
    );
    assertEquals(
      capabilities.getCapability("appium:automationName"),
      AutomationName.ANDROID_UIAUTOMATOR2
    );
    assertEquals(capabilities.getCapability("appium:udid"), "123456");
    assertEquals(capabilities.getCapability("appium:platformVersion"), "9.0");
    assertEquals(capabilities.getCapability("appium:deviceName"), "emulator-5554");
  }

  @Test
  public void testAddDeviceLockCapabilities() {
    when(androidConfiguration.getDeviceLockConfiguration()).thenReturn(deviceLockConfiguration);
    when(deviceLockConfiguration.isDeviceUnlock()).thenReturn(true);
    when(deviceLockConfiguration.getUnlockType()).thenReturn("pin");
    when(deviceLockConfiguration.getUnlockKey()).thenReturn("1234");
    when(deviceLockConfiguration.getUnlockStrategy()).thenReturn("strategy");
    when(deviceLockConfiguration.getUnlockSuccessTimeout()).thenReturn(10000);

    AndroidCapabilitiesUtil.addDeviceLockCapabilities(androidConfiguration, capabilities);

    assertEquals(capabilities.getCapability("skipUnlock"), true);
    assertEquals(capabilities.getCapability("unlockType"), "pin");
    assertEquals(capabilities.getCapability("unlockKey"), "1234");
    assertEquals(capabilities.getCapability("unlockStrategy"), "strategy");
    assertEquals(capabilities.getCapability("unlockSuccessTimeout"), 10000);
  }
}
