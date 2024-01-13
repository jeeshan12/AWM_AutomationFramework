package org.kira.automation.factory;

import java.util.HashMap;
import java.util.Map;


import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.web.ChromeOptionsConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class ChromeBrowserDriver implements IBrowserDriver{
    @Override
    public WebDriver getWebDriver (final Configuration configuration) {
       ChromeOptions chromeOptions =  getBrowserOptions(configuration);
       return new ChromeDriver (chromeOptions);
    }

    @Override
    public ChromeOptions getBrowserOptions (final Configuration configuration) {
        ChromeOptions chromeOptions = new ChromeOptions ();
        ChromeOptionsConfig chromeOptionsConfig = configuration.getWeb ()
            .getBrowserOptions ()
            .getChrome ();
        chromeOptionsConfig
            .getOptions ()
             .forEach (chromeOptions::addArguments);

        if (configuration.getWeb ().isHeadless ()) {
            ChromeOptionsDecorator.CHROME_HEADLESS_DECORATOR.accept (chromeOptions);
        }

        if (chromeOptionsConfig.getDownloadOption ().isDownloadRequired ()) {
            Map<String, Object> chromePrefs = new HashMap<> ();
            chromeOptionsConfig
                .getDownloadOption ()
                .getDownloadOptions ()
                .stream()
                .map (options -> options.split ("="))
                .forEach (option -> chromePrefs.put (option[0], option[1]));
            chromeOptions.setExperimentalOption("prefs", chromePrefs);

        }
        return chromeOptions;
    }
}
