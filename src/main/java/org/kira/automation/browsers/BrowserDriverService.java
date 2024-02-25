package org.kira.automation.browsers;

import org.kira.automation.configuration.Configuration;
import org.openqa.selenium.WebDriver;

public interface BrowserDriverService {

    WebDriver getWebDriver(final Configuration configuration);
}
