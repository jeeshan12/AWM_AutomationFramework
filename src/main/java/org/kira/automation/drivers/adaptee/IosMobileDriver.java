package org.kira.automation.drivers.adaptee;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;

public class IosMobileDriver implements MobileDriver {

  @Override
  public IOSDriver getMobileDriver(WebDriver driver) {
    return (IOSDriver) driver;
  }
}
