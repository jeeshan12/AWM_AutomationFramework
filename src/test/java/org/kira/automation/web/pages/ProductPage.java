package org.kira.automation.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

  @FindBy(css = ".product_label")
  private WebElement productLabel;

  public ProductPage(WebDriver driver) {
    super(driver);
  }

  public String retrieveProductLabelText() {
    return this.productLabel.getText();
  }

  @Override
  public boolean isLoaded() {
    return this.webDriverWait.until(
        (d) -> this.productLabel.isDisplayed()
    );
  }
}
