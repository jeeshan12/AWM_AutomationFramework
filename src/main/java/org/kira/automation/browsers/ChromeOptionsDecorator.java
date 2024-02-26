package org.kira.automation.browsers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.openqa.selenium.chrome.ChromeOptions;

public final class ChromeOptionsDecorator {

  static final BiConsumer<Boolean, ChromeOptions> CHROME_HEADLESS_DECORATOR = (isHeadless, chromeOptions) -> {
    if (Boolean.TRUE.equals(isHeadless)) {
      String[] headlessOptions = new String[]{"--headless=new", "--no-sandbox", "--disable-gpu",
          "--disable-dev-shm-usage"};
      Arrays.stream(headlessOptions).forEach(chromeOptions::addArguments);
    }
  };
  static final BiConsumer<List<String>, ChromeOptions> ADD_ARGUMENTS_DECORATOR = (arguments, chromeOptions) -> arguments.forEach(
      chromeOptions::addArguments);
  static final BiConsumer<List<String>, ChromeOptions> DOWNLOAD_FILE_DECORATOR = (arguments, chromeOptions) -> {
    Map<String, Object> chromePrefs = new HashMap<>();
    arguments
        .stream()
        .map(options -> options.split("="))
        .forEach(option -> chromePrefs.put(option[0], option[1]));
    chromeOptions.setExperimentalOption("prefs", chromePrefs);
  };

  private ChromeOptionsDecorator() {
    throw new FrameworkGenericException(
        "You can not use the constructor to create object of this class");
  }

}
