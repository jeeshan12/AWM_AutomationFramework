package org.kira.automation.factory;

import java.util.function.Consumer;

import org.kira.automation.exceptions.FrameworkGenericException;
import org.openqa.selenium.chrome.ChromeOptions;

public final class ChromeOptionsDecorator {

    private ChromeOptionsDecorator(){
       throw new FrameworkGenericException ("You can not use the constructor to create object of this class");
    }

    static final Consumer<ChromeOptions> CHROME_HEADLESS_DECORATOR = chromeOptions -> {
        chromeOptions.addArguments ("--headless=new");
        chromeOptions.addArguments ("--no-sandbox");
        chromeOptions.addArguments ("--disable-gpu");
        chromeOptions.addArguments ("--disable-dev-shm-usage");
    };


}
