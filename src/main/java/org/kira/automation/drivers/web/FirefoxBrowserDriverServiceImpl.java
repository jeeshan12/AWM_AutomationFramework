package org.kira.automation.drivers.web;

import static org.kira.automation.constants.FrameworkConstants.HEADLESS;
import static org.kira.automation.drivers.web.FirefoxOptionsDecorator.ADD_ARGUMENTS_DECORATOR;
import static org.kira.automation.drivers.web.FirefoxOptionsDecorator.DOWNLOAD_FILE_DECORATOR;
import static org.kira.automation.drivers.web.FirefoxOptionsDecorator.FIREFOX_HEADLESS_DECORATOR;

import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.web.FirefoxOptionsConfig;
import org.kira.automation.drivers.WebDriverService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxBrowserDriverServiceImpl implements WebDriverService {

  @Override
  public WebDriver getWebDriver(final Configuration configuration) {
    FirefoxOptions firefoxOptions = getBrowserOptions(configuration);
    return new FirefoxDriver(firefoxOptions);
  }

  private FirefoxOptions getBrowserOptions(final Configuration configuration) {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    FirefoxOptionsConfig firefoxOptionsConfig = configuration
      .getWeb()
      .getBrowserOptions()
      .getFirefox();

    boolean isHeadless = Boolean.valueOf(System.getProperty(HEADLESS))
      ? true
      : configuration.getWeb().isHeadless();
    FIREFOX_HEADLESS_DECORATOR.accept(isHeadless, firefoxOptions);

    ADD_ARGUMENTS_DECORATOR.accept(firefoxOptionsConfig.getOptions(), firefoxOptions);

    if (firefoxOptionsConfig.getDownloadOption().isDownloadRequired()) {
      FirefoxProfile firefoxProfile = new FirefoxProfile();
      DOWNLOAD_FILE_DECORATOR.accept(
        firefoxOptionsConfig.getDownloadOption().getDownloadOptions(),
        firefoxProfile
      );
      firefoxOptions.setProfile(firefoxProfile);
    }

    return firefoxOptions;
  }
}
