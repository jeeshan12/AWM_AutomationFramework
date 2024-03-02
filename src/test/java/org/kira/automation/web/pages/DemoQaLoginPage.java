package org.kira.automation.web.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoQaLoginPage extends BasePage{
  public DemoQaLoginPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(id = "userName")
  private WebElement usernameTextBox;
  @FindBy(id = "password")
  private WebElement passwordTextBox;

  @FindBy(id = "login")
  private WebElement loginButton;

  public void performLogin(final String username, final String password) {
    this.usernameTextBox.sendKeys(username);
    this.passwordTextBox.sendKeys(password);
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
    javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", this.loginButton);
    this.loginButton.click();
  }

  @Override
  public boolean isLoaded() {
    return this.webDriverWait.until(
        (d) -> this.usernameTextBox.isDisplayed() && this.usernameTextBox.isEnabled()
    );
  }

}
