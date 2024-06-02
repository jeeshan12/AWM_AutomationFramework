package org.kira.automation.framework;

import static org.testng.Assert.assertEquals;

import org.apache.commons.lang3.SystemUtils;
import org.kira.automation.configuration.mobile.IosConfiguration;
import org.kira.automation.utils.IosCapabilitiesUtil;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.testng.annotations.Test;

public class IosCapabilitiesUtilTest {

  @Test
  public void testConfigureCapabilities() {
    // Arrange
    IosConfiguration iosConfiguration = new IosConfiguration();
    iosConfiguration.setApp("MyApp.app");
    iosConfiguration.setUdid("12345");
    iosConfiguration.setPlatformVersion("14.5");
    iosConfiguration.setDeviceName("iPhone 12");
    MutableCapabilities capabilities = new MutableCapabilities(); // Initialize MutableCapabilities object

    // Act
    IosCapabilitiesUtil.configureCapabilities(iosConfiguration, capabilities);

    // Assert
    assertEquals(capabilities.getCapability("platformName"), Platform.IOS);
    assertEquals(
      capabilities.getCapability("appium:app"),
      String.format("%s/%s", SystemUtils.getUserDir(), "MyApp.app")
    );
    assertEquals(capabilities.getCapability("appium:automationName"), "XCuiTest");
    assertEquals(capabilities.getCapability("appium:udid"), "12345");
    assertEquals(capabilities.getCapability("appium:platformVersion"), "14.5");
    assertEquals(capabilities.getCapability("appium:deviceName"), "iPhone 12");
  }
}
