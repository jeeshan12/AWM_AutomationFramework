package org.kira.automation.utils;

import static org.kira.automation.constants.FrameworkConstants.APPIUM_PORT;
import static org.kira.automation.constants.FrameworkConstants.APPIUM_SERVER;

import java.util.Optional;
import org.kira.automation.configuration.mobile.MobileConfiguration;

public class AppiumServerUtil {

  private AppiumServerUtil() {}

  public static String buildAppiumServerUrlString(MobileConfiguration mobileConfiguration) {
    return String.format(
      "%s:%s",
      Optional.ofNullable(System.getProperty(APPIUM_SERVER)).orElse(
        mobileConfiguration.getServerUrl()
      ),
      Optional.ofNullable(System.getProperty(APPIUM_PORT)).orElse(
        String.valueOf(mobileConfiguration.getPort())
      )
    );
  }
}
