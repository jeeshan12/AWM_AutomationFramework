package org.kira.automation.web.tests;

import org.kira.automation.annotations.Chrome;
import org.kira.automation.annotations.Firefox;
import org.kira.automation.annotations.Web;
import org.kira.automation.runner.TestSuiteRunner;
import org.kira.automation.utils.SessionStorageUtil;
import org.kira.automation.web.pages.LoginPage;
import org.kira.automation.web.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestSuiteRunner {

  private LoginPage loginPage;

  private ProductPage productPage;

  private String sessionStorage;

  @BeforeMethod
  public void setUp() {
    this.loginPage = new LoginPage(getDriver());
    this.productPage = new ProductPage(getDriver());
    getDriver().navigate().to("https://www.saucedemo.com/v1/");
  }

  @Test
  @Web
  @Firefox
  public void performLogin() {
    this.loginPage.performLogin("standard_user", "secret_sauce");
    Assert.assertEquals(this.productPage.retrieveProductLabelText(), "Products");
    sessionStorage = SessionStorageUtil.getSessionStorage(getDriver());
  }

  @Test(dependsOnMethods = "performLogin")
  @Web
  @Chrome
  public void testSessionStorage() {
    SessionStorageUtil.setSessionStorage(getDriver(), "www.saucedemo.com", sessionStorage);
    getDriver().navigate().to("https://www.saucedemo.com/v1/inventory.html");
    Assert.assertEquals(this.productPage.retrieveProductLabelText(), "Products");
  }
}
