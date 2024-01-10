package org.kira.automation.factory;

import org.kira.automation.configuration.Configuration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public interface IBrowserDriver {

    WebDriver getWebDriver(final Configuration configuration);
    MutableCapabilities getBrowserOptions(final Configuration configuration);
}
