package org.kira.automation.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
  public LoginPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(id = "user-name")
  private WebElement usernameTextBox;
  @FindBy(id = "password")
  private WebElement passwordTextBox;

  @FindBy(id = "login-button")
  private WebElement loginButton;

  public void performLogin(final String username, final String password) {
    this.usernameTextBox.sendKeys(username);
    this.passwordTextBox.sendKeys(password);
    this.loginButton.click();
  }

  @Override
  public boolean isLoaded() {
    return this.webDriverWait.until(
        (d) -> this.usernameTextBox.isDisplayed() && this.usernameTextBox.isEnabled()
    );
  }
}
