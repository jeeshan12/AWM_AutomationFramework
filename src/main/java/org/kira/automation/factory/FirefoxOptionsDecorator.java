package org.kira.automation.factory;

import java.util.function.Consumer;

import org.kira.automation.exceptions.FrameworkGenericException;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class FirefoxOptionsDecorator {

    private FirefoxOptionsDecorator(){
      throw  new FrameworkGenericException ("You can not use the constructor to create object of this class");
    }
    static final Consumer<FirefoxOptions> FIREFOX_HEADLESS_DECORATOR = firefoxOptions -> firefoxOptions.addArguments ("--headless");


}
