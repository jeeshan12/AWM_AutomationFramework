package org.kira.automation.framework;

import static org.kira.automation.constants.FrameworkConstants.APPIUM_Port;
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
    mobileConfiguration = mock(MobileConfiguration.class);

    when(mobileConfiguration.getServerUrl()).thenReturn(DEFAULT_SERVER_URL);
    when(mobileConfiguration.getPort()).thenReturn(DEFAULT_PORT);
  }

  @AfterMethod
  public void tearDown() {
    System.clearProperty(APPIUM_SERVER);
    System.clearProperty(APPIUM_Port);
  }

  @Test
  public void testBuildAppiumServerUrlStringWithSystemProperties() {
    System.setProperty(APPIUM_SERVER, CUSTOM_SERVER_URL);
    System.setProperty(APPIUM_Port, CUSTOM_PORT);
    String appiumServerUrlString = AppiumServerUtil.buildAppiumServerUrlString(mobileConfiguration);
    assertEquals(appiumServerUrlString, CUSTOM_SERVER_URL + ":" + CUSTOM_PORT);
  }

  @Test
  public void testBuildAppiumServerUrlStringWithoutSystemProperties() {
    String appiumServerUrlString = AppiumServerUtil.buildAppiumServerUrlString(mobileConfiguration);

    assertEquals(appiumServerUrlString, DEFAULT_SERVER_URL + ":" + DEFAULT_PORT);
  }

  @Test
  public void testBuildAppiumServerUrlStringWithPartialSystemProperties() {
    System.setProperty(APPIUM_SERVER, CUSTOM_SERVER_URL);

    String appiumServerUrlString = AppiumServerUtil.buildAppiumServerUrlString(mobileConfiguration);

    assertEquals(appiumServerUrlString, CUSTOM_SERVER_URL + ":" + DEFAULT_PORT);
  }

  @Test
  public void testBuildAppiumServerUrlStringWithOtherPartialSystemProperties() {
    System.setProperty(APPIUM_Port, CUSTOM_PORT);

    String appiumServerUrlString = AppiumServerUtil.buildAppiumServerUrlString(mobileConfiguration);

    assertEquals(appiumServerUrlString, DEFAULT_SERVER_URL + ":" + CUSTOM_PORT);
  }
}
