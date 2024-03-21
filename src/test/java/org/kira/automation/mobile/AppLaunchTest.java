package org.kira.automation.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.kira.automation.annotations.Mobile;
import org.kira.automation.runner.TestSuiteRunner;
import org.testng.annotations.Test;

public class AppLaunchTest extends TestSuiteRunner {


  @Test
  @Mobile
  public void launchAppAndroid() {
    System.out.println(
        "Run this test to test Android when platform is set to Android in config.json");
    AndroidDriver androidDriver = (AndroidDriver) getDriver();
    System.out.println("Current Activity : " + androidDriver.currentActivity());
  }

  @Test
  @Mobile
  public void launchAppIOS() {
    System.out.println("Run this test to test IOS when platform is set to ios in config.json");
    IOSDriver iosDriver = (IOSDriver) getDriver();
    System.out.println("Is Device locked : " + iosDriver.isDeviceLocked());
  }

}
