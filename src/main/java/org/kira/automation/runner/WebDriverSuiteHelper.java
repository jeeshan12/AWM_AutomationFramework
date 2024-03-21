package org.kira.automation.runner;

import static org.kira.automation.constants.FrameworkConstants.BROWSER;
import static org.kira.automation.constants.FrameworkConstants.BROWSER_NAME;
import static org.kira.automation.constants.FrameworkConstants.BROWSER_VERSION;
import static org.kira.automation.constants.FrameworkConstants.CHROME;
import static org.kira.automation.constants.FrameworkConstants.FIREFOX;
import static org.kira.automation.constants.FrameworkConstants.OS;
import static org.kira.automation.constants.FrameworkConstants.OS_VERSION;
import static org.kira.automation.constants.FrameworkConstants.RESOLUTION;
import static org.kira.automation.drivers.mobile.MobileDriverFactory.addMobileDriver;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import org.kira.automation.annotations.Android;
import org.kira.automation.annotations.Chrome;
import org.kira.automation.annotations.Firefox;
import org.kira.automation.annotations.Mobile;
import org.kira.automation.annotations.Web;
import org.kira.automation.annotations.WebCloud;
import org.kira.automation.annotations.iOS;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.drivers.DriverConsumer;
import org.kira.automation.drivers.cloud.factory.RemoteDriverFactory;
import org.kira.automation.drivers.mobile.AndroidDriverServiceInjector;
import org.kira.automation.drivers.mobile.IosDriverServiceInjector;
import org.kira.automation.drivers.web.ChromeBrowserServiceInjector;
import org.kira.automation.drivers.web.FirefoxBrowserServiceInjector;
import org.kira.automation.exceptions.AnnotationMissingException;
import org.kira.automation.exceptions.FrameworkGenericException;

public class WebDriverSuiteHelper {

  private WebDriverSuiteHelper() {}

  static void setWebDriver(final MethodContextImpl context, final Configuration configuration) {
    Method method = context.method;
    String browser = System.getenv(BROWSER);
    if (
      (isAnnotationPresent(method, Chrome.class) || CHROME.equalsIgnoreCase(browser)) &&
      isAnnotationPresent(method, Web.class)
    ) {
      addChromeDriver(context, configuration);
    } else if (
      (isAnnotationPresent(method, Firefox.class) || FIREFOX.equalsIgnoreCase(browser)) &&
      isAnnotationPresent(method, Web.class)
    ) {
      addFirefoxDriver(context, configuration);
    } else if (
      (isAnnotationPresent(method, iOS.class)) && isAnnotationPresent(method, Mobile.class)
    ) {
      addiOSDriver(context, configuration);
    } else if (
      (isAnnotationPresent(method, Android.class)) && isAnnotationPresent(method, Mobile.class)
    ) {
      addAndroidDriver(context, configuration);
    } else if (isAnnotationPresent(method, Mobile.class)) {
      addMobileDriver(context, configuration, configuration.getMobile().getPlatform());
    } else {
      throw new AnnotationMissingException(
        "Please provide valid annotations like  like @Web , @Chrome, @Firefox, @Mobile, @iOS and @Android to initialise the webdriver"
      );
    }
  }

  static void addDefaultWebDriver(
    final MethodContextImpl context,
    final Configuration configuration
  ) {
    addChromeDriver(context, configuration);
  }

  static void addiOSDriver(final MethodContextImpl context, final Configuration configuration) {
    Injector iosDriverServiceInjector = Guice.createInjector(new IosDriverServiceInjector());
    DriverConsumer driverConsumer = iosDriverServiceInjector.getInstance(DriverConsumer.class);
    context.setWebDriver(driverConsumer.getWebDriver(configuration));
  }

  static void addAndroidDriver(final MethodContextImpl context, final Configuration configuration) {
    Injector androidDriverServiceInjector = Guice.createInjector(
      new AndroidDriverServiceInjector()
    );
    DriverConsumer driverConsumer = androidDriverServiceInjector.getInstance(DriverConsumer.class);
    context.setWebDriver(driverConsumer.getWebDriver(configuration));
  }

  static void addFirefoxDriver(final MethodContextImpl context, final Configuration configuration) {
    Injector firefoxDriverInjector = Guice.createInjector(new FirefoxBrowserServiceInjector());
    DriverConsumer driverConsumer = firefoxDriverInjector.getInstance(DriverConsumer.class);
    context.setWebDriver(driverConsumer.getWebDriver(configuration));
  }

  static void addChromeDriver(final MethodContextImpl context, final Configuration configuration) {
    Injector chromeDriverInjector = Guice.createInjector(new ChromeBrowserServiceInjector());
    DriverConsumer driverConsumer = chromeDriverInjector.getInstance(DriverConsumer.class);
    context.setWebDriver(driverConsumer.getWebDriver(configuration));
  }

  public static void setRemoteDriver(MethodContextImpl context, Configuration configuration) {
    if (configuration.getWeb().getCloud().isCloudExecutionEnabled()) {
      setWebCloudRemoteDriver(context, configuration);
    } else if (configuration.getWeb().getSeleniumGrid().isGridEnabled()) {
      context.setWebDriver(
        RemoteDriverFactory.getRemoteWebDriver(
          configuration.getWeb().getCloud().getCloudProvider()
        ).getWebDriver(configuration, Optional.empty())
      );
    } else {
      throw new FrameworkGenericException(
        "Please provide valid cloud provider or grid properties for remote execution"
      );
    }
  }

  private static void setWebCloudRemoteDriver(
    MethodContextImpl context,
    Configuration configuration
  ) {
    Method method = context.method;

    if (method.isAnnotationPresent(WebCloud.class)) {
      WebCloud webCloudAnnotation = method.getAnnotation(WebCloud.class);
      Map<String, String> capabilityMap = Map.of(
        BROWSER_VERSION,
        webCloudAnnotation.browserVersion(),
        OS,
        webCloudAnnotation.os(),
        OS_VERSION,
        webCloudAnnotation.osVersion(),
        BROWSER_NAME,
        webCloudAnnotation.browserName(),
        RESOLUTION,
        webCloudAnnotation.resolution()
      );

      context.setWebDriver(
        RemoteDriverFactory.getRemoteWebDriver(
          configuration.getWeb().getCloud().getCloudProvider()
        ).getWebDriver(configuration, Optional.of(capabilityMap))
      );
    } else {
      context.setWebDriver(
        RemoteDriverFactory.getRemoteWebDriver(
          configuration.getWeb().getCloud().getCloudProvider()
        ).getWebDriver(configuration, Optional.empty())
      );
    }
  }

  private static boolean isAnnotationPresent(
    Method method,
    Class<? extends Annotation> annotation
  ) {
    return method.isAnnotationPresent(annotation);
  }
}
