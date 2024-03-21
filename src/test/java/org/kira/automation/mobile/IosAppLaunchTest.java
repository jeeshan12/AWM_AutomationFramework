package org.kira.automation.mobile;

import io.appium.java_client.ios.IOSDriver;
import org.kira.automation.annotations.Android;
import org.kira.automation.annotations.Mobile;
import org.kira.automation.annotations.iOS;
import org.kira.automation.runner.TestSuiteRunner;
import org.testng.annotations.Test;

public class IosAppLaunchTest extends TestSuiteRunner {

  @Test
  @Android
  @Mobile
  @iOS
  public void launchApp() {
    IOSDriver driver = (IOSDriver) getDriver();
    System.out.println("Location service is enabled ? : " + driver.isDeviceLocked());
  }
}
