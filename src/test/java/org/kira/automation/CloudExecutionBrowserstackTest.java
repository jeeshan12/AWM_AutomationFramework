package org.kira.automation;

import com.aventstack.extentreports.Status;
import org.kira.automation.annotations.Web;
import org.kira.automation.annotations.WebCloud;
import org.kira.automation.runner.TestSuiteRunner;
import org.testng.annotations.Test;

public class CloudExecutionBrowserstackTest extends TestSuiteRunner {

  @Test
  @Web
  public void testCloudBrowser() {
    getExtentTest().log(Status.INFO, "Opening Browser to test cloud execution");
    getDriver().navigate().to("https://google.com/");
  }

  @Test
  @Web
  @WebCloud(os = "OS X", osVersion = "Sonoma", browserVersion = "121.0", browserName = "firefox")
  public void testCloudBrowserWithAnnotation() {
    getExtentTest().log(Status.INFO, "Opening Browser for test with annotation");
    getDriver().navigate().to("https://google.com/");
  }
}
