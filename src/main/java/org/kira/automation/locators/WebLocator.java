package org.kira.automation.locators;

public class WebLocator extends LocatorBuilder<WebLocator> {

  private WebLocator(LocatorStrategy locatorStrategy) {
    super(locatorStrategy);
  }

  public static WebLocator builder() {
    return new WebLocator(new UiLocatorStrategy());
  }
}
