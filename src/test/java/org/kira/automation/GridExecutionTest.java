package org.kira.automation;

import com.aventstack.extentreports.Status;
import org.kira.automation.annotations.Grid;
import org.kira.automation.annotations.Web;
import org.kira.automation.runner.TestSuiteRunner;
import org.testng.annotations.Test;

public class GridExecutionTest extends TestSuiteRunner {

  @Test(invocationCount = 4)
  @Web
  public void testGridBrowser() {
    this.getExtentTest().log(Status.INFO, "Opening Browser to test cloud execution");
    this.getDriver().navigate().to("https://google.com/");
  }

  @Test(invocationCount = 4, threadPoolSize = 3)
  @Web
  @Grid(browserName = "firefox", platformName = "Linux", browserVersion = "121")
  public void testGridBrowserWithAnnotation() {
    this.getExtentTest().log(Status.INFO, "Opening Browser for test with annotation");
    this.getDriver().navigate().to("https://google.com/");
  }
}
