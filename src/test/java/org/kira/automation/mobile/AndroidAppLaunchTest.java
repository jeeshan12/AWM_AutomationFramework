package org.kira.automation.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.kira.automation.annotations.Android;
import org.kira.automation.annotations.Mobile;
import org.kira.automation.drivers.adaptee.AndroidMobileDriver;
import org.kira.automation.drivers.adapter.WebDriverAdapter;
import org.kira.automation.drivers.adapter.WebDriverAdapterImpl;
import org.kira.automation.runner.TestSuiteRunner;
import org.testng.annotations.Test;

public class AndroidAppLaunchTest extends TestSuiteRunner {


  @Test @Android @Mobile
  public void launchApp() {
    WebDriverAdapter  webDriverAdapter = new WebDriverAdapterImpl(new AndroidMobileDriver());
    AndroidDriver driver = (AndroidDriver) getDriver();
    System.out.println("Location service is enabled ? : " + driver.isLocationServicesEnabled());
  }

}
