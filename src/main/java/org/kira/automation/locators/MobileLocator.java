package org.kira.automation.locators;

public class MobileLocator extends LocatorBuilder<MobileLocator> {

  private MobileLocator(LocatorStrategy locatorStrategy) {
    super(locatorStrategy);
  }

  public static MobileLocator builder() {
    return new MobileLocator(new MobileLocatorStrategy());
  }

  public MobileLocator withAccessibilityId(String accessibilityId) {
    this.byFunction = locatorStrategy.getLocatorsMap().get("accessibilityId");
    this.locatorValue = accessibilityId;
    return this;
  }

  public MobileLocator withAndroidDataMatcher(String androidDataMatcher) {
    this.byFunction = locatorStrategy.getLocatorsMap().get("androidDataMatcher");
    this.locatorValue = androidDataMatcher;
    return this;
  }

  public MobileLocator withAndroidViewMatcher(String androidViewMatcher) {
    this.byFunction = locatorStrategy.getLocatorsMap().get("androidViewMatcher");
    this.locatorValue = androidViewMatcher;
    return this;
  }

  public MobileLocator withIOSClassChain(String iOSClassChain) {
    this.byFunction = locatorStrategy.getLocatorsMap().get("iOSClassChain");
    this.locatorValue = iOSClassChain;
    return this;
  }

  public MobileLocator withIOSNsPredicateString(String iOSNsPredicateString) {
    this.byFunction = locatorStrategy.getLocatorsMap().get("iOSNsPredicateString");
    this.locatorValue = iOSNsPredicateString;
    return this;
  }
}
