package org.kira.automation;

import com.aventstack.extentreports.Status;
import org.kira.automation.annotations.Firefox;
import org.kira.automation.annotations.Web;
import org.kira.automation.runner.TestSuiteRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

public class TestScreenshot extends TestSuiteRunner {

  @Test(expectedExceptions = NoSuchElementException.class)
  @Web
  @Firefox
  public void testScreenshotOnFailure() {
    getExtentTest().log(Status.INFO, "Test Firefox Browser");
    getDriver().navigate().to("https://google.com/");
    getDriver().findElement(By.name("aa")).sendKeys("Test Automation");
  }
}
