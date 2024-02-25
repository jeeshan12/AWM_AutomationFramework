package org.kira.automation.enums;

public enum CloudProvider {

  LAMBDATEST("lambdatest"),
  BROWSERSTACK("browserstack");

  private String providerName;

  CloudProvider(String providerName) {
    this.providerName = providerName;
  }

  public String getProviderName() {
    return providerName;
  }
}
