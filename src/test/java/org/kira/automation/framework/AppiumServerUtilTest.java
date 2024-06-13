package org.kira.automation.framework;

import static org.kira.automation.constants.FrameworkConstants.APPIUM_PORT;
import static org.kira.automation.constants.FrameworkConstants.APPIUM_SERVER;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.kira.automation.configuration.mobile.MobileConfiguration;
import org.kira.automation.utils.AppiumServerUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AppiumServerUtilTest {

  private static final String DEFAULT_SERVER_URL = "http://localhost";
  private static final int DEFAULT_PORT = 4723;
  private static final String CUSTOM_SERVER_URL = "http://11.22.33";
  private static final String CUSTOM_PORT = "8080";

  private MobileConfiguration mobileConfiguration;

  @BeforeMethod
  public void setUp() {
    this.mobileConfiguration = mock(MobileConfiguration.class);

    when(this.mobileConfiguration.getServerUrl()).thenReturn(DEFAULT_SERVER_URL);
    when(this.mobileConfiguration.getPort()).thenReturn(DEFAULT_PORT);
  }

  @AfterMethod
  public void tearDown() {
    System.clearProperty(APPIUM_SERVER);
    System.clearProperty(APPIUM_PORT);
  }

  @Test
  public void testBuildAppiumServerUrlStringWithSystemProperties() {
    System.setProperty(APPIUM_SERVER, CUSTOM_SERVER_URL);
    System.setProperty(APPIUM_PORT, CUSTOM_PORT);
    String appiumServerUrlString = AppiumServerUtil.buildAppiumServerUrlString(
      this.mobileConfiguration
    );
    assertEquals(appiumServerUrlString, CUSTOM_SERVER_URL + ":" + CUSTOM_PORT);
  }

  @Test
  public void testBuildAppiumServerUrlStringWithoutSystemProperties() {
    String appiumServerUrlString = AppiumServerUtil.buildAppiumServerUrlString(
      this.mobileConfiguration
    );

    assertEquals(appiumServerUrlString, DEFAULT_SERVER_URL + ":" + DEFAULT_PORT);
  }

  @Test
  public void testBuildAppiumServerUrlStringWithPartialSystemProperties() {
    System.setProperty(APPIUM_SERVER, CUSTOM_SERVER_URL);

    String appiumServerUrlString = AppiumServerUtil.buildAppiumServerUrlString(
      this.mobileConfiguration
    );

    assertEquals(appiumServerUrlString, CUSTOM_SERVER_URL + ":" + DEFAULT_PORT);
  }

  @Test
  public void testBuildAppiumServerUrlStringWithOtherPartialSystemProperties() {
    System.setProperty(APPIUM_PORT, CUSTOM_PORT);

    String appiumServerUrlString = AppiumServerUtil.buildAppiumServerUrlString(
      this.mobileConfiguration
    );

    assertEquals(appiumServerUrlString, DEFAULT_SERVER_URL + ":" + CUSTOM_PORT);
  }
}
