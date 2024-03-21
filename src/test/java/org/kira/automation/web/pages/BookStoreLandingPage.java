package org.kira.automation.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookStoreLandingPage extends BasePage {

  public BookStoreLandingPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(id = "submit")
  private WebElement logoutButton;

  public String retrieveLogoutButtonText() {
    return this.logoutButton.getText();
  }

  @Override
  public boolean isLoaded() {
    return this.webDriverWait.until(
        d -> this.logoutButton.isDisplayed() && this.logoutButton.isEnabled()
      );
  }
}
