package org.kira.automation.locators;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.openqa.selenium.By;

public abstract class LocatorBuilder<T extends LocatorBuilder<T>> {

  protected String locatorValue;

  protected Function<String, By> byFunction;

  protected LocatorStrategy locatorStrategy;

  protected static final Map<String, Function<String, By>> LOCATORS_MAP = new HashMap<>();

  static {
    LOCATORS_MAP.put("name", By::name);
    LOCATORS_MAP.put("xpath", By::xpath);
    LOCATORS_MAP.put("id", By::id);
    LOCATORS_MAP.put("className", By::className);
    LOCATORS_MAP.put("cssSelector", By::cssSelector);
    LOCATORS_MAP.put("linkText", By::linkText);
    LOCATORS_MAP.put("partialLinkText", By::partialLinkText);
    LOCATORS_MAP.put("tagName", By::tagName);
  }

  protected LocatorBuilder(LocatorStrategy locatorStrategy) {
    this.locatorStrategy = locatorStrategy;
  }

  public T withName(String name) {
    this.byFunction = locatorStrategy.getLocatorsMap().get("name");
    this.locatorValue = name;
    return (T) this;
  }

  public T withXPath(String xpath) {
    this.byFunction = locatorStrategy.getLocatorsMap().get("xpath");
    this.locatorValue = xpath;
    return (T) this;
  }

  public T withId(String id) {
    this.byFunction = locatorStrategy.getLocatorsMap().get("id");
    this.locatorValue = id;
    return (T) this;
  }

  public T withClassName(String className) {
    this.byFunction = locatorStrategy.getLocatorsMap().get("className");
    this.locatorValue = className;
    return (T) this;
  }

  public T withCssSelector(String cssSelector) {
    this.byFunction = locatorStrategy.getLocatorsMap().get("cssSelector");
    this.locatorValue = cssSelector;
    return (T) this;
  }

  public T withLinkText(String linkText) {
    this.byFunction = locatorStrategy.getLocatorsMap().get("linkText");
    this.locatorValue = linkText;
    return (T) this;
  }

  public T withPartialLinkText(String partialLinkText) {
    this.byFunction = locatorStrategy.getLocatorsMap().get("partialLinkText");
    this.locatorValue = partialLinkText;
    return (T) this;
  }

  public T withTagName(String tagName) {
    this.byFunction = locatorStrategy.getLocatorsMap().get("tagName");
    this.locatorValue = tagName;
    return (T) this;
  }

  public By build() {
    if (byFunction == null) {
      throw new IllegalArgumentException("Locator type is not set or invalid.");
    }
    return byFunction.apply(locatorValue);
  }
}
