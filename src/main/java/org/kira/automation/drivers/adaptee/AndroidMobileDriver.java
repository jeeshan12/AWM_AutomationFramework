package org.kira.automation.drivers.adaptee;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

public class AndroidMobileDriver implements MobileDriver {
  @Override
  public AndroidDriver getMobileDriver(WebDriver driver) {
    return (AndroidDriver) driver;
  }
}
