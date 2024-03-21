package org.kira.automation.web.tests;

import java.util.Set;
import org.kira.automation.annotations.Chrome;
import org.kira.automation.annotations.Web;
import org.kira.automation.runner.TestSuiteRunner;
import org.kira.automation.utils.CookieUtils;
import org.kira.automation.web.pages.BookStoreLandingPage;
import org.kira.automation.web.pages.DemoQaLoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DemoQaLoginTest extends TestSuiteRunner {

  private DemoQaLoginPage demoQaLoginPage;
  private BookStoreLandingPage bookStoreLandingPage;

  @BeforeMethod
  public void setStorageState() {
    Set<String> storedState = getRedisManager().retrieveState();
    this.demoQaLoginPage = new DemoQaLoginPage(getDriver());
    this.bookStoreLandingPage = new BookStoreLandingPage(getDriver());
    if (storedState.isEmpty()) {
      getDriver().navigate().to("https://demoqa.com/login");
      getDriver().manage().window().maximize();
      demoQaLoginPage.performLogin("Admin", "admin@12");
      Assert.assertEquals(bookStoreLandingPage.isLoaded(), true);
      Assert.assertEquals(bookStoreLandingPage.retrieveLogoutButtonText(), "Log out");
      getRedisManager().storeState(CookieUtils.getCookiesAsSet(getDriver()));
    } else {
      getDriver().navigate().to("https://demoqa.com/profile");
      CookieUtils.restoreState(getDriver(), storedState);
    }
  }

  @Test
  @Web
  @Chrome
  public void testStorageState() {
    getDriver().manage().window().maximize();
    Assert.assertEquals(this.bookStoreLandingPage.retrieveLogoutButtonText(), "Log out");
    Set<String> cookiesAsSet = CookieUtils.getCookiesAsSet(getDriver());
    System.out.println(cookiesAsSet);
  }
}
