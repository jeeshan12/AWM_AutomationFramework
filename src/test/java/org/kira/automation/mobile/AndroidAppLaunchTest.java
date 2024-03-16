package org.kira.automation.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.kira.automation.annotations.Android;
import org.kira.automation.annotations.Mobile;
import org.kira.automation.runner.TestSuiteRunner;
import org.testng.annotations.Test;

public class AndroidAppLaunchTest extends TestSuiteRunner {


  @Test @Android @Mobile
  public void launchApp() {
    AndroidDriver driver = (AndroidDriver) getDriver();
    System.out.println("Location service is enabled ? : " + driver.isLocationServicesEnabled());
  }

}
