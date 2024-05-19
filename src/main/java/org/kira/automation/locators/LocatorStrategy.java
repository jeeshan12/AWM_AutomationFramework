package org.kira.automation.locators;

import java.util.Map;
import java.util.function.Function;
import org.openqa.selenium.By;

public interface LocatorStrategy {
  Map<String, Function<String, By>> getLocatorsMap();
}
