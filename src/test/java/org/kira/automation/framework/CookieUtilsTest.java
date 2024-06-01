package org.kira.automation.framework;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.kira.automation.utils.CookieUtils;
import org.mockito.ArgumentCaptor;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CookieUtilsTest {

  @Test
  public void testGetCookiesAsSet() {
    // Arrange
    WebDriver driver = mock(ChromeDriver.class);
    Options options = mock(Options.class);
    when(driver.manage()).thenReturn(options);
    Set<Cookie> cookies = new HashSet<>();
    cookies.add(new Cookie("cookie1", "value1", "example.com", "/", null, false, false));
    cookies.add(new Cookie("cookie2", "value2", "example.com", "/", null, false, false));
    System.out.println(driver.manage());

    when(driver.manage().getCookies()).thenReturn(cookies);

    // Act
    Set<String> cookiesSet = CookieUtils.getCookiesAsSet(driver);

    // Assert
    assertEquals(cookiesSet.size(), 2);
    assertTrue(
      cookiesSet.contains(
        "cookie1=value1;Domain=example.com;Path=/;Expiry=null;Secure=false;HttpOnly=false;"
      )
    );
    assertTrue(
      cookiesSet.contains(
        "cookie2=value2;Domain=example.com;Path=/;Expiry=null;Secure=false;HttpOnly=false;"
      )
    );
  }

  @Test
  public void testRestoreState() {
    // Arrange
    WebDriver driver = mock(ChromeDriver.class);
    Options options = mock(Options.class);
    when(driver.manage()).thenReturn(options);
    Navigation navigation = mock(Navigation.class);
    when(driver.navigate()).thenReturn(navigation);
    Set<String> state = new HashSet<>();
    state.add("cookie1=value1;Domain=example.com;Path=/;Secure=false;HttpOnly=false;");
    state.add("cookie2=value2;Domain=example.com;Path=/;Secure=false;HttpOnly=false;");

    ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);

    // Act
    CookieUtils.restoreState(driver, state);

    // Assert
    verify(driver.manage(), times(1)).deleteAllCookies();
    verify(driver.manage(), times(2)).addCookie(cookieCaptor.capture());
    verify(navigation, times(1)).refresh();
  }
}
