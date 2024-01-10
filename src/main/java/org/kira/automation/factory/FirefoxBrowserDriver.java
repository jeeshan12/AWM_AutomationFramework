package org.kira.automation.factory;


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

        firefoxOptionsConfig
            .getOptions ()
            .forEach (firefoxOptions::addArguments);

        if (firefoxOptionsConfig.getDownloadOption ().isDownloadRequired ()) {
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxOptionsConfig
                .getDownloadOption ()
                .getDownloadOptions ()
                .stream()
                .map (options -> options.split ("="))
                .forEach (option -> firefoxProfile.setPreference (option[0], option[1]));
            firefoxOptions.setProfile (firefoxProfile);
        }

        if (configuration.getWeb ().isHeadless ()) {
            FirefoxOptionsDecorator.FIREFOX_HEADLESS_DECORATOR.accept (firefoxOptions);
        }

        return firefoxOptions;
    }
}
