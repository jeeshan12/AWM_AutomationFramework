package org.kira.automation.web.tests;

import java.util.Set;
import org.kira.automation.annotations.Chrome;
import org.kira.automation.annotations.Web;
import org.kira.automation.runner.TestSuiteRunner;
import org.kira.automation.utils.CookieUtils;
import org.kira.automation.web.pages.LoginPage;
import org.kira.automation.web.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestSuiteRunner {

  private LoginPage loginPage;

  private ProductPage productPage;

  @BeforeMethod
  public void setUp() {
    this.loginPage = new LoginPage(getDriver());
    this.productPage = new ProductPage(getDriver());
    getDriver().navigate().to("https://www.saucedemo.com/v1/");
  }

  @Test
  @Web
  @Chrome
  public void performLogin() {
    this.loginPage.performLogin("standard_user", "secret_sauce");
    Assert.assertEquals( this.productPage.retrieveProductLabelText(), "Products");
    Set<String> cookiesAsSet = CookieUtils.getCookiesAsSet(getDriver());
    System.out.println(getDriver().manage().getCookies());
    System.out.println(cookiesAsSet);
  }

}
