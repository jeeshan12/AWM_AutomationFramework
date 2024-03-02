package org.kira.automation.enums;

import lombok.Getter;

@Getter
public enum Platform {

  WEB("web"),
  MOBILE("mobile");

  private final String platformForExecution;

  Platform(String platform) {
    this.platformForExecution = platform;
  }

}
