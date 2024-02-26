package org.kira.automation.browsers;

import static org.kira.automation.browsers.ChromeOptionsDecorator.ADD_ARGUMENTS_DECORATOR;
import static org.kira.automation.browsers.ChromeOptionsDecorator.CHROME_HEADLESS_DECORATOR;
import static org.kira.automation.browsers.ChromeOptionsDecorator.DOWNLOAD_FILE_DECORATOR;

import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.web.ChromeOptionsConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowserDriverServiceImpl implements BrowserDriverService {

  @Override
  public WebDriver getWebDriver(final Configuration configuration) {
    ChromeOptions chromeOptions = getBrowserOptions(configuration);
    return new ChromeDriver(chromeOptions);
  }


  private ChromeOptions getBrowserOptions(final Configuration configuration) {
    ChromeOptions chromeOptions = new ChromeOptions();
    ChromeOptionsConfig chromeOptionsConfig = configuration.getWeb()
        .getBrowserOptions()
        .getChrome();
    ADD_ARGUMENTS_DECORATOR.accept(chromeOptionsConfig.getOptions(), chromeOptions);

    CHROME_HEADLESS_DECORATOR.accept(configuration.getWeb().isHeadless(), chromeOptions);

    if (chromeOptionsConfig.getDownloadOption().isDownloadRequired()) {
      DOWNLOAD_FILE_DECORATOR.accept(
          chromeOptionsConfig.getDownloadOption().getDownloadOptions(), chromeOptions
      );
    }

    return chromeOptions;
  }
}
