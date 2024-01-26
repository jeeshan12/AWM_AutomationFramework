package org.kira.automation.factory;

import org.kira.automation.configuration.Configuration;
import org.openqa.selenium.WebDriver;

public interface BrowserConsumer {

  WebDriver getWebDriver(final Configuration configuration);

}
