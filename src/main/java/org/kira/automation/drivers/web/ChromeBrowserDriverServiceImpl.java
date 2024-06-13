package org.kira.automation.drivers.web;

import static org.kira.automation.constants.FrameworkConstants.HEADLESS;
import static org.kira.automation.drivers.web.ChromeOptionsDecorator.ADD_ARGUMENTS_DECORATOR;
import static org.kira.automation.drivers.web.ChromeOptionsDecorator.CHROME_HEADLESS_DECORATOR;
import static org.kira.automation.drivers.web.ChromeOptionsDecorator.DOWNLOAD_FILE_DECORATOR;

import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.web.ChromeOptionsConfig;
import org.kira.automation.drivers.WebDriverService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowserDriverServiceImpl implements WebDriverService {

  @Override
  public WebDriver getWebDriver(Configuration configuration) {
    ChromeOptions chromeOptions = this.getBrowserOptions(configuration);
    return new ChromeDriver(chromeOptions);
  }

  private ChromeOptions getBrowserOptions(Configuration configuration) {
    ChromeOptions chromeOptions = new ChromeOptions();
    ChromeOptionsConfig chromeOptionsConfig = configuration
      .getWeb()
      .getBrowserOptions()
      .getChrome();
    ADD_ARGUMENTS_DECORATOR.accept(chromeOptionsConfig.getOptions(), chromeOptions);
    boolean isHeadless = Boolean.valueOf(System.getProperty(HEADLESS))
      ? true
      : configuration.getWeb().isHeadless();
    CHROME_HEADLESS_DECORATOR.accept(isHeadless, chromeOptions);

    if (chromeOptionsConfig.getDownloadOption().isDownloadRequired()) {
      DOWNLOAD_FILE_DECORATOR.accept(
        chromeOptionsConfig.getDownloadOption().getDownloadOptions(),
        chromeOptions
      );
    }

    return chromeOptions;
  }
}
