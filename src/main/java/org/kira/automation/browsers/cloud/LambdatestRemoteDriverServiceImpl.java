package org.kira.automation.browsers.cloud;

import static org.kira.automation.constants.FrameworkConstants.BROWSER_NAME;
import static org.kira.automation.constants.FrameworkConstants.BROWSER_VERSION;
import static org.kira.automation.constants.FrameworkConstants.CHROME;
import static org.kira.automation.constants.FrameworkConstants.CLOUD_ACCESS_KEY;
import static org.kira.automation.constants.FrameworkConstants.CLOUD_USERNAME;
import static org.kira.automation.constants.FrameworkConstants.EDGE;
import static org.kira.automation.constants.FrameworkConstants.FIREFOX;
import static org.kira.automation.constants.FrameworkConstants.IE;
import static org.kira.automation.constants.FrameworkConstants.OS;
import static org.kira.automation.constants.FrameworkConstants.OS_VERSION;
import static org.kira.automation.constants.FrameworkConstants.RESOLUTION;
import static org.kira.automation.constants.FrameworkConstants.SAFARI;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.kira.automation.browsers.cloud.factory.LambdatestBrowserFactory;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.cloud.CloudBrowserConfiguration;
import org.kira.automation.configuration.cloud.CloudConfiguration;
import org.kira.automation.configuration.cloud.lambdatest.LambdatestConfiguration;
import org.kira.automation.enums.Platform;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdatestRemoteDriverServiceImpl implements CloudRemoteDriverService {

  private static final Map<String, LambdatestBrowserConfigRetriever> BROWSER_CONFIG_RETRIEVERS = new HashMap<>();

  static {
    BROWSER_CONFIG_RETRIEVERS.put(CHROME, LambdatestConfiguration::getChromeConfig);
    BROWSER_CONFIG_RETRIEVERS.put(FIREFOX, LambdatestConfiguration::getFirefoxConfig);
    BROWSER_CONFIG_RETRIEVERS.put(EDGE, LambdatestConfiguration::getEdgeConfig);
    BROWSER_CONFIG_RETRIEVERS.put(SAFARI, LambdatestConfiguration::getSafariConfig);
    BROWSER_CONFIG_RETRIEVERS.put(IE, LambdatestConfiguration::getIeConfig);

  }

  @Override
  public WebDriver getWebDriver(Configuration configuration,
      Optional<Map<String, String>> capabilityMapOptional) {
    LambdatestConfiguration lambdatestConfiguration = configuration.getWeb().getCloud()
        .getProvider().getLambdatestConfiguration();

    String username = Optional.ofNullable(lambdatestConfiguration.getUserName())
        .orElse(System.getProperty(CLOUD_USERNAME));
    String password = Optional.ofNullable(lambdatestConfiguration.getAccessKey())
        .orElse(System.getProperty(CLOUD_ACCESS_KEY));

    String url = String.format("https://%s:%s@hub.lambdatest.com/wd/hub", username, password);

    try {
      return new RemoteWebDriver(new URI(url).toURL(),
          getPlatformSpecificCapabilities(configuration.getWeb().getCloud(),
              capabilityMapOptional));
    } catch (URISyntaxException | MalformedURLException e) {
      throw new FrameworkGenericException(
          "Error occurred while creating remote WebDriver reference", e);
    }
  }

  @Override
  public MutableCapabilities getPlatformSpecificCapabilities(CloudConfiguration cloudConfiguration,
      Optional<Map<String, String>> capabilityMapOptional) {
    LambdatestConfiguration lambdatestConfiguration = cloudConfiguration.getProvider()
        .getLambdatestConfiguration();
    Map<String, Object> ltOptions = new HashMap<>();
    String browserName =
        capabilityMapOptional.isPresent() ? capabilityMapOptional.get().get(BROWSER_NAME)
            : lambdatestConfiguration.getBrowserName();
    CloudBrowserConfiguration browserSpecificCapabilities = getBrowserSpecificCapabilities(
        lambdatestConfiguration, browserName);
    AbstractDriverOptions<?> browserOptions = LambdatestBrowserFactory.getBrowserOptions(
        browserName);
    if (Platform.WEB.name().equalsIgnoreCase(cloudConfiguration.getPlatform())) {
      capabilityMapOptional.ifPresentOrElse(
          map -> {
            browserOptions.setPlatformName(
                String.format("%s %s", map.get(OS), map.get(OS_VERSION)));
            browserOptions.setBrowserVersion(map.get(BROWSER_VERSION));
            ltOptions.put("resolution", map.get(RESOLUTION));
          },
          () -> {
            browserOptions.setPlatformName(
                String.format("%s %s", browserSpecificCapabilities.getOs(),
                    browserSpecificCapabilities.getOsVersion()));
            browserOptions.setBrowserVersion(browserSpecificCapabilities.getBrowserVersion());
            ltOptions.put("resolution", browserSpecificCapabilities.getResolution());
          }
      );
    }
    ltOptions.put("project", lambdatestConfiguration.getProjectName());
    ltOptions.put("build", lambdatestConfiguration.getBuildName());
    ltOptions.put("tunnel", lambdatestConfiguration.isTunnel());
    ltOptions.put("console", lambdatestConfiguration.getConsole());
    ltOptions.put("selenium_version", lambdatestConfiguration.getSeleniumVersion());
    ltOptions.put("w3c", true);
    ltOptions.put("timezone", lambdatestConfiguration.getTimezone());
    ltOptions.put("network", lambdatestConfiguration.isNetwork());
    ltOptions.put("seCdp", true);
    ltOptions.put("headless", lambdatestConfiguration.isHeadless());
    ltOptions.put("video", lambdatestConfiguration.isVideo());
    ltOptions.put("visual", lambdatestConfiguration.isVisual());
    browserOptions.setCapability("LT:Options", ltOptions);
    return browserOptions;
  }

  private CloudBrowserConfiguration getBrowserSpecificCapabilities(
      LambdatestConfiguration lambdatestConfiguration, String browserName) {
    LambdatestBrowserConfigRetriever retriever = BROWSER_CONFIG_RETRIEVERS.get(
        browserName.toLowerCase());
    if (retriever == null) {
      throw new FrameworkGenericException(
          "Please provide valid browser (e.g., chrome, firefox, edge, safari) in the config.json file in the cloud section");
    }
    return retriever.retrieve(lambdatestConfiguration);
  }

  @FunctionalInterface
  interface LambdatestBrowserConfigRetriever {

    CloudBrowserConfiguration retrieve(LambdatestConfiguration lambdatestConfiguration);
  }
}
