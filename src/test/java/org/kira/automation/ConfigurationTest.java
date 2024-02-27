package org.kira.automation;

import org.kira.automation.configuration.Configuration;
import org.kira.automation.constants.FrameworkConstants;
import org.kira.automation.utils.FileUtils;
import org.kira.automation.utils.JsonParserUtil;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */

public class ConfigurationTest {

  @Test
  public void testConfiguration() {
    Configuration webConfiguration = JsonParserUtil.readJsonFile(FileUtils.readFileAsString(
            FrameworkConstants.TEST_RESOURCE_FOLDER + FrameworkConstants.CONFIG_FILE_NAME),
        Configuration.class);
    System.out.println(webConfiguration.getWeb().getBrowser());
    System.out.println(webConfiguration.getWeb().getBrowserOptions().getChrome());
    System.out.println(webConfiguration.getWeb().getCloud());
    System.out.println(webConfiguration.getWeb().getSeleniumGrid());
    System.out.println(webConfiguration.getApi().getRestConfiguration());
    System.out.println(webConfiguration.getApi().getGraphQLConfiguration());

  }

}
