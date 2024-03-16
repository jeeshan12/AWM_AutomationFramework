package org.kira.automation.drivers;

import jakarta.inject.Inject;
import org.kira.automation.configuration.Configuration;
import org.openqa.selenium.WebDriver;

public class DriverConsumer {

  private final WebDriverService webDriverService;

  @Inject
  public DriverConsumer(WebDriverService webDriverService) {
    this.webDriverService = webDriverService;
  }

  public WebDriver getWebDriver(Configuration configuration) {
    return webDriverService.getWebDriver(configuration);
  }
}
