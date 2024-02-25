package org.kira.automation.browsers;

import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.web.FirefoxOptionsConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import static org.kira.automation.browsers.FirefoxOptionsDecorator.ADD_ARGUMENTS_DECORATOR;
import static org.kira.automation.browsers.FirefoxOptionsDecorator.DOWNLOAD_FILE_DECORATOR;
import static org.kira.automation.browsers.FirefoxOptionsDecorator.FIREFOX_HEADLESS_DECORATOR;

public class FirefoxBrowserDriverServiceImpl implements BrowserDriverService {

    @Override
    public WebDriver getWebDriver ( final Configuration configuration) {
       FirefoxOptions firefoxOptions =  getBrowserOptions(configuration);
       return new FirefoxDriver (firefoxOptions);
    }

    private FirefoxOptions getBrowserOptions (final Configuration configuration) {
        FirefoxOptions firefoxOptions = new FirefoxOptions ();
        FirefoxOptionsConfig firefoxOptionsConfig = configuration.getWeb ()
            .getBrowserOptions ()
            .getFirefox ();

        FIREFOX_HEADLESS_DECORATOR.accept (configuration.getWeb ().isHeadless (), firefoxOptions);

        ADD_ARGUMENTS_DECORATOR.accept ( firefoxOptionsConfig.getOptions (), firefoxOptions);

        if (firefoxOptionsConfig.getDownloadOption ().isDownloadRequired ()) {
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            DOWNLOAD_FILE_DECORATOR.accept ( firefoxOptionsConfig.getDownloadOption ().getDownloadOptions (), firefoxProfile);
            firefoxOptions.setProfile (firefoxProfile);
        }

        return firefoxOptions;
    }
}
