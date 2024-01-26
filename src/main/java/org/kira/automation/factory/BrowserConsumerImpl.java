package org.kira.automation.factory;

import org.kira.automation.configuration.Configuration;
import org.openqa.selenium.WebDriver;

public class BrowserConsumerImpl implements BrowserConsumer {

  private BrowserDriverService browserDriverService;

  public BrowserConsumerImpl(BrowserDriverService browserDriverService) {
    this.browserDriverService = browserDriverService;
  }

  @Override
  public WebDriver getWebDriver(Configuration configuration) {
    return browserDriverService.getWebDriver(configuration);
  }
}
