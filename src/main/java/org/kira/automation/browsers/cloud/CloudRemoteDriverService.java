package org.kira.automation.browsers.cloud;

import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.cloud.CloudConfiguration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public interface CloudRemoteDriverService {

  WebDriver getWebDriver(final Configuration configuration);

  MutableCapabilities getPlatformSpecificCapabilities(CloudConfiguration cloudConfiguration);


}
