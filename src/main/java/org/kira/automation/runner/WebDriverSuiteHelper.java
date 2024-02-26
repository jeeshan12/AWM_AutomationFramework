package org.kira.automation.runner;

import static org.kira.automation.constants.FrameworkConstants.BROWSER;
import static org.kira.automation.constants.FrameworkConstants.BROWSER_NAME;
import static org.kira.automation.constants.FrameworkConstants.BROWSER_VERSION;
import static org.kira.automation.constants.FrameworkConstants.CHROME;
import static org.kira.automation.constants.FrameworkConstants.FIREFOX;
import static org.kira.automation.constants.FrameworkConstants.OS;
import static org.kira.automation.constants.FrameworkConstants.OS_VERSION;
import static org.kira.automation.constants.FrameworkConstants.RESOLUTION;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import org.kira.automation.annotations.Android;
import org.kira.automation.annotations.Chrome;
import org.kira.automation.annotations.Firefox;
import org.kira.automation.annotations.WebCloud;
import org.kira.automation.annotations.iOS;
import org.kira.automation.browsers.BrowserConsumer;
import org.kira.automation.browsers.ChromeBrowserServiceInjector;
import org.kira.automation.browsers.FirefoxBrowserServiceInjector;
import org.kira.automation.browsers.factory.RemoteDriverFactory;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.exceptions.AnnotationMissingException;
import org.kira.automation.exceptions.FrameworkGenericException;

public class WebDriverSuiteHelper {

  private WebDriverSuiteHelper() {
  }

  static void setWebDriver(final MethodContextImpl context, final Configuration configuration) {
    Method method = context.method;
    String browser = System.getenv(BROWSER);

    if (method.isAnnotationPresent(Chrome.class)
        || CHROME.equalsIgnoreCase(browser)) {
      addChromeDriver(context, configuration);
    } else if (method.isAnnotationPresent(Firefox.class) ||
        FIREFOX.equalsIgnoreCase(browser)) {
      addFirefoxDriver(context, configuration);
    } else if (method.isAnnotationPresent(iOS.class)) {
      addAndroidDriver(context, configuration);
    } else if (method.isAnnotationPresent(Android.class)) {
      addiOSDriver(context, configuration);
    } else {
      throw new AnnotationMissingException(
          "Please provide valid annotations like  like @Chrome, @Firefox, @iOS and @Android to initialise the webdriver"
      );
    }
  }


  static void addDefaultWebDriver(final MethodContextImpl context,
      final Configuration configuration) {
    addChromeDriver(context, configuration);
  }

  static void addiOSDriver(final MethodContextImpl context, final Configuration configuration) {
    // TODO document q q why this method is empty
  }

  static void addAndroidDriver(final MethodContextImpl context, final Configuration configuration) {
    // TODO document why this method is empty
  }

  static void addFirefoxDriver(final MethodContextImpl context, final Configuration configuration) {
    Injector firefoxDriverInjector = Guice.createInjector(new FirefoxBrowserServiceInjector());
    BrowserConsumer browserConsumer = firefoxDriverInjector.getInstance(BrowserConsumer.class);
    context.setWebDriver(
        browserConsumer.getWebDriver(configuration)
    );
  }

  static void addChromeDriver(final MethodContextImpl context, final Configuration configuration) {
    Injector chromeDriverInjector = Guice.createInjector(new ChromeBrowserServiceInjector());
    BrowserConsumer browserConsumer = chromeDriverInjector.getInstance(BrowserConsumer.class);
    context.setWebDriver(
        browserConsumer.getWebDriver(configuration)
    );
  }

  public static void setRemoteDriver(MethodContextImpl context, Configuration configuration) {

    if (configuration.getWeb().getCloud().isCloudExecutionEnabled()) {
      setWebCloudRemoteDriver(context, configuration);
    } else if (configuration.getWeb().getSeleniumGrid().isGridEnabled()) {
      context.setWebDriver(RemoteDriverFactory.getRemoteWebDriver(
              configuration.getWeb().getCloud().getCloudProvider())
          .getWebDriver(configuration, Optional.empty()));
    } else {
      throw new FrameworkGenericException(
          "Please provide valid cloud provider or grid properties for remote execution");
    }
  }

  private static void setWebCloudRemoteDriver(MethodContextImpl context,
      Configuration configuration) {

    Method method = context.method;

    if (method.isAnnotationPresent(WebCloud.class)) {
      WebCloud webCloudAnnotation = method.getAnnotation(WebCloud.class);
      Map<String, String> capabilityMap = Map.of(
          BROWSER_VERSION, webCloudAnnotation.browserVersion(),
          OS, webCloudAnnotation.os(),
          OS_VERSION, webCloudAnnotation.osVersion(),
          BROWSER_NAME, webCloudAnnotation.browserName(),
          RESOLUTION, webCloudAnnotation.resolution()
      );

      context.setWebDriver(
          RemoteDriverFactory.getRemoteWebDriver(
                  configuration.getWeb().getCloud().getCloudProvider())
              .getWebDriver(configuration, Optional.of(capabilityMap))
      );

    } else {
      context.setWebDriver(
          RemoteDriverFactory.getRemoteWebDriver(
                  configuration.getWeb().getCloud().getCloudProvider())
              .getWebDriver(configuration, Optional.empty())
      );
    }
  }
}
