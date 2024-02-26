package org.kira.automation.browsers;

import java.util.List;
import java.util.function.BiConsumer;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public final class FirefoxOptionsDecorator {

  static final BiConsumer<Boolean, FirefoxOptions> FIREFOX_HEADLESS_DECORATOR = (isHeadless, firefoxOptions) -> {
    if (Boolean.TRUE.equals(isHeadless)) {
      firefoxOptions.addArguments("--headless");
    }
  };
  static final BiConsumer<List<String>, FirefoxOptions> ADD_ARGUMENTS_DECORATOR = (arguments, firefoxOptions) -> arguments.forEach(
      firefoxOptions::addArguments);
  static final BiConsumer<List<String>, FirefoxProfile> DOWNLOAD_FILE_DECORATOR = (arguments, firefoxProfile) ->
      arguments
          .stream()
          .map(options -> options.split("="))
          .forEach(option -> firefoxProfile.setPreference(option[0], option[1]));
  private FirefoxOptionsDecorator() {
    throw new FrameworkGenericException(
        "You can not use the constructor to create object of this class");
  }
}
