package org.kira.automation.locators;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.openqa.selenium.By;

public class UiLocatorStrategy implements LocatorStrategy {

  private static final Map<String, Function<String, By>> LOCATORS_MAP;

  static {
    LOCATORS_MAP = new HashMap<>(LocatorBuilder.LOCATORS_MAP);
  }

  @Override
  public Map<String, Function<String, By>> getLocatorsMap() {
    return LOCATORS_MAP;
  }
}
