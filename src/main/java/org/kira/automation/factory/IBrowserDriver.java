package org.kira.automation.factory;

import org.kira.automation.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.AbstractDriverOptions;

public interface IBrowserDriver {

    WebDriver getWebDriver(final Configuration configuration);
    AbstractDriverOptions<? extends AbstractDriverOptions> getBrowserOptions(final Configuration configuration);
}
