package org.kira.automation.browsers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.mobile.AndroidConfiguration;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;

public class AndroidDriverServiceImpl implements WebDriverService {

  @Override
  public WebDriver getWebDriver(final Configuration configuration) {
    AndroidConfiguration androidConfiguration = configuration.getMobile().getAndroidConfiguration();
    MutableCapabilities capabilities = new MutableCapabilities();
    capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
    Optional<String> appLocationOptional = getAppLocation(androidConfiguration.getAppPath(),
        androidConfiguration.getAppName());
    String appName = appLocationOptional.orElseGet(androidConfiguration::getAppUrl);
    capabilities.setCapability(
          getCapabilityWithAppiumPrefix("appPackage"), androidConfiguration.getAppPackage()
    );
    capabilities.setCapability(
          getCapabilityWithAppiumPrefix("appActivity"), androidConfiguration.getAppActivity()
    );
    capabilities.setCapability(
          getCapabilityWithAppiumPrefix("app"), appName
    );

    capabilities.setCapability(
        getCapabilityWithAppiumPrefix("automationName"), AutomationName.ANDROID_UIAUTOMATOR2
    );

    capabilities.setCapability(
        getCapabilityWithAppiumPrefix("udid"), androidConfiguration.getUdid()
    );

    addGlobalCapabilitiesForAppium(configuration.getMobile(), capabilities);

    addAdditionalCapabilities(androidConfiguration.getAdditionalCapabilities(), capabilities);

    try {
      return new AndroidDriver(
          new URL(String.format("%s:%s/wd/hub", configuration.getMobile().getServerUrl(),
              configuration.getMobile().getPort())), capabilities
      );
    } catch (MalformedURLException e) {
      throw new FrameworkGenericException("Error while creating the webdriver reference", e);
    }
  }

  private Optional<String> getAppLocation(String appPath, String appName) {

    if (appPath == null || appName == null) {
      return Optional.empty();
    }
    return Optional.of(String.format("%s%s", appPath, appName));
  }
}
