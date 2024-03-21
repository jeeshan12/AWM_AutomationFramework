package org.kira.automation;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.HashMap;
import java.util.Map;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.constants.FrameworkConstants;
import org.kira.automation.drivers.DriverConsumer;
import org.kira.automation.drivers.web.ChromeBrowserServiceInjector;
import org.kira.automation.drivers.web.FirefoxBrowserServiceInjector;
import org.kira.automation.utils.FileUtils;
import org.kira.automation.utils.JsonParserUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestBrowser {

  private WebDriver driver;

  private Configuration webConfiguration;

  @BeforeClass
  public void setUp() {
    this.webConfiguration = JsonParserUtil.readJsonFile(
      FileUtils.readFileAsString(
        FrameworkConstants.TEST_RESOURCE_FOLDER + FrameworkConstants.CONFIG_FILE_NAME
      ),
      Configuration.class
    );
  }

  @Test
  public void testFirefoxBrowser() {
    Injector firefoxDriverInjector = Guice.createInjector(new FirefoxBrowserServiceInjector());
    DriverConsumer browserConsumer = firefoxDriverInjector.getInstance(DriverConsumer.class);
    this.driver = browserConsumer.getWebDriver(this.webConfiguration);
    this.driver.navigate().to("https://google.com/");
  }

  @Test
  public void testChromeBrowser() {
    Map<String, String> map = new HashMap<>();
    Injector chromeDriverInjector = Guice.createInjector(new ChromeBrowserServiceInjector());
    DriverConsumer driverConsumer = chromeDriverInjector.getInstance(DriverConsumer.class);
    this.driver = driverConsumer.getWebDriver(this.webConfiguration);
    this.driver.navigate().to("https://google.com/");
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    this.driver.close();
  }
}
