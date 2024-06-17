package org.kira.automation.enums;

import lombok.Getter;

@Getter
public enum CloudProvider {
  LAMBDATEST("lambdatest"),
  BROWSERSTACK("browserstack"),
  GRID("grid");

  private final String providerName;

  CloudProvider(String providerName) {
    this.providerName = providerName;
  }
}
