package org.kira.automation.drivers.cloud;

import static org.kira.automation.constants.FrameworkConstants.BROWSER_NAME;
import static org.kira.automation.constants.FrameworkConstants.BROWSER_VERSION;
import static org.kira.automation.constants.FrameworkConstants.CHROME;
import static org.kira.automation.constants.FrameworkConstants.CLOUD_ACCESS_KEY;
import static org.kira.automation.constants.FrameworkConstants.CLOUD_USERNAME;
import static org.kira.automation.constants.FrameworkConstants.EDGE;
import static org.kira.automation.constants.FrameworkConstants.FIREFOX;
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
import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.cloud.CloudBrowserConfiguration;
import org.kira.automation.configuration.cloud.CloudConfiguration;
import org.kira.automation.configuration.cloud.browserstack.BrowserstackConfiguration;
import org.kira.automation.enums.Platform;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserstackRemoteDriverServiceImpl implements CloudRemoteDriverService {

  private static final Map<String, BrowserstackBrowserConfigRetriever> BROWSER_CONFIG_RETRIEVERS =
    new HashMap<>();

  static {
    BROWSER_CONFIG_RETRIEVERS.put(CHROME, BrowserstackConfiguration::getChromeConfig);
    BROWSER_CONFIG_RETRIEVERS.put(FIREFOX, BrowserstackConfiguration::getFirefoxConfig);
    BROWSER_CONFIG_RETRIEVERS.put(EDGE, BrowserstackConfiguration::getEdgeConfig);
    BROWSER_CONFIG_RETRIEVERS.put(SAFARI, BrowserstackConfiguration::getSafariConfig);
  }

  @Override
  public WebDriver getWebDriver(
    Configuration configuration,
    Optional<Map<String, String>> capabilityMapOptional
  ) {
    BrowserstackConfiguration browserstackConfiguration = configuration
      .getWeb()
      .getCloud()
      .getProvider()
      .getBrowserstackConfiguration();

    String username = Optional.ofNullable(browserstackConfiguration.getUserName()).orElse(
      System.getProperty(CLOUD_USERNAME)
    );
    String password = Optional.ofNullable(browserstackConfiguration.getAccessKey()).orElse(
      System.getProperty(CLOUD_ACCESS_KEY)
    );

    String url = String.format(
      "https://%s:%s@hub-cloud.browserstack.com/wd/hub",
      username,
      password
    );

    try {
      return new RemoteWebDriver(
        new URI(url).toURL(),
        getPlatformSpecificCapabilities(configuration.getWeb().getCloud(), capabilityMapOptional)
      );
    } catch (URISyntaxException | MalformedURLException e) {
      throw new FrameworkGenericException(
        "Error occurred while creating remote WebDriver reference",
        e
      );
    }
  }

  private CloudBrowserConfiguration getBrowserSpecificCapabilities(
    BrowserstackConfiguration browserstackConfiguration,
    String browserName
  ) {
    BrowserstackBrowserConfigRetriever retriever = BROWSER_CONFIG_RETRIEVERS.get(
      browserName.toLowerCase()
    );
    if (retriever == null) {
      throw new FrameworkGenericException(
        "Please provide valid browser (e.g., chrome, firefox, edge, safari) in the config.json file in the cloud section"
      );
    }
    return retriever.retrieve(browserstackConfiguration);
  }

  @Override
  public MutableCapabilities getPlatformSpecificCapabilities(
    CloudConfiguration cloudConfiguration,
    Optional<Map<String, String>> capabilityMapOptional
  ) {
    BrowserstackConfiguration browserstackConfiguration = cloudConfiguration
      .getProvider()
      .getBrowserstackConfiguration();
    MutableCapabilities capabilities = new MutableCapabilities();
    Map<String, Object> bstackOptions = new HashMap<>();
    String browserName = capabilityMapOptional.isPresent()
      ? capabilityMapOptional.get().get(BROWSER_NAME)
      : browserstackConfiguration.getBrowserName();
    CloudBrowserConfiguration browserSpecificCapabilities = getBrowserSpecificCapabilities(
      browserstackConfiguration,
      browserName
    );
    if (Platform.WEB.name().equalsIgnoreCase(cloudConfiguration.getPlatform())) {
      capabilities.setCapability(BROWSER_NAME, browserName);
      capabilityMapOptional.ifPresentOrElse(
        map -> {
          bstackOptions.put(OS, map.get(OS));
          bstackOptions.put(BROWSER_VERSION, map.get(BROWSER_VERSION));
          bstackOptions.put(OS_VERSION, map.get(OS_VERSION));
          bstackOptions.put(RESOLUTION, map.get(RESOLUTION));
        },
        () -> {
          bstackOptions.put(OS, browserSpecificCapabilities.getOs());
          bstackOptions.put(OS_VERSION, browserSpecificCapabilities.getOsVersion());
          bstackOptions.put(BROWSER_VERSION, browserSpecificCapabilities.getBrowserVersion());
          bstackOptions.put(RESOLUTION, browserSpecificCapabilities.getResolution());
        }
      );
    }
    bstackOptions.put("projectName", browserstackConfiguration.getProjectName());
    bstackOptions.put("buildName", browserstackConfiguration.getBuildName());
    bstackOptions.put("sessionName", browserstackConfiguration.getSessionName());
    bstackOptions.put("video", browserstackConfiguration.getVideo());
    bstackOptions.put("networkLogs", browserstackConfiguration.getNetworkLogs());
    bstackOptions.put("debug", browserstackConfiguration.getDebug());
    capabilities.setCapability("bstack:options", bstackOptions);
    return capabilities;
  }

  @FunctionalInterface
  interface BrowserstackBrowserConfigRetriever {
    CloudBrowserConfiguration retrieve(BrowserstackConfiguration configuration);
  }
}
