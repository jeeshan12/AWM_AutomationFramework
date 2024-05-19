package org.kira.automation.locators;

import io.appium.java_client.AppiumBy;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.openqa.selenium.By;

public class MobileLocatorStrategy implements LocatorStrategy {

  private static final Map<String, Function<String, By>> MOBILE_LOCATOR_MAP;

  static {
    MOBILE_LOCATOR_MAP = new HashMap<>(LocatorBuilder.LOCATORS_MAP);
    MOBILE_LOCATOR_MAP.put("accessibilityId", AppiumBy::accessibilityId);
    MOBILE_LOCATOR_MAP.put("androidDataMatcher", AppiumBy::androidDataMatcher);
    MOBILE_LOCATOR_MAP.put("androidViewMatcher", AppiumBy::androidViewMatcher);
    MOBILE_LOCATOR_MAP.put("iOSClassChain", AppiumBy::iOSClassChain);
    MOBILE_LOCATOR_MAP.put("iOSNsPredicateString", AppiumBy::iOSNsPredicateString);
  }

  @Override
  public Map<String, Function<String, By>> getLocatorsMap() {
    return MOBILE_LOCATOR_MAP;
  }
}
