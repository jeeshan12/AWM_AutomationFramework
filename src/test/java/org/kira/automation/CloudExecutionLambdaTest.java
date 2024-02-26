package org.kira.automation;

import com.aventstack.extentreports.Status;
import org.kira.automation.annotations.Web;
import org.kira.automation.annotations.WebCloud;
import org.kira.automation.runner.TestSuiteRunner;
import org.testng.annotations.Test;

public class CloudExecutionLambdaTest extends TestSuiteRunner {


  @Test
  @Web
  public void testLambdatestCloudBrowser() {
    getExtentTest().log(Status.INFO, "Opening Browser to test cloud execution");
    getDriver().navigate().to("https://google.com/");
  }


  @Test
  @Web
  @WebCloud(os = "macOS", osVersion = "Sonoma", browserVersion = "17.0", browserName = "safari")
  public void testLambdatestCloudBrowserWithAnnotation() {
    getExtentTest().log(Status.INFO, "Opening Browser for test with annotation");
    getDriver().navigate().to("https://google.com/");
  }

}
