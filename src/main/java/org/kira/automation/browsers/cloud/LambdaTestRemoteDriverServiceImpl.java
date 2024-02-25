package org.kira.automation.browsers.cloud;

import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.cloud.CloudConfiguration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public class LambdaTestRemoteDriverServiceImpl implements CloudRemoteDriverService {
  @Override public WebDriver getWebDriver(Configuration configuration) {

    return null;
  }

  @Override
  public MutableCapabilities getPlatformSpecificCapabilities(CloudConfiguration cloudConfiguration) {
    return null;
  }
}
