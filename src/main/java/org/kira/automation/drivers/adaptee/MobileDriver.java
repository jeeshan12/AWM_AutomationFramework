package org.kira.automation.drivers.adaptee;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public interface MobileDriver {

   AppiumDriver getMobileDriver(WebDriver driver);
}
