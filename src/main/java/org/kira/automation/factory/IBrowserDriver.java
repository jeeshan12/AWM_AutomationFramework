package org.kira.automation.factory;

import java.util.Map;

import org.kira.automation.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public interface IBrowserDriver {

    WebDriver getWebDriver(final Configuration configuration);

    AbstractDriverOptions<?> getBrowserOptions(final Configuration configuration);
}
