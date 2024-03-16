package org.kira.automation.drivers.mobile;

import static org.kira.automation.utils.AndroidCapabilitiesUtil.addDeviceLockCapabilities;
import static org.kira.automation.utils.AndroidCapabilitiesUtil.configureCapabilities;

import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
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
    if (androidConfiguration.getDeviceLockConfiguration().isDeviceLockEnabled()) {
      addDeviceLockCapabilities(androidConfiguration, capabilities);
    }
    addAdditionalCapabilities(androidConfiguration.getAdditionalCapabilities(), capabilities);
    try {
      return new AndroidDriver(
          new URI(String.format("%s:%s", configuration.getMobile().getServerUrl(),
              configuration.getMobile().getPort())
          ).toURL(), capabilities);
    } catch (MalformedURLException | URISyntaxException e) {
      throw new FrameworkGenericException("Error while creating the webdriver reference", e);
    }
  }

}
