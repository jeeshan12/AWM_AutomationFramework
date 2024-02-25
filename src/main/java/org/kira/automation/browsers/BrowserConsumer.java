package org.kira.automation.browsers;

import jakarta.inject.Inject;
import org.kira.automation.configuration.Configuration;
import org.openqa.selenium.WebDriver;

public class BrowserConsumer {
  private final BrowserDriverService browserDriverService;
  @Inject
  public BrowserConsumer(BrowserDriverService browserDriverService) {
    this.browserDriverService = browserDriverService;
  }

  public WebDriver getWebDriver(Configuration configuration) {
    return browserDriverService.getWebDriver(configuration);
  }
}
