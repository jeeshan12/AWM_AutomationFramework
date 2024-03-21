package org.kira.automation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class SessionStorageUtil {

  private SessionStorageUtil() {}

  public static void setSessionStorage(WebDriver driver, String hostName, String sessionStorage) {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    String sessionStorageScript = getSessionStorageScript(hostName, sessionStorage);
    jsExecutor.executeScript(sessionStorageScript);
  }

  public static String getSessionStorage(WebDriver driver) {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    return (String) jsExecutor.executeScript("return JSON.stringify(window.sessionStorage);");
  }

  private static String getSessionStorageScript(String hostname, String sessionStorage) {
    return """
    if (window.location.hostname === '%s') {
      const entries = JSON.parse('%s');
      for (const [key, value] of Object.entries(entries)) {
        window.sessionStorage.setItem(key, value);
      }
    }
    """.formatted(hostname, sessionStorage);
  }
}
