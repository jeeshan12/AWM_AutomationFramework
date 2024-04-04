package org.kira.automation.drivers.adapter;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public interface WebDriverAdapter {

  AppiumDriver getWebDriver(WebDriver driver);
}
