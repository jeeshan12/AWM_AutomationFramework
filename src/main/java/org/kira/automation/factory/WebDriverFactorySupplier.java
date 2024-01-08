package org.kira.automation.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.kira.automation.exceptions.FrameworkGenericException;

public final  class WebDriverFactorySupplier {

    private WebDriverFactorySupplier (){
       throw new FrameworkGenericException ("You can not use the constructor to create object of this class");
    }

    private static final Map<String, Supplier<IBrowserDriver>> BROWSER_MAP = new HashMap<> ();

    private static final Supplier<IBrowserDriver> CHROME_BROWSER_SUPPLIER = ChromeBrowserDriver::new;

    private static final Supplier<IBrowserDriver> FIREFOX_BROWSER_SUPPLIER = FirefoxBrowserDriver::new;

    static {
        BROWSER_MAP.put ("chrome", CHROME_BROWSER_SUPPLIER);
        BROWSER_MAP.put ("firefox", FIREFOX_BROWSER_SUPPLIER);
    }


    public static IBrowserDriver getWebDriver(final String browserName) {
        return BROWSER_MAP.get (browserName).get ();
    }
}
