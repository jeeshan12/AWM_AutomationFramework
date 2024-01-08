package org.kira.automation;

import java.util.HashMap;
import java.util.Map;

import org.kira.automation.configuration.Configuration;
import org.kira.automation.factory.WebDriverFactorySupplier;
import org.kira.automation.constants.FrameworkConstants;
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
         this.webConfiguration =  JsonParserUtil.readJsonFile (
            FileUtils.readFileAsString (
                FrameworkConstants.TEST_RESOURCE_FOLDER + FrameworkConstants.CONFIG_FILE_NAME), Configuration.class);
    }

    @Test
    public void testFirefoxBrowser() {
        Map<String, String> map = new HashMap<> ();
        this.driver = WebDriverFactorySupplier.getWebDriver ("firefox").getWebDriver ( this.webConfiguration);
        this.driver.navigate ().to ("https://google.com/");
    }

    @Test
    public void testChromeBrowser() {
        Map<String, String> map = new HashMap<> ();
        this.driver = WebDriverFactorySupplier.getWebDriver ("chrome").getWebDriver ( this.webConfiguration);
        this.driver.navigate ().to ("https://google.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        this.driver.close ();
    }
}
