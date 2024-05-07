package org.kira.automation.drivers.mobile;

import static org.kira.automation.utils.AppiumServerUtil.buildAppiumServerUrlString;
import static org.kira.automation.utils.IosCapabilitiesUtil.configureCapabilities;

import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.mobile.IosConfiguration;
import org.kira.automation.drivers.WebDriverService;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public class IosDriverServiceImpl implements WebDriverService {

  @Override
  public WebDriver getWebDriver(final Configuration configuration) {
    IosConfiguration iosConfiguration = configuration.getMobile().getIosConfiguration();
    MutableCapabilities capabilities = new MutableCapabilities();
    addGlobalCapabilitiesForAppium(configuration.getMobile(), capabilities);
    configureCapabilities(iosConfiguration, capabilities);
    Optional.ofNullable(iosConfiguration.getBrowserName())
      .filter(StringUtils::isNotEmpty)
      .ifPresent(browserName -> capabilities.setCapability("browserName", browserName));
    addAdditionalCapabilities(iosConfiguration.getAdditionalCapabilities(), capabilities);
    try {
      return new IOSDriver(
        new URI(buildAppiumServerUrlString(configuration.getMobile())).toURL(),
        capabilities
      );
    } catch (MalformedURLException | URISyntaxException e) {
      throw new FrameworkGenericException("Error while creating the web driver reference", e);
    }
  }
}
