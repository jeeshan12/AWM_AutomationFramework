package org.kira.automation.factory;

import static org.kira.automation.factory.FirefoxOptionsDecorator.ADD_ARGUMENTS_DECORATOR;
import static org.kira.automation.factory.FirefoxOptionsDecorator.DOWNLOAD_FILE_DECORATOR;
import static org.kira.automation.factory.FirefoxOptionsDecorator.FIREFOX_HEADLESS_DECORATOR;

import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.web.FirefoxOptionsConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxBrowserDriver implements IBrowserDriver {

    @Override
    public WebDriver getWebDriver ( final Configuration configuration) {
       FirefoxOptions firefoxOptions =  getBrowserOptions(configuration);
       return new FirefoxDriver (firefoxOptions);
    }

    @Override
    public FirefoxOptions getBrowserOptions (final Configuration configuration) {
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
