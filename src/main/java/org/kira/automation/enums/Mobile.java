package org.kira.automation.enums;

import lombok.Getter;

@Getter
public enum Mobile {
  ANDROID("android"),
  IOS("ios");

  private final String name;

  Mobile(String name) {
    this.name = name;
  }
}
