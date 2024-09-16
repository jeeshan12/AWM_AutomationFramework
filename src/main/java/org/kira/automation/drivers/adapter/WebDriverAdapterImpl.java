package org.kira.automation.drivers.adapter;

import io.appium.java_client.AppiumDriver;
import org.kira.automation.drivers.adaptee.MobileDriver;
import org.openqa.selenium.WebDriver;

public class WebDriverAdapterImpl implements WebDriverAdapter {

  MobileDriver mobileDriver;

  public WebDriverAdapterImpl(MobileDriver mobileDriver) {
    this.mobileDriver = mobileDriver;
  }

  @Override
  public AppiumDriver getWebDriver(WebDriver driver) {
    return mobileDriver.getMobileDriver(driver);
  }
}
