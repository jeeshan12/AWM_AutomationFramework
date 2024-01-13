package org.kira.automation.runner;

import static org.kira.automation.constants.FrameworkConstants.BROWSER;
import static org.kira.automation.constants.FrameworkConstants.CHROME;
import static org.kira.automation.constants.FrameworkConstants.FIREFOX;

import java.lang.reflect.Method;

import org.kira.automation.annotations.Android;
import org.kira.automation.annotations.Chrome;
import org.kira.automation.annotations.Firefox;
import org.kira.automation.annotations.iOS;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.exceptions.AnnotationMissingException;
import org.kira.automation.factory.WebDriverFactorySupplier;

public class WebDriverSuiteHelper {

    private WebDriverSuiteHelper() {}

     static void setWebDriver (final MethodContextImpl context, final Configuration configuration) {
        Method method = context.method;
        if (method.isAnnotationPresent (Chrome.class)
            || CHROME.equalsIgnoreCase (System.getenv (BROWSER))) {
            addChromeDriver(context, configuration);
        } else if (method.isAnnotationPresent (Firefox.class) ||
            FIREFOX.equalsIgnoreCase (System.getenv (BROWSER))) {
            addFirefoxDriver(context, configuration);
        } else if (method.isAnnotationPresent (iOS.class) ){
            addAndroidDriver(context, configuration);
        } else if (method.isAnnotationPresent (Android.class)) {
            addiOSDriver(context, configuration);
        } else {
            throw new AnnotationMissingException (
                "Please provide valid annotations like  like @Chrome, @Firefox, @iOS and @Android to initialise the webdriver"
            );
        }
    }



     static void addDefaultWebDriver (final MethodContextImpl context, final Configuration configuration) {
        context.setWebDriver (
            WebDriverFactorySupplier.getWebDriver (configuration.getWeb ().getBrowser ()).getWebDriver (configuration)
        );
    }

     static void addiOSDriver (final MethodContextImpl context, final Configuration configuration) {
        // TODO document why this method is empty
    }

     static void addAndroidDriver (final MethodContextImpl context, final Configuration configuration) {
        // TODO document why this method is empty
    }

     static void addFirefoxDriver (final MethodContextImpl context, final Configuration configuration) {
        context.setWebDriver (
            WebDriverFactorySupplier.getWebDriver (FIREFOX).getWebDriver (configuration)
        );
    }
     static void addChromeDriver (final MethodContextImpl context, final Configuration configuration) {
        context.setWebDriver (
            WebDriverFactorySupplier.getWebDriver (CHROME).getWebDriver (configuration)
        );
    }
}
