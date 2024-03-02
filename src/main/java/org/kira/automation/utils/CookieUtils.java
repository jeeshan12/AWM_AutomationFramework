package org.kira.automation.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class CookieUtils {

  private CookieUtils() {}

  public static Set<String> getCookiesAsSet(WebDriver driver) {
    Set<String> cookiesSet = new HashSet<>();
    Set<Cookie> cookies = driver.manage().getCookies();

    for (Cookie cookie : cookies) {
      String serializedCookie = serializeCookie(cookie);
      cookiesSet.add(serializedCookie);
    }
    return cookiesSet;
  }

  private static String serializeCookie(Cookie cookie) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
    stringBuilder.append("Domain=").append(cookie.getDomain()).append(";");
    stringBuilder.append("Path=").append(cookie.getPath()).append(";");
    stringBuilder.append("Expiry=").append(cookie.getExpiry()).append(";");
    stringBuilder.append("Secure=").append(cookie.isSecure()).append(";");
    stringBuilder.append("HttpOnly=").append(cookie.isHttpOnly()).append(";");
    return stringBuilder.toString();
  }


  public static void restoreState(WebDriver driver, Set<String> state) {
    driver.manage().deleteAllCookies();
    state.forEach(cookie -> driver.manage().addCookie(parseCookie(cookie)));
    driver.navigate().refresh();
  }

  private static Cookie parseCookie(String cookieString) {
    Map<String, String> cookieMap = new HashMap<>();
    String[] cookieParts = cookieString.split(";");

    for (String part : cookieParts) {
      String[] keyValue = part.trim().split("=", 2);
      if (keyValue.length == 2) {
        cookieMap.put(keyValue[0].trim(), keyValue[1].trim());
      }
    }

    if (cookieMap.containsKey("Domain") && cookieMap.containsKey("Path") && cookieMap.containsKey("Value")) {
      Cookie.Builder builder = new Cookie.Builder(
          cookieMap.get("Name"),
          cookieMap.get("Value"))
          .domain(cookieMap.get("Domain"))
          .path(cookieMap.get("Path"));

      if (cookieMap.containsKey("Expires")) {
        try {
          builder.expiresOn(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").parse(cookieMap.get("Expires")));
        } catch (ParseException e) {
          throw new FrameworkGenericException(String.format(
              "Error while parsing date %s", e.getMessage()
          ));
        }
      }

      if (cookieMap.containsKey("Secure")) {
        builder.isSecure(Boolean.parseBoolean(cookieMap.get("Secure")));
      }

      if (cookieMap.containsKey("HttpOnly")) {
        builder.isHttpOnly(Boolean.parseBoolean(cookieMap.get("HttpOnly")));
      }

      return builder.build();
    } else {
      return null;
    }
  }
}
