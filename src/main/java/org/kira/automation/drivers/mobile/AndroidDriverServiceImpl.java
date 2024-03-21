package org.kira.automation.drivers.mobile;

import static org.kira.automation.utils.AndroidCapabilitiesUtil.addDeviceLockCapabilities;
import static org.kira.automation.utils.AndroidCapabilitiesUtil.configureCapabilities;

import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.mobile.AndroidConfiguration;
import org.kira.automation.drivers.WebDriverService;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public class AndroidDriverServiceImpl implements WebDriverService {

  @Override
  public WebDriver getWebDriver(final Configuration configuration) {
    AndroidConfiguration androidConfiguration = configuration.getMobile().getAndroidConfiguration();
    MutableCapabilities capabilities = new MutableCapabilities();
    addGlobalCapabilitiesForAppium(configuration.getMobile(), capabilities);
    configureCapabilities(androidConfiguration, capabilities);
    Optional.ofNullable(androidConfiguration.getDeviceLockConfiguration().isDeviceLockEnabled())
      .filter(Boolean::booleanValue)
      .ifPresent(enabled -> addDeviceLockCapabilities(androidConfiguration, capabilities));
    Optional.ofNullable(androidConfiguration.getBrowserName())
      .filter(StringUtils::isNotEmpty)
      .ifPresent(browserName -> capabilities.setCapability("browserName", browserName));
    Optional.ofNullable(androidConfiguration.getChromedriverExecutable())
      .filter(StringUtils::isNotEmpty)
      .ifPresent(
        chromedriverExecutable ->
          capabilities.setCapability(
            WebDriverService.getCapabilityWithAppiumPrefix("chromedriverExecutable"),
            chromedriverExecutable
          )
      );
    addAdditionalCapabilities(androidConfiguration.getAdditionalCapabilities(), capabilities);
    try {
      return new AndroidDriver(
        new URI(
          String.format(
            "%s:%s",
            configuration.getMobile().getServerUrl(),
            configuration.getMobile().getPort()
          )
        ).toURL(),
        capabilities
      );
    } catch (MalformedURLException | URISyntaxException e) {
      throw new FrameworkGenericException("Error while creating the web driver reference", e);
    }
  }
}
