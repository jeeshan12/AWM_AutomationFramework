package org.kira.automation.mobile;

import org.kira.automation.annotations.Android;
import org.kira.automation.annotations.Mobile;
import org.kira.automation.runner.TestSuiteRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AndroidBrowserLaunchTest extends TestSuiteRunner {

  @Test
  @Android
  @Mobile
  public void launchBrowser() {
    getDriver().navigate().to("https://www.google.com/");
    Assert.assertEquals(getDriver().getTitle(), "Google");
  }
}
