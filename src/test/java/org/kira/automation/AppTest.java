package org.kira.automation;

import org.kira.automation.configuration.Configuration;
import org.kira.automation.constants.FrameworkConstants;
import org.kira.automation.utils.FileUtils;
import org.kira.automation.utils.JsonParserUtil;

/**
 * Unit test for simple App.
 */

public class AppTest {

    public static void main (String[] args) {
        Configuration webConfiguration =     JsonParserUtil.readJsonFile (FileUtils.readFileAsString (FrameworkConstants.TEST_RESOURCE_FOLDER + FrameworkConstants.CONFIG_FILE_NAME),
            Configuration.class);
        System.out.println (webConfiguration.getWeb ().getBrowser ());
        System.out.println (webConfiguration.getWeb ().getBrowserOptions ().getChrome ());
    }

}
