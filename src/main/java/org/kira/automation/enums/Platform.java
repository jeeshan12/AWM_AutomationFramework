package org.kira.automation.enums;

public enum Platform {

  WEB("web"),
  MOBILE("mobile");

  private final String platformForExecution;

  Platform(String platform) {
    this.platformForExecution = platform;
  }

  public String getPlatform() {
    return platformForExecution;
  }
}
