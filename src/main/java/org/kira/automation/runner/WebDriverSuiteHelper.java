package org.kira.automation.runner;

import static org.kira.automation.constants.FrameworkConstants.BROWSER;
import static org.kira.automation.constants.FrameworkConstants.CHROME;
import static org.kira.automation.constants.FrameworkConstants.FIREFOX;
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
import org.kira.automation.annotations.Grid;
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
import org.openqa.selenium.WebDriver;

public class WebDriverSuiteHelper {

  private WebDriverSuiteHelper() {}

  static void setWebDriver(MethodContextImpl context, Configuration configuration) {
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

  static void addDefaultWebDriver(MethodContextImpl context, Configuration configuration) {
    addChromeDriver(context, configuration);
  }

  static void addiOSDriver(MethodContextImpl context, Configuration configuration) {
    Injector iosDriverServiceInjector = Guice.createInjector(new IosDriverServiceInjector());
    DriverConsumer driverConsumer = iosDriverServiceInjector.getInstance(DriverConsumer.class);
    context.setWebDriver(driverConsumer.getWebDriver(configuration));
  }

  static void addAndroidDriver(MethodContextImpl context, Configuration configuration) {
    Injector androidDriverServiceInjector = Guice.createInjector(
      new AndroidDriverServiceInjector()
    );
    DriverConsumer driverConsumer = androidDriverServiceInjector.getInstance(DriverConsumer.class);
    context.setWebDriver(driverConsumer.getWebDriver(configuration));
  }

  static void addFirefoxDriver(MethodContextImpl context, Configuration configuration) {
    Injector firefoxDriverInjector = Guice.createInjector(new FirefoxBrowserServiceInjector());
    DriverConsumer driverConsumer = firefoxDriverInjector.getInstance(DriverConsumer.class);
    context.setWebDriver(driverConsumer.getWebDriver(configuration));
  }

  static void addChromeDriver(MethodContextImpl context, Configuration configuration) {
    Injector chromeDriverInjector = Guice.createInjector(new ChromeBrowserServiceInjector());
    DriverConsumer driverConsumer = chromeDriverInjector.getInstance(DriverConsumer.class);
    context.setWebDriver(driverConsumer.getWebDriver(configuration));
  }

  public static void setRemoteDriver(MethodContextImpl context, Configuration configuration) {
    Method method = context.method;
    CapabilityBuilder capabilityBuilder = getCapabilityBuilder(method).orElse(
      new DefaultCapabilityBuilder()
    );
    Map<String, String> capabilityMap = capabilityBuilder.buildCapabilities(method);
    context.setWebDriver(getCloudWebDriver(configuration, capabilityMap));
  }

  private static boolean isAnnotationPresent(
    Method method,
    Class<? extends Annotation> annotation
  ) {
    return method.isAnnotationPresent(annotation);
  }

  private static Optional<CapabilityBuilder> getCapabilityBuilder(Method method) {
    if (method.isAnnotationPresent(WebCloud.class)) {
      return Optional.of(new WebCloudCapabilityBuilder());
    } else if (method.isAnnotationPresent(Grid.class)) {
      return Optional.of(new GridCapabilityBuilder());
    } else {
      return Optional.empty();
    }
  }

  private static WebDriver getCloudWebDriver(
    Configuration configuration,
    Map<String, String> capabilityMap
  ) {
    return RemoteDriverFactory.getRemoteWebDriver(
      configuration.getWeb().getCloud().getCloudProvider()
    ).getWebDriver(configuration, capabilityMap);
  }
}
