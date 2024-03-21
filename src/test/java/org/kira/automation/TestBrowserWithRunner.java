package org.kira.automation;

import com.aventstack.extentreports.Status;
import org.kira.automation.annotations.Chrome;
import org.kira.automation.annotations.Firefox;
import org.kira.automation.annotations.Web;
import org.kira.automation.runner.TestSuiteRunner;
import org.testng.annotations.Test;

public class TestBrowserWithRunner extends TestSuiteRunner {

  @Test
  @Web
  @Firefox
  public void testFirefoxBrowser() {
    getExtentTest().log(Status.INFO, "Test Firefox Browser");
    getDriver().navigate().to("https://google.com/");
  }

  @Test
  @Chrome
  @Web
  public void testChromeBrowser() {
    getExtentTest().log(Status.INFO, "Test Chrome Browser");
    getDriver().navigate().to("https://google.com/");
  }

  @Test
  @Web
  public void testDefaultBrowserFromConfig() {
    getExtentTest().log(Status.INFO, "Logging default browser from config");
    getDriver().navigate().to("https://google.com/");
  }
}
