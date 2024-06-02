package org.kira.automation.enums;

import lombok.Getter;

@Getter
public enum CloudProvider {
  LAMBDATEST("lambdatest"),
  BROWSERSTACK("browserstack"),
  K8S("k8s");

  private final String providerName;

  CloudProvider(String providerName) {
    this.providerName = providerName;
  }
}
