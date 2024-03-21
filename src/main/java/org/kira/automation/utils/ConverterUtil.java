package org.kira.automation.utils;

public final class ConverterUtil {

  private ConverterUtil() {}

  public static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean isBoolean(String str) {
    return (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false"));
  }
}
