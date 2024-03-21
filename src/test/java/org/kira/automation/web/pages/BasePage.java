package org.kira.automation.web.pages;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

  protected WebDriver webDriver;
  protected WebDriverWait webDriverWait;

  public BasePage(WebDriver driver) {
    if (driver == null) {
      throw new FrameworkGenericException(
        "Driver cannot be null, use getDriver() to initialise the driver reference"
      );
    }
    this.webDriver = driver;
    PageFactory.initElements(this.webDriver, this);
    this.webDriverWait = new WebDriverWait(this.webDriver, Duration.of(30, ChronoUnit.SECONDS));
  }

  public abstract boolean isLoaded();
}
